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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.sps.data.Comment;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

/* Servlet that creates a comment*/
@WebServlet("/new-comment")
public class NewCommentServlet extends HttpServlet {
  
  private static final DateTimeFormatter commentDateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Comment newComment = new Comment(
      request.getParameter("commentAuthor"),
      request.getParameter("commentText"), 
      commentDateFormat.format(LocalDateTime.now()),
      System.currentTimeMillis());
      
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(newComment.makeEntity());

    response.sendRedirect("/index.html");
  }
}
