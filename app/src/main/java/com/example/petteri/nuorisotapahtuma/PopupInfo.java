package com.example.petteri.nuorisotapahtuma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/* Activity for popup screen */
public class PopupInfo extends AppCompatActivity {

    TextView popupText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_info);

    }
}
