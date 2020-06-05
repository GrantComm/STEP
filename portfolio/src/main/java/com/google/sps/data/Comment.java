package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

// A comment in the comment list
public final class Comment {

  private final String content;
  private final String currentDate; 
  private final long timestamp;
  
  public Comment(String content, String currentDate, long timestamp){
    this.content = content;
    this.currentDate = currentDate; 
    this.timestamp = timestamp;
  }
  
  public Entity makeEntity(){
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("content", this.content);
    commentEntity.setProperty("currentDate", this.currentDate);
    commentEntity.setProperty("timestampMillis", this.timestamp);
    return commentEntity; 
  }
  
}
