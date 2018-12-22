package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    // The array for the Event-objects

    private static EventList instance = new EventList();
    private List<Event> event_list = new ArrayList<Event>();

    public List<Event> getEvent_list() {
        return event_list;
    }

    public void removeEvent(int bottle_nr) {
        event_list.remove(bottle_nr);
    }

    public static EventList getInstance () {
        return instance;
    }
    public void createEvent(String name, String begins, String ends, String place, String info) {
        Event new_event = new Event();
        new_event.setName(name);
        new_event.setBegins(begins);
        new_event.setEnds(ends);
        new_event.setPlace(place);
        new_event.setInfo(info);
        event_list.add(new_event);
    }

    public Event getEvent(int i) {
        return event_list.get(i);
    }


}
