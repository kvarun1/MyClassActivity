package com.mbinfo.myclassactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.mbinfo.adapter.ItemAdapter;
import com.mbinfo.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Item> mList = new ArrayList<>();
    private ItemAdapter mAdapter;
   // Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initLayout();

    }
    private void initLayout(){
        recyclerView =  findViewById(R.id.recl_view);
        recyclerView.setHasFixedSize(true);
//set a vertical layout so the list is displayed top down
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
         Item  item = new Item();
        item.setImage(R.drawable.tomato);
        item.setName("Tomato");
        mList.add(item);
        item = new Item();
        item.setImage(R.drawable.apple);
        item.setName("Apple");
        mList.add(item);
        item = new Item();
       item.setImage(R.drawable.wheat);
        item.setName("Wheat");
        mList.add(item);
        item = new Item();
        item.setImage(R.drawable.arhaer);
        item.setName("Arhar");
        mList.add(item);
        item = new Item();
        item.setImage(R.drawable.gram);
        item.setName("Gram");
        mAdapter = new ItemAdapter(mList,HomeScreen.this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(HomeScreen.this,MainActivity.class);
        startActivity(i);
        finish();

    }
}