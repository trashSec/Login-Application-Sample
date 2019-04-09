package com.example.trash.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    final String SAVED_NAME = "NAME";
    final String SAVED_SNAME = "SURNAME";
    final String SAVED_USERNAME = "USERNAME";
    final String SAVED_PASS = "PASS";
    final String PREF = "PREF";

    private TextView AppText;
    private EditText LogIn, PassWord;
    private Button logInButton, clearButton, endButton;
    private TextInputLayout addLoginLabel, addPassWordLabel;

    private String Login, Password, Name, Surname;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findElements();

        changeText();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
                String savedUsername = sharedPreferences.getString(SAVED_USERNAME, "username");
                String savedPass = sharedPreferences.getString(SAVED_PASS, "password");
                Login = savedUsername;
                Password = savedPass;
                Toast.makeText(LoginActivity.this, "Text loaded", Toast.LENGTH_SHORT).show();
                if (LogIn.getText().toString().equals(Login) && PassWord.getText().toString().equals(Password)) {
                    String savedName = sharedPreferences.getString(SAVED_NAME, "name");
                    String savedSurname = sharedPreferences.getString(SAVED_SNAME, "surname");
                    Name = savedName;
                    Surname = savedSurname;
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
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void findElements() {
        AppText = findViewById(R.id.WelcomeText);
        LogIn = findViewById(R.id.LogIn);
        PassWord = findViewById(R.id.PassWord);
        logInButton = findViewById(R.id.LogInButton);
        clearButton = findViewById(R.id.clearButton);
        endButton = findViewById(R.id.endButton);
        addLoginLabel = findViewById(R.id.add_login_label);
        addPassWordLabel = findViewById(R.id.add_pw_label);
    }

    private void submitForm() {
        if (!validateLogIn()) {
            return;
        }
        if (!validatePw()) {
            return;
        }
    }

    void changeText() {
        LogIn.addTextChangedListener(new MyTextWatcher(LogIn));
        PassWord.addTextChangedListener(new MyTextWatcher(PassWord));
    }

    private boolean validateLogIn() {
        if (LogIn.getText().toString().trim().isEmpty()) {
            addLoginLabel.setError("This field is required");
            requestFocus(LogIn);
            return false;
        } else {
            addLoginLabel.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePw() {
        if (PassWord.getText().toString().trim().isEmpty()) {
            addPassWordLabel.setError("This field is required");
            requestFocus(PassWord);
            return false;
        } else {
            addPassWordLabel.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View mView;

        private MyTextWatcher(View view) {
            this.mView = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (mView.getId()) {
                case R.id.LogIn:
                    validateLogIn();
                    break;
                case R.id.PassWord:
                    validatePw();
                    break;
            }
        }
    }

}
