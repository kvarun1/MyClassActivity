package com.mbinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbinfo.model.Item;
import com.mbinfo.myclassactivity.HomeScreen;
import com.mbinfo.myclassactivity.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> mList;
     Context mContext;

    public ItemAdapter(List<Item> list, Context context) {
        super();
        mList = list;
        mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((ViewHolder) viewHolder).mTv_name.setText(mList.get(position).getName());
       ((ViewHolder) viewHolder).mImg.setImageResource(mList.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv_name;
        public ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv_name = (TextView) itemView.findViewById(R.id.tv_name);
            mImg = (ImageView) itemView.findViewById(R.id.img_item);
        }

    }
}
