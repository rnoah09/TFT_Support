package com.example.tftsupport;

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
        cDataset = new ArrayList<>();
        Log.e("childadapter", "oncreate ");
        View v =  LayoutInflater.from(parent.getContext())
        .inflate(R.layout.child_recycler, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildModel child = cDataset.get(position);
        Log.e("childadapter", "ViewHolder: " + child.getImage());
        holder.imageView.setImageResource(child.getImage());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.child_imageView);
        }
    }

    @Override
    public int getItemCount() {
        return cDataset.size();
    }
}