package com.example.tftsupport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ParentModel[] pDataset;
    private RecyclerView.RecycledViewPool viewPool;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public MyViewHolder(View itemView) {
            RecyclerView recyclerView = itemView.findViewById(R.id.rv_child);
            TextView textView = itemView.textView;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(ParentModel[] myDataset) {
        pDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.champion_textview, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ParentModel parent = pDataset[position];
        holder.textView.setText(pDataset[position]);
        RecyclerView.LayoutManager childLayoutManager = LinearLayoutManager.getChildMeasureSpec(holder.recyclerView.context, LinearLayout.HORIZONTAL, false);
        childLayoutManager.initialPrefetchItemCount = 4;

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = ChildAdapter(parent.children)
            setRecycledViewPool(viewPool)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pDataset.length;
    }
}