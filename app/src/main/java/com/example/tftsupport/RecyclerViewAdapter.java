package com.example.tftsupport;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<ParentModel> pDataset;
    private RecyclerView.RecycledViewPool viewPool;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView textView;

        public MyViewHolder( final View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_child);
            textView = itemView.findViewById(R.id.parent_textview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(List<ParentModel> myDataset) {
        pDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
            final View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.parent_recycler, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        ParentModel parent = pDataset.get(position);

        Log.e("onbindparent2", "onBindViewHolder: " + parent.getListChampion());
        holder.recyclerView.setAdapter(new ChildAdapter(parent.getListChampion()));

        RecyclerView.LayoutManager childLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), RecyclerView.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(childLayoutManager);

        Log.e("onbindparent", "onBindViewHolder: ");
        holder.recyclerView.setRecycledViewPool(viewPool);
        holder.textView.setText(parent.getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pDataset.size();
    }
}