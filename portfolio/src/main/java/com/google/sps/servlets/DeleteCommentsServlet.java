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
 
 
/** Servlet responsible for listing comments. */
@WebServlet("/delete-comments")
public class DeleteCommentsServlet extends HttpServlet {
  
  private DatastoreService datastore; 
  
  @Override
  public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
  } 
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp_millis", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);
    List<Comment> comments = new ArrayList<>();
      for (Entity entity : results.asIterable()) {
        datastore.delete(entity.getKey()); 
      }
      response.sendRedirect("/index.html");
  }
}
