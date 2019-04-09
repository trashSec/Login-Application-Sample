package com.example.trash.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button regButton, clear, closeButton;
    EditText nameID, surnameID, userNameID, pwID, pwCopyID;
    TextView regText;
    Intent i;

    final String SAVED_NAME = "NAME";
    final String SAVED_SNAME = "SURNAME";
    final String SAVED_USERNAME = "USERNAME";
    final String SAVED_PASS = "PASS";
    final String PREF = "PREF";

    String Name, Surname, Username, Password, PwCopy;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        i = new Intent(RegisterActivity.this, MainActivity.class);

        regText = (TextView) findViewById(R.id.regTextId);
        regButton = (Button) findViewById(R.id.regButton);
        clear = (Button) findViewById(R.id.ClButton);
        closeButton = (Button) findViewById(R.id.closeButton);
        nameID = (EditText) findViewById(R.id.nameID);
        surnameID = (EditText) findViewById(R.id.surnameID);
        userNameID = (EditText) findViewById(R.id.userNameID);
        pwID = (EditText) findViewById(R.id.pwID);
        pwCopyID = (EditText) findViewById(R.id.pwCopyID);

        View.OnClickListener cL1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.regButton) {
                    if (nameID.getText().length() > 0 && surnameID.getText().length() > 0 && userNameID.getText().length() > 0 && pwID.getText().length() > 0 && pwCopyID.getText().length() > 0) {

                        Name = nameID.getText().toString();
                        Surname = surnameID.getText().toString();
                        Username = userNameID.getText().toString();
                        Password = pwID.getText().toString();
                        PwCopy = pwCopyID.getText().toString();

                        saveData();

                        regText.setTextColor(getResources().getColor(R.color.colorGreen));
                        regText.setText(getString(R.string.successReg));
                    } else if (nameID.getText().length() == 0 && surnameID.getText().length() == 0 && userNameID.getText().length() == 0 && pwID.getText().length() == 0 && pwCopyID.getText().length() == 0) {
                        regText.setTextColor(getResources().getColor(R.color.colorRed));
                        regText.setText(R.string.regError);
                    }
                }
                if (v.getId() == R.id.ClButton) {
                    if (nameID.getText().toString().equals("") && surnameID.getText().toString().equals("") && pwCopyID.getText().toString().equals("") && pwID.getText().toString().equals("") && userNameID.getText().toString().equals("")) {
                        Toast.makeText(RegisterActivity.this, R.string.clearFields, Toast.LENGTH_SHORT).show();
                    } else {
                        nameID.setText(null);
                        surnameID.setText(null);
                        userNameID.setText(null);
                        pwID.setText(null);
                        pwCopyID.setText(null);
                        Toast.makeText(RegisterActivity.this, R.string.clearedText, Toast.LENGTH_SHORT).show();
                    }
                }
                if (v.getId() == R.id.closeButton) {
                    finish();
                }
            }
        };
        regButton.setOnClickListener(cL1);
        clear.setOnClickListener(cL1);
        closeButton.setOnClickListener(cL1);
    }

    void saveData() {
        sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_NAME, Name);
        editor.putString(SAVED_SNAME, Surname);
        editor.putString(SAVED_USERNAME, Username);
        editor.putString(SAVED_PASS, Password);
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}
