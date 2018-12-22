package com.example.petteri.nuorisotapahtuma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestActivity extends AppCompatActivity {


    Event current_event;
    EventList eventlist;
    TextView eventTextView;
    TextView beginsTextView;
    TextView endsTextView;
    TextView placeTextView;

    PopupWindow infoPopup;
    Button infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        eventTextView = (TextView) findViewById(R.id.textViewEventName);
        beginsTextView = (TextView) findViewById(R.id.textViewBegins);
        endsTextView = (TextView) findViewById(R.id.textViewEnds);
        placeTextView = (TextView) findViewById(R.id.textViewPlace);

        infoButton = (Button) findViewById(R.id.buttonInfo);
        infoPopup = new PopupWindow(this);


        eventlist = EventList.getInstance();
        eventlist.createEvent("Junnukertsi", "18:00", "22:00", "Ahjola");

        eventTextView.setText(eventlist.getEvent(0).getName());
        beginsTextView.setText(eventlist.getEvent(0).getBegins());
        endsTextView.setText(eventlist.getEvent(0).getEnds());
        placeTextView.setText(eventlist.getEvent(0).getPlace());
    }

    public void launchFeedback(View quest) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void showInfo(View quest) {
        /* From: https://stackoverflow.com/a/50188704 */
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_info, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(quest, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View quest, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
