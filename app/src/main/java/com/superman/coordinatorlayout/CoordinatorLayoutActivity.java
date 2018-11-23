package com.superman.coordinatorlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
