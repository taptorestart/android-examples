package com.taptorestart.smarttab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity act;
    private final List<Object> mRecyclerViewItems;

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItem;

        ViewHolder(View v, Context ctx) {
            super(v);
            txtItem = (TextView) v.findViewById(R.id.txt_item);
        }
    }

    public ListAdapter(Activity activity, List<Object> recyclerViewItems) {
        act = activity;
        this.mRecyclerViewItems = recyclerViewItems;
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        int viewType = getItemViewType(position);
        final ViewHolder holder = (ViewHolder) viewHolder;
        final String label = (String) mRecyclerViewItems.get(position);
        holder.txtItem.setText(label);
    }
}