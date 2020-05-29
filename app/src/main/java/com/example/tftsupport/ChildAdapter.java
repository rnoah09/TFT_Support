package com.example.tftsupport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{
    private ChildModel[] cDataset;

    public ChildAdapter(ChildModel[] myDataset) {
        cDataset = myDataset;
    }

    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v =  LayoutInflater.from(parent.getContext())
        .inflate(R.layout.child_recycler,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return cDataset.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildModel child = cDataset[position];
        holder.imageView.setImageResource(child.image);
        }


        public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView){
            ImageView imageView = itemView.child_imageView;
        }
        }
}