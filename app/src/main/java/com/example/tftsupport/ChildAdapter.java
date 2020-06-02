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

class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{
    private List<ChildModel> cDataset;

    public ChildAdapter(List<ChildModel> myDataset) {
        cDataset = myDataset;
    }

    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v =  LayoutInflater.from(parent.getContext())
        .inflate(R.layout.child_recycler, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildModel child = cDataset.get(position);
        holder.imageView.setImageResource(child.getImage());
        holder.textView.setText("" + child.getCost());
        if (holder.textView.getText().equals("2")){
            holder.textView.setTextColor(Color.parseColor("#54d670"));
        }
        else if (holder.textView.getText().equals("3")){
            holder.textView.setTextColor(Color.parseColor("#4d9fe8"));
        }
        else if (holder.textView.getText().equals("4")){
            holder.textView.setTextColor(Color.parseColor("#ca4ad4"));
        }
        else if (holder.textView.getText().equals("5")){
            holder.textView.setTextColor(Color.parseColor("#f2d91d"));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.child_imageView);
            textView = itemView.findViewById(R.id.textViewChild);
        }
    }

    @Override
    public int getItemCount() {
        return cDataset.size();
    }
}