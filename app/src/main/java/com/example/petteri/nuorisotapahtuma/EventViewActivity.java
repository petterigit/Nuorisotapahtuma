package com.example.petteri.nuorisotapahtuma;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/* Event view activity to show and modify events info */

public class EventViewActivity extends AppCompatActivity {

    EditText editName;
    EditText editBegins;
    EditText editEnds;
    EditText editPlace;
    EditText editDate;
    EditText editInfo;
    EditText editAges;
    EditText editVisitors;
    int index;

    Intent intent;
    Event event;

    ListView fbListView;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);


        intent = getIntent();
        index = intent.getIntExtra("index", 0);

        event = EventList.getInstance().getEvent(index);

        editName = (EditText) findViewById(R.id.editText);
        editName.setText(event.getName());
        editBegins = (EditText) findViewById(R.id.editText2);
        editBegins.setText(event.getBegins());
        editEnds = (EditText) findViewById(R.id.editText3);
        editEnds.setText(event.getEnds ());
        editPlace = (EditText) findViewById(R.id.editText4);
        editPlace.setText(event.getPlace());
        editDate = (EditText) findViewById(R.id.editText5);
        editDate.setText(event.getDate());
        editInfo = (EditText) findViewById(R.id.editText6);
        editInfo.setText(event.getInfo());
        editAges = (EditText) findViewById(R.id.editText7);
        editAges.setText(event.getAges());
        editVisitors = (EditText) findViewById(R.id.editText8);
        editVisitors.setText(event.getVisitors());



        fbListView = (ListView) findViewById(R.id.feedBackListView);
        ArrayList<FeedBack> feedBackList = event.getFeedBackList().getFeedBack_list();

        /* Set list items using ArrayAdapter */
        ArrayAdapter<FeedBack> adapter_t = new ArrayAdapter<FeedBack>(this,
                android.R.layout.simple_expandable_list_item_1, feedBackList);
        adapter_t.setDropDownViewResource(android.R.layout.simple_list_item_1);
        fbListView.setAdapter(adapter_t);

        /* OnClickListener set, but not used */
        fbListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter_t, View v, int position, long id) {
            }
        });



    }

    /* Saves info and starts EventActivity */
    public void saveEvent(View v) {
        Event event = EventList.getInstance().getEvent(index);

        event.setName(editName.getText().toString());
        event.setBegins(editBegins.getText().toString());
        event.setEnds(editEnds.getText().toString());
        event.setPlace(editPlace.getText().toString());
        event.setDate(editDate.getText().toString());
        event.setInfo(editInfo.getText().toString());
        event.setAges(editAges.getText().toString());
        event.setVisitors(editVisitors.getText().toString());
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);

    }

    /* Starts EventActivity without saving */
    public void back(View v) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }


}
