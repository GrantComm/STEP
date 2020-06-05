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

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.time.format.DateTimeFormatter;
import java.time.*; 

/* Servlet that creates a comment*/
@WebServlet("/new-comment")
public class NewCommentServlet extends HttpServlet {
  
  private static final DateTimeFormatter commentDateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String text = request.getParameter("commentText");
    long timestamp_millis = System.currentTimeMillis();
    String currentDate = commentDateFormat.format(LocalDateTime.now());
    
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("content", text);
    commentEntity.setProperty("currentDate", currentDate);
    commentEntity.setProperty("timestamp_millis", timestamp_millis);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);

    response.sendRedirect("/index.html");
  }
  
  private String convertToJsonUsingGson(ArrayList<String> comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }

  private String getParameter(HttpServletRequest request, String commentText, String defaultValue) {
    String value = request.getParameter(commentText);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
  
}
