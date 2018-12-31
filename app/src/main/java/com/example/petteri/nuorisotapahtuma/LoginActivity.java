package com.example.petteri.nuorisotapahtuma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* Login activity to handle different user permissions */

public class LoginActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    String usernameText_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = (EditText) findViewById(R.id.editTextUname);
        passwordText = (EditText) findViewById(R.id.editTextPasswd);

    }

    public void launchMainActivity(View v) {

        Intent intent = new Intent(this, MainActivity.class);

        usernameText_s = usernameText.getText().toString();
        System.out.println("usernameText_x: " + usernameText_s);

        /* Launches main activity and passes given username with putExtra
        *  Prints to stdout if username is wrong */
        if (usernameText_s.equals("Guest")) {
            intent.putExtra("user", "Guest");
            startActivity(intent);
        } else if (usernameText_s.equals("Super")) {
            //super
            intent.putExtra("user", "Super");
            startActivity(intent);
        } else if (usernameText_s.equals("Admin")) {
            //admin
            intent.putExtra("user", "Admin");
            startActivity(intent);
        } else {
            System.out.println("User not found; Type Guest / Super / Admin");
        }
    }
}
