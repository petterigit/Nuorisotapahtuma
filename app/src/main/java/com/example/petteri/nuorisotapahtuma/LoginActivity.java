package com.example.petteri.nuorisotapahtuma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = (EditText) findViewById(R.id.editTextUname);
        passwordText = (EditText) findViewById(R.id.editTextPasswd);

    }

    public void launchMainActivity(View v) {
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }
}
