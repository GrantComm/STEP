package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

// A comment in the comment list
public final class Comment {

  private final String author; 
  private final String content;
  private final String currentDate; 
  private final long timestampMillis;
  
  public Comment(String author, String content, String currentDate, long timestampMillis){
    this.author = author;
    this.content = content;
    this.currentDate = currentDate; 
    this.timestampMillis = timestampMillis;
  }
  
  public Entity makeEntity(){
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("author", this.author);
    commentEntity.setProperty("content", this.content);
    commentEntity.setProperty("currentDate", this.currentDate);
    commentEntity.setProperty("timestampMillis", this.timestampMillis);
    return commentEntity; 
  }
}
