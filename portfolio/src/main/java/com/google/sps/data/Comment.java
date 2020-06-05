package com.google.sps.data;
import java.time.*; 

// A comment in the comment list
public final class Comment {

  private final long id;
  private final String content;
  private final String currentDate; 
  private final long timestamp;

  public Comment(long id, String content, String currentDate, long timestamp) {
    this.id = id;
    this.content = content;
    this.currentDate = currentDate; 
    this.timestamp = timestamp;
  }
}