package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;

/* Class for singleton Event List */
public class EventList {



    /* Initialize 2 events as an example */
    private EventList() {
        Event newEvent1 = new Event("Junnukertsi Aamu", "12:00", "16:00", "Ahjola",
                "24/12", "Kivaa yhdessäoloa", "6-10", "404");
        event_list.add(newEvent1);

        Event newEvent2 = new Event("Junnukertsi Ilta", "18:00", "22:00", "Ahjola",
                "24/12", "Kivaa yhdessäoloa", "10-16", "12");
        event_list.add(newEvent2);
    }


    private static EventList instance = new EventList();
    private ArrayList<Event> event_list = new ArrayList<Event>();

    /* Reguired class call methods for singleton */
    public ArrayList<Event> getEvent_list() {
        return event_list;
    }
    public static EventList getInstance () {
        return instance;
    }

    /* Remove and create constructor */
    public void removeEvent(int index) {
        event_list.remove(index);
    }
    public void createEvent(String name, String begins, String ends, String place, String date, String info, String ages, String visitors) {
        Event new_event = new Event(name, begins, ends, place, date, info, ages, visitors);
        event_list.add(new_event);
    }

    /* Sets given index to zero, and swaps zero element
     * Element 3 -> Element 0
     * Element 0 -> Element 3
     */
    public void setIndexZero(int Index) {
        Event event = event_list.get(Index);
        event_list.set(event_list.indexOf(event), event_list.get(0));
        event_list.set(0, event);
    }

    /* Returns event at given index */
    public Event getEvent(int i) {
        return event_list.get(i);
    }

    /* Print events for debugging purposes */
    public void printEvents() {
        for (int i = 0; i < event_list.size(); i++) {
            Event e = event_list.get(i);
            System.out.println("Event " + i + ": " + e.getName());
        }

    }


}
