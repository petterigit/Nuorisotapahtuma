package com.example.petteri.nuorisotapahtuma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    ListView eventListView;
    int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventListView = (ListView) findViewById(R.id.listViewEvent);

        ArrayList<Event> event_list = EventList.getInstance().getEvent_list();

        ArrayAdapter<Event> adapter_t = new ArrayAdapter<Event>(this,
                android.R.layout.simple_spinner_item, event_list);
        adapter_t.setDropDownViewResource(android.R.layout.simple_list_item_1);
        eventListView.setAdapter(adapter_t);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter_t, View v, int position, long id) {
                selectedIndex = position;
            }
        });
    }


    public void refresh() {
        ((ArrayAdapter) eventListView.getAdapter()).notifyDataSetChanged();
    }

    public void removeEvent(View v) {
        EventList.getInstance().removeEvent(selectedIndex);
        refresh();
    }

    public void addEvent(View asd) {

    }
}
