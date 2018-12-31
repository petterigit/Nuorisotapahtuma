package com.example.petteri.nuorisotapahtuma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/* Event listing activity */
public class EventActivity extends AppCompatActivity {

    ListView eventListView;

    /* Keeps track of selected item */
    int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventListView = (ListView) findViewById(R.id.listViewEvent);


        ArrayList<Event> event_list = EventList.getInstance().getEvent_list();

        /* Set list view items using ArrayAdapter */
        ArrayAdapter<Event> adapter_t = new ArrayAdapter<Event>(this,
                android.R.layout.simple_spinner_item, event_list);
        adapter_t.setDropDownViewResource(android.R.layout.simple_list_item_1);
        eventListView.setAdapter(adapter_t);

        /* Set OnClickListener for when an item is clicked
         * Updates selected Index to keep track of item */
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter_t, View v, int position, long id) {
                selectedIndex = position;
            }
        });
    }

    /* Refresh event list */
    public void refresh() {
        ((ArrayAdapter) eventListView.getAdapter()).notifyDataSetChanged();
    }

    /* Remove event and refresh list */
    public void removeEvent(View v) {
        EventList.getInstance().removeEvent(selectedIndex);
        refresh();
    }

    /* Add a blank event and refresh list */
    public void addEvent(View v) {
        EventList.getInstance().createEvent("Uusi tapahtuma", "", "",
                "", "", "", "", "");
        refresh();
    }

    /* Set event index to zero, thus making it active */
    public void startEvent(View v) {
        EventList.getInstance().setIndexZero(selectedIndex);
    }

    /* Launch event modify activity
    *  Pass index to intent with putExtra()*/
    public void launchEventView(View v) {
        Intent intent = new Intent(this, EventViewActivity.class);
        intent.putExtra("index", selectedIndex);
        startActivity(intent);
    }
}
