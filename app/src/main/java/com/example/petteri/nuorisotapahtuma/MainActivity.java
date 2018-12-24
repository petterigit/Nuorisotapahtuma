package com.example.petteri.nuorisotapahtuma;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {


    Event current_event;
    EventList eventlist;
    TextView eventTextView;
    TextView beginsTextView;
    TextView endsTextView;
    TextView placeTextView;

    PopupWindow infoPopup;
    Button infoButton;

    Context context;

    Intent intent;

    User user;

    private static final int MENU_NEWITEM = Menu.FIRST + 2;
    private static final int MENU_NEWITEM2 = Menu.FIRST + 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventTextView = (TextView) findViewById(R.id.textViewEventName);
        beginsTextView = (TextView) findViewById(R.id.textViewBegins);
        endsTextView = (TextView) findViewById(R.id.textViewEnds);
        placeTextView = (TextView) findViewById(R.id.textViewPlace);

        infoButton = (Button) findViewById(R.id.buttonInfo);
        infoPopup = new PopupWindow(this);

        intent = getIntent();
        String username = intent.getStringExtra("user");
        if (username.equals("Guest")) {
            user = new GuestUser();
        } else if (username.equals("Super")) {
            user = new SuperUser();
        } else if (username.equals("Admin")) {
            user = new AdminUser();
        }

        eventlist = EventList.getInstance();
        eventlist.createEvent("Junnukertsi", "18:00", "22:00", "Ahjola",
                "24/12", "Kivaa yhdessäoloa", 0, 0);

        eventTextView.setText(eventlist.getEvent(0).getName());
        beginsTextView.setText(eventlist.getEvent(0).getBegins());
        endsTextView.setText(eventlist.getEvent(0).getEnds());
        placeTextView.setText(eventlist.getEvent(0).getPlace());

        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.CreateXMLFileJava();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if (user.name.equals("Guest")) {
            // Do nothing
        }
        if (user.name.equals("Super")) {
            // Guest+Super user menu items
            menu.add(0, MENU_NEWITEM, Menu.NONE, "Text1");
        }
        if (user.name.equals("Admin")) {
            // Guest+Super+Admin user menu items
            menu.add(0, MENU_NEWITEM, Menu.NONE, "Text1");
            menu.add(0, MENU_NEWITEM2, Menu.NONE, "Text2");
        }

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
            case MENU_NEWITEM:
                System.out.println("New item");
            case MENU_NEWITEM2:
                System.out.println("New item 2");
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void launchFeedback(View main) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void showInfo(View main) {
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
        popupWindow.showAtLocation(main, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View main, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
