package com.example.tftsupport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.MyViewHolder> {
    private List<ItemParentModel> pDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView textView;
        private ImageView imageView;

        public MyViewHolder( final View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_child_item);
            textView = itemView.findViewById(R.id.component_textview);
            imageView = itemView.findViewById(R.id.main_component_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemRecyclerAdapter(List<ItemParentModel> myDataset) {
        pDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        final View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parent_recycler, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        ItemParentModel parent = pDataset.get(position);

        holder.recyclerView.setAdapter(new ItemChildAdapter(parent.getListItem()));

        RecyclerView.LayoutManager childLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), RecyclerView.VERTICAL, false);
        holder.recyclerView.setLayoutManager(childLayoutManager);

        holder.textView.setText(parent.getName());
        holder.imageView.setImageResource(parent.getImage());

    }

    @Override
    public int getItemCount() {
        return pDataset.size();
    }
    // Return the size of your dataset (invoked by the layout manager)

}