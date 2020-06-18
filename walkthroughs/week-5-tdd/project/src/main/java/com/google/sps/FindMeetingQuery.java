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

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator; 
import java.util.List;

public final class FindMeetingQuery {
  
  private static final Comparator<Event> ORDER_BY_START = new Comparator<Event>() {
    @Override
    public int compare(Event a, Event b) {
      return Long.compare(a.getWhen().start(), b.getWhen().start());
    }};
  
  public Collection<TimeRange> query(Collection<Event> eventCollection, MeetingRequest request) { 
    
    // Too long of a request
    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return Collections.emptyList(); 
    }
    
    // No events or attendees 
    List<Event> events = new ArrayList<Event>(eventCollection);
    List<String> meetingAttendees = new ArrayList<String>(request.getAttendees());
    if (events.isEmpty() || meetingAttendees.isEmpty()) {
      return Arrays.asList(TimeRange.WHOLE_DAY); 
    }
    
    // Ignores unattended events
    List<Event> importantEvents = new ArrayList<Event>(); 
    for (Event event : events) {
      if (!Collections.disjoint(event.getAttendees(), request.getAttendees())) {
        importantEvents.add(event); 
      }
    }
    
    // If the list of important events is empty, return the whole day 
    if (importantEvents.isEmpty()) {
     return Arrays.asList(TimeRange.WHOLE_DAY);
    }
    
    // Order the events by their start time 
    Collections.sort(importantEvents, ORDER_BY_START); 
    
    // Instantiate the list of acceptable meeting times and ensure it is empty
    List<TimeRange> acceptableMeetingTimes = new ArrayList<TimeRange>();
    acceptableMeetingTimes.clear();
    
    // Add the event that starts first
    acceptableMeetingTimes.add(TimeRange.fromStartEnd(0, importantEvents.get(0).getWhen().start(), false)); 
    
    // Set the end time and start time as the end of the first event 
    int latestEventEnd = importantEvents.get(0).getWhen().end();
    int start = importantEvents.get(0).getWhen().start();
    
    // Iterate through the events and add times where the request duration fits
    for (Event event : importantEvents) {
      start = event.getWhen().start();
       
      if (start > latestEventEnd) {
        if (rangeLessThanDuration(TimeRange.fromStartEnd(latestEventEnd, start, false), request.getDuration())) {
          acceptableMeetingTimes.add(TimeRange.fromStartEnd(latestEventEnd, start, false)); 
          latestEventEnd = event.getWhen().end(); 
        }
        latestEventEnd = event.getWhen().end(); 
      }
      
      if (start < latestEventEnd) {
        if (latestEventEnd < event.getWhen().end()) {
          latestEventEnd = event.getWhen().end();
          if (rangeLessThanDuration(TimeRange.fromStartEnd(latestEventEnd, start, false), request.getDuration())) {
            acceptableMeetingTimes.add(TimeRange.fromStartEnd(latestEventEnd, start, false)); 
            latestEventEnd = event.getWhen().end();
          } 
        }
        if (rangeLessThanDuration(TimeRange.fromStartEnd(latestEventEnd, start, false), request.getDuration())) {
          acceptableMeetingTimes.add(TimeRange.fromStartEnd(latestEventEnd, start, false)); 
          latestEventEnd = event.getWhen().end();
        }
      } 
    }
    
    // If there is still time between the last meeting and the end of the day, add the time range
    if (rangeLessThanDuration(TimeRange.fromStartEnd(latestEventEnd, TimeRange.END_OF_DAY, false), request.getDuration())) {
      acceptableMeetingTimes.add(TimeRange.fromStartEnd(latestEventEnd, TimeRange.END_OF_DAY, true));
    }
  
    
    // If the first acceptable time range is zero, check to see if it is the only timerange in the list 
    if (acceptableMeetingTimes.get(0).duration() == TimeRange.fromStartEnd(0, 0, false).duration()) {
      if (acceptableMeetingTimes.size() > 1) {
        acceptableMeetingTimes.remove(0); 
      } else {
        acceptableMeetingTimes.clear();
      } 
    }
    
    return acceptableMeetingTimes; 
  }
  
  // Check if the meeting duration fits within a given time range
  private static boolean rangeLessThanDuration(TimeRange range, long meetingDuration) {
    return (range.duration() >= meetingDuration);
  }
}
