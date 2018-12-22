package com.example.petteri.nuorisotapahtuma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    FeedBackList feedbacklist;
    EditText feedbackEditText;
    TextView feedbackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = (EditText) findViewById(R.id.editTextFB);
        feedbackTextView = (TextView) findViewById(R.id.textViewFB);
    }

    public void addFeedback(View fb) {
        feedbacklist = FeedBackList.getInstance();
        String feedback_string = feedbackEditText.getText().toString();
        feedbacklist.createFeedBack(feedback_string);
        feedbackTextView.setText(feedback_string);
    }
}
