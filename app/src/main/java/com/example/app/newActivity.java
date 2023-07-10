package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class newActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new DataFragment()).commit();
    }
}