package com.example.lab61;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class QuestionView extends LinearLayout {
    public QuestionView(Context context) {
        super(context);

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.question, this, true);
    }
}
