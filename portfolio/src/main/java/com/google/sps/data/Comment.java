package com.google.sps.data;

/** An item on a todo list. */
public final class Comment {

  private final long id;
  private final String content;
  private final long timestamp;

  public Comment(long id, String content, long timestamp) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
  }
}