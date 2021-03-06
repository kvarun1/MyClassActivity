package com.mbinfo.myclassactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mbinfo.fragment.Fragment_Two;

public class ClickToCall extends AppCompatActivity {
TextView datashow;
Button fragmentcall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_to_call);
        datashow = findViewById(R.id.textView);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("message");
       // int number = intent.getIntExtra("number");
        datashow.setText(data);
        fragmentcall = findViewById(R.id.button5);
        fragmentcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =  getSupportFragmentManager();
                FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
                  Bundle bundle = new Bundle();
                  bundle.putString("key",data);
                 Fragment_Two fragment_two = new Fragment_Two();
                 fragment_two.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_container,fragment_two);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                fragmentcall.setVisibility(View.GONE);
            }
        });


    }
    public static void show(){

    }
}