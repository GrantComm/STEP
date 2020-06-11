package com.google.sps.data;

// User object that represents the current user on the site 
public final class User{
  private final boolean loggedIn; 
  private final String url; 
  
  public User(boolean loggedIn, String url) {
    this.loggedIn = loggedIn;
    this.url  = url; 
  }
}
