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
import com.google.appengine.api.search.checkers.GeoPointChecker;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.sps.data.MapMarker;
import lombok.SneakyThrows;

// Servlet that creates a map marker
@WebServlet("/new-map-marker")
public class NewMapMarkerServlet extends HttpServlet {

  private final GeoApiContext geoApiContext = new GeoApiContext.Builder()
    .apiKey("AIzaSyCfSokI5WnEOJZg12yfV-mINDnFaClsj6M").build();
   
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    MapMarker newMapMarker = new MapMarker(
      request.getParameter("collegeName"),
      request.getParameter("internName"), 
      (long)getLngLat(request.getParameter("collegeAddress")).geometry.location.lng,
      (long)getLngLat(request.getParameter("collegeAddress")).geometry.location.lat);
      
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(newMapMarker.makeEntity());

    response.sendRedirect("/index.html");
  }

  @SneakyThrows
  public GeocodingResult getLngLat(String address) {
    GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
    GeocodingResult result = results[0];
    return result; 
  } 
}
