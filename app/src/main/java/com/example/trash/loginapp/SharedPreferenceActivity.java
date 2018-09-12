package com.example.trash.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferenceActivity extends AppCompatActivity {

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref = sharedPreferences.getString(key, "");
    }
}
