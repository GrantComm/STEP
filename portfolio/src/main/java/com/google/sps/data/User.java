package com.google.sps.data;

public final class User{
  private final boolean loggedIn; 
  private final String url; 
  
  public User(boolean loggedIn, String url) {
    this.loggedIn = loggedIn;
    this.url  = url; 
  }
}
