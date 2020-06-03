package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/comments-list")
public class CommentsListServlet extends HttpServlet {
  
  private DatastoreService datastore; 
  
  @Override
  public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
  } 
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int numComments = getMax(request); 
    Query query = new Query("Comment").addSort("timestamp_millis", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asList(FetchOptions.Builder.withLimit(numComments))) {
      long id = entity.getKey().getId();
      String content = (String) entity.getProperty("content");
      long timestamp_millis = (long) entity.getProperty("timestamp_millis");

      Comment comment = new Comment(id, content, timestamp_millis);
      comments.add(comment);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }
  
  private int getMax(HttpServletRequest request) {
    String requestedMaxComments = request.getParameter("max"); 
    return Integer.parseInt(requestedMaxComments); 
  }
}