package com.mbinfo.myclassactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mbinfo.database.DatabaseHelper;
import com.mbinfo.util.Validation;

import java.io.File;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView signup;
    Button login;
    DatabaseHelper databaseHelper;
    Validation validation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.newuser);
        validation = new Validation();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation.isEmpty(username) && validation.isEmpty(password)){
                    username.setError("username can not be empty");
                    password.setError("password can not be empty");
                    new downaloadFile();

                }
              /*  if (username.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }*/
                // check user from database
                if (databaseHelper.checkUser(username.getText().toString().trim(), password.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeScreen.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();

                }
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(i);
            }
        });

    }

    private class downaloadFile implements Runnable{

        @Override
        public void run() {


        }
    }
}