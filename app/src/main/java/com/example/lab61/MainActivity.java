package com.example.lab61;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout body = findViewById(R.id.body);
        for (int i = 0; i < 12; i++) {
            body.addView(new QuestionView(this) {});
        }
    }
}
