package com.example.petteri.nuorisotapahtuma;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.StrictMode;
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

import java.io.File;

/* Main view for the application */
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

    static User user;

    /* Assigns Events as second, and Admin as third option
    *  Logout given in xml
     */
    private static final int MENU_EVENTS = Menu.FIRST + 2;
    private static final int MENU_ADMIN = Menu.FIRST + 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this.getApplicationContext();

        eventTextView = (TextView) findViewById(R.id.textViewEventName);
        beginsTextView = (TextView) findViewById(R.id.textViewBegins);
        endsTextView = (TextView) findViewById(R.id.textViewEnds);
        placeTextView = (TextView) findViewById(R.id.textViewPlace);

        infoButton = (Button) findViewById(R.id.buttonInfo);
        infoPopup = new PopupWindow(this);

        /* Sets user depending on given username
         * Could've been done in login activity, since user was
          * later set static */
        intent = getIntent();
        if ((intent.getStringExtra("user"))!=null) {
            String username = intent.getStringExtra("user");
            if (username.equals("Guest")) {
                user = new GuestUser();
            } else if (username.equals("Super")) {
                user = new SuperUser();
            } else if (username.equals("Admin")) {
                user = new AdminUser();
            }
        }

        eventlist = EventList.getInstance();

        /* Initialize xml files */
        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.writeXML(context);

        /* ReadXML may not work properly, if not events are created with constructor */
        if (!(eventlist.getInstance().getEvent_list().isEmpty())) {
            xmlHandler.readXML(context);
        }

        //eventlist.printEvents();

        eventTextView.setText(eventlist.getEvent(0).getName());
        beginsTextView.setText(eventlist.getEvent(0).getBegins() + " - " + eventlist.getEvent(0).getEnds());
        endsTextView.setText("Iät: " + eventlist.getEvent(0).getAges());
        placeTextView.setText(eventlist.getEvent(0).getPlace());




    }

    /* Creates options menu with items matching user permissions */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if (user.name.equals("Guest")) {
            // Do nothing
        }
        if (user.name.equals("Super")) {
            // Guest+Super user menu items
            menu.add(0, MENU_EVENTS, Menu.NONE, "Events");
        }
        if (user.name.equals("Admin")) {
            // Guest+Super+Admin user menu items
            menu.add(0, MENU_EVENTS, Menu.NONE, "Events");
            menu.add(0, MENU_ADMIN, Menu.NONE, "Example menu item");
        }

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    /* Sets functions for different items */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_id1:
                System.out.println("Logout");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case MENU_EVENTS:
                System.out.println("Events");
                launchEvents();
            case MENU_ADMIN:
                System.out.println("Example menu item for admin user");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchFeedback(View main) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void launchEvents() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    /* Function to show info popup
     * Done with example from:
     * https://stackoverflow.com/a/50188704
     */
    public void showInfo(View main) {

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
