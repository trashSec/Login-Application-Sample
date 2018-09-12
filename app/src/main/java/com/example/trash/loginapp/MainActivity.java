package com.example.trash.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button LogIn;
    Button Regist;
    ImageView WelcomeImage;
    String Name, Surname, Username, Password;
    Intent LogAct, regAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = getIntent().getStringExtra("username");
        Password = getIntent().getStringExtra("password");
        Name = getIntent().getStringExtra("name");
        Surname = getIntent().getStringExtra("surname");

        LogIn = (Button) findViewById(R.id.logButton);
        Regist = (Button) findViewById(R.id.registButton);
        WelcomeImage = (ImageView) findViewById(R.id.WelcomeImage);

        View.OnClickListener Cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.logButton) {
                    LogAct = new Intent(MainActivity.this, LoginActivity.class);
                    LogAct.putExtra("name", Name);
                    LogAct.putExtra("surname", Surname);
                    LogAct.putExtra("username", Username);
                    LogAct.putExtra("password", Password);
                    startActivity(LogAct);
                }
                if (v.getId() == R.id.registButton){
                    regAct = new Intent (MainActivity.this, RegisterActivity.class);
                    startActivity(regAct);
                }
            }
        };
        LogIn.setOnClickListener(Cl);
        Regist.setOnClickListener(Cl);
    }
}
