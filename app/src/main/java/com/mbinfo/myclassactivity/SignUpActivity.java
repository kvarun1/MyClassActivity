package com.mbinfo.myclassactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mbinfo.User;
import com.mbinfo.database.DatabaseHelper;
import com.mbinfo.util.Validation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText username,password,phone;
    Button signup;
    DatabaseHelper databaseHelper;
    User user;
    Validation validation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.button);
        validation = new Validation();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 databaseHelper =  new DatabaseHelper(SignUpActivity.this);
                 user = new User();
                 String name =  username.getText().toString();
                String userpwd = password.getText().toString();
                String mobile = phone.getText().toString();
                if(validation.isEmpty(username) && (validation.isEmpty(password) && (validation.isEmpty(phone)))){
                    username.setError("username can not be empty");
                    password.setError("password can not be empty");
                    phone.setError("phone can not be empty");

                }else if(validation.isValidMobile(mobile) && mobile.length() == 10) {
                    phone.setError("Use Correct mobile");
                    phone.setError("phone should be length 10 digit");

                } else {
                    user.setUsername(name);
                    user.setPassword(userpwd);
                    user.setPhone(mobile);
                    databaseHelper.addUser(user);
                    Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(i);
                    Toast.makeText(SignUpActivity.this,"User has Registerted",Toast.LENGTH_LONG).show();
                }
               // user.setPassword(userpwd);
              //  user.setPhone(mobile);





            }
        });

    }
}
