package com.example.tftsupport;

import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class ItemChildAdapter extends RecyclerView.Adapter<ItemChildAdapter.ViewHolder>{
    private List<ItemChildModel> cDataset;

    public ItemChildAdapter(List<ItemChildModel> myDataset) {
        cDataset = myDataset;
    }

    @Override
    public ItemChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_child_recycler, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemChildModel child = cDataset.get(position);
        holder.imageView.setImageResource(child.getItem());
        holder.imageViewComponent1.setImageResource(child.getComponent1());
        holder.imageViewComponent2.setImageResource(child.getComponent2());
        holder.textView.setText(child.getName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private ImageView imageViewComponent1;
        private ImageView imageViewComponent2;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_completeditem_item);
            textView = itemView.findViewById(R.id.textview_completeditem_item);
            imageViewComponent1 = itemView.findViewById(R.id.imageview_component1_item);
            imageViewComponent2 = itemView.findViewById(R.id.imageview_component2_item);
        }
    }

    @Override
    public int getItemCount() {
        return cDataset.size();
    }
}