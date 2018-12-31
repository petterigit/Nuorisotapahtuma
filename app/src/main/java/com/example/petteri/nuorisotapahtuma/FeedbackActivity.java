package com.example.petteri.nuorisotapahtuma;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    FeedBackList feedbacklist;
    EditText feedbackEditText;
    TextView feedbackTextView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = (EditText) findViewById(R.id.editTextFB);
        feedbackTextView = (TextView) findViewById(R.id.textViewFB);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void addFeedback(View fb) {
        feedbacklist = EventList.getInstance().getEvent(0).getFeedBackList();

        String feedback_string = feedbackEditText.getText().toString();
        
        feedbacklist.createFeedBack(feedback_string, Float.toString(ratingBar.getRating()));
        finish();

    }
}
