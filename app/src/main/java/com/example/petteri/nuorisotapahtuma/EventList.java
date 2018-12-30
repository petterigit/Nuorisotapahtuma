package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    // The array for the Event-objects

    private static EventList instance = new EventList();
    private ArrayList<Event> event_list = new ArrayList<Event>();

    public ArrayList<Event> getEvent_list() {
        return event_list;
    }

    public void removeEvent(int index) {
        event_list.remove(index);
    }

    public static EventList getInstance () {
        return instance;
    }

    public void createEvent(String name, String begins, String ends, String place, String date, String info, int agesFrom, int agesTo) {
        Event new_event = new Event(name, begins, ends, place, date, info, agesFrom, agesTo);
        event_list.add(new_event);
    }

    public Event getEvent(int i) {
        return event_list.get(i);
    }

    public void printEvents() {
        for (int i = 0; i < event_list.size(); i++) {
            Event e = event_list.get(i);
            System.out.println("Event " + i + ": " + e.getName());
        }

    }


}
