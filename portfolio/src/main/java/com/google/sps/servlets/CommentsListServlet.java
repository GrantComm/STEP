// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.search.query.QueryParser.query_return;
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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/** Servlet responsible for listing comments. */
@WebServlet("/comments-list")
public class CommentsListServlet extends HttpServlet {

  private DatastoreService datastore;

  @Override
  public void init() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    final int kMaxComments = 3; 
    Query query = new Query("Comment").addSort("timestampMillis", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);
    List<Comment> comments = new ArrayList<>();
    FetchOptions fetchOps = FetchOptions.Builder.withLimit(kMaxComments).offset((pageNumber - 1) * kMaxComments);

    for (Entity entity : results.asIterable(fetchOps)) {
      comments.add(
        new Comment(
          (String) entity.getProperty("author"), 
          (String) entity.getProperty("content"), 
          (String) entity.getProperty("currentDate"), 
          (long) entity.getProperty("timestampMillis")));
      
    }
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }
}
