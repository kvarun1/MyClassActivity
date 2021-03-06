package com.mbinfo.myclassactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;

import com.mbinfo.adapter.MyAdapter;
import com.mbinfo.model.GridItem;

import java.util.ArrayList;

public class Sanskrit extends AppCompatActivity {

    GridView simpleList;
    ArrayList<GridItem> nameList = new ArrayList<GridItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanskrit);
        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorHeaderText));
        // set up rid
        simpleList = findViewById(R.id.simpleGridView);
        nameList.add(new GridItem("संस्कृत श्लोक"));
        nameList.add(new GridItem("संस्कृत श्लोक"));
        nameList.add(new GridItem("संस्कृत श्लोक"));
        nameList.add(new GridItem("संस्कृत श्लोक"));
        MyAdapter myAdapter=new MyAdapter(this,nameList);
        simpleList.setAdapter(myAdapter);
    }
}