package com.example.gadau.sqldemo.logic;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadau.sqldemo.data.DataItem;
import com.example.gadau.sqldemo.R;
import java.util.List;

/**
 * Created by gadau on 8/2/2017.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.FragListItem> {
    private final List<DataItem> inventory;
    private Context mContext;
    private ItemClickListener clickListener;

    public LineAdapter(List<DataItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public FragListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        mContext = parent.getContext();
        return new FragListItem(v);
    }

    @Override
    public void onBindViewHolder(FragListItem holder, int position) {
        holder.itemID.setText(inventory.get(position).getID());
        holder.itemLoc.setText(inventory.get(position).getLocation());
        holder.itemQty.setText(inventory.get(position).getQty());
    }

    @Override
    public int getItemCount() {
        return inventory != null ? inventory.size() : 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void updateData(List<DataItem> list){
        inventory.clear();
        inventory.addAll(list);
        notifyDataSetChanged();
    }

    public class FragListItem extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView itemID;
        public TextView itemLoc;
        public TextView itemQty;

        public FragListItem (View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemID = (TextView) itemView.findViewById(R.id.list_id);
            itemLoc = (TextView) itemView.findViewById(R.id.list_loc);
            itemQty = (TextView) itemView.findViewById(R.id.list_qty);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
