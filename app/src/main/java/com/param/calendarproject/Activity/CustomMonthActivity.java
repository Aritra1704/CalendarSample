package com.param.calendarproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.param.calendarproject.R;

/**
 * Created by Aritra on 5/2/2016.
 */
public class CustomMonthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custommonthcalendar);
    }
}
