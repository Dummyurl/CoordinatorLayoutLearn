package com.superman.coordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.system_coordinator).setOnClickListener(this);
        findViewById(R.id.img_coordinator).setOnClickListener(this);
        findViewById(R.id.tab_coordinator).setOnClickListener(this);
        findViewById(R.id.bilibili_coordinator).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.system_coordinator:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            case R.id.img_coordinator:
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case R.id.tab_coordinator:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.bilibili_coordinator:
                startActivity(new Intent(this, BilibiliActivity.class));
                break;
        }
    }
}
