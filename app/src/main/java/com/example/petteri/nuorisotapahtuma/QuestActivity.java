package com.example.petteri.nuorisotapahtuma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
        eventlist.createEvent("Junnukertsi", "18:00", "22:00", "Ahjola", "Kivaa yhdessäoloa");

        eventTextView.setText(eventlist.getEvent(0).getName());
        beginsTextView.setText(eventlist.getEvent(0).getBegins());
        endsTextView.setText(eventlist.getEvent(0).getEnds());
        placeTextView.setText(eventlist.getEvent(0).getPlace());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 2, "New_item");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_id1:
                System.out.println("Logout");
                return true;
            case R.id.menu_id2:
                System.out.println("Settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        String info = eventlist.getEvent(0).getInfo();
        ((TextView)popupWindow.getContentView().findViewById(R.id.textViewInfo)).setText(info);

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
