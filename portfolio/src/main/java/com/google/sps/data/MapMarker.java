package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

// A Marker on the map
public final class MapMarker {
  private final String collegeName; 
  private final String internName; 
  private final long longitude; 
  private final long latitude; 

  public MapMarker(String collegeName, String internName, long longitude, long latitude) {
    this.collegeName = collegeName; 
    this.internName = internName; 
    this.longitude = longitude;
    this.latitude = latitude; 
  }

  public Entity makeEntity(){
    Entity mapMarkerEntity = new Entity("mapMarker");
    mapMarkerEntity.setProperty("collegeName", this.collegeName);
    mapMarkerEntity.setProperty("internName", this.internName);
    mapMarkerEntity.setProperty("longitude", this.longitude);
    mapMarkerEntity.setProperty("latitude", this.latitude);
    return mapMarkerEntity; 
  }
}
