package com.android.tmsoneprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.tmsoneprototype.ui.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivity mainActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(mainActivity, HomeActivity.class);
        startActivity(intent);
    }

}