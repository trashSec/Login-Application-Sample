package com.example.trash.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    final String SAVED_USERNAME = "USERNAME";
    final String SAVED_PASS = "PASS";
    final String PREF = "PREF";

    TextView AppText;
    EditText LogIn, PassWord;
    Button logInButton, clearButton, endButton;
    String Login, Password, Name, Surname;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findElements();

        View.OnClickListener Cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.LogInButton) {
                    LoginActivity.this.getSharedPreferences(PREF, MODE_PRIVATE);
                    String savedUsername = sharedPreferences.getString(SAVED_USERNAME, "");
                    String savedPass = sharedPreferences.getString(SAVED_PASS, "");
                    Login = savedUsername;
                    Password = savedPass;
                    Toast.makeText(LoginActivity.this, "Text loaded", Toast.LENGTH_SHORT).show();
                    if (LogIn.getText().toString().equals(Login) && PassWord.getText().toString().equals(Password)) {
                        AppText.setText(getString(R.string.welcome) + " " + Name + " " + Surname);
                        AppText.setTextColor(getResources().getColor(R.color.colorGreen));
                    } else {
                        AppText.setText(getString(R.string.missText));
                        AppText.setTextColor(getResources().getColor(R.color.colorRed));
                        LogIn.setText(null);
                        PassWord.setText(null);
                        Toast.makeText(LoginActivity.this, R.string.clearedText, Toast.LENGTH_SHORT).show();
                    }
                }
                if (v.getId() == R.id.clearButton) {
                    if (LogIn.getText().toString().equals("") && PassWord.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, R.string.clearFields, Toast.LENGTH_SHORT).show();
                    } else {
                        AppText.setText(getResources().getString(R.string.app_text));
                        AppText.setTextColor(getResources().getColor(R.color.colorBlue));
                        LogIn.setText(null);
                        PassWord.setText(null);
                        Toast.makeText(LoginActivity.this, R.string.clearedText, Toast.LENGTH_SHORT).show();
                    }

                }
                if (v.getId() == R.id.endButton) {
                    finish();
                }
            }
        };
        logInButton.setOnClickListener(Cl);
        clearButton.setOnClickListener(Cl);
        endButton.setOnClickListener(Cl);
    }

    void findElements() {
        AppText = findViewById(R.id.WelcomeText);
        LogIn = findViewById(R.id.LogIn);
        PassWord = findViewById(R.id.PassWord);
        logInButton = findViewById(R.id.LogInButton);
        clearButton = findViewById(R.id.clearButton);
        endButton = findViewById(R.id.endButton);
    }
}
