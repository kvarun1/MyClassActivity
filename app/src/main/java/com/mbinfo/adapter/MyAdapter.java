package com.mbinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mbinfo.model.GridItem;
import com.mbinfo.myclassactivity.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter <GridItem>{

    List<GridItem> nameList = new ArrayList<>();

    public MyAdapter(@NonNull Context context, ArrayList<GridItem> resource) {
        super(context,0, resource);
        this.nameList = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null){
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        GridItem courseModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.message_text_view);
        courseTV.setText(courseModel.getName());
        return listitemView;
    }
}
