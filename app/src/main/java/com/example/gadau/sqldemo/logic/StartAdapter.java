package com.example.gadau.sqldemo.logic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadau.sqldemo.R;
import com.example.gadau.sqldemo.data.MenuOption;
import com.example.gadau.sqldemo.view.ListActivity;
import com.example.gadau.sqldemo.view.MainActivity;
import com.example.gadau.sqldemo.view.StartPageActivity;

import java.util.List;

/**
 * Created by gadau on 8/10/2017.
 */

public class StartAdapter extends RecyclerView.Adapter<StartAdapter.StartOptionItem> {
    private final List<MenuOption> listOfData;
    private Context mContext;
    private ItemClickListener clickListener;

    public StartAdapter(List<MenuOption> list) {
        listOfData = list;
    }

    @Override
    public StartOptionItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cardview, parent, false);
        mContext = parent.getContext();
        return new StartOptionItem(v);
    }

    @Override
    public void onBindViewHolder(StartOptionItem holder, int position) {
        final int pos = position;
        holder.iHeader.setText(listOfData.get(position).getHeader());
        holder.iDesc.setText(listOfData.get(position).getDesc());
        holder.iBackground.setBackgroundResource(listOfData.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return listOfData != null ? listOfData.size() : 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class StartOptionItem extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView iHeader;
        public TextView iDesc;
        public CardView iCard;
        public View iBackground;

        public StartOptionItem(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iHeader = (TextView) itemView.findViewById(R.id.start_header);
            iDesc = (TextView) itemView.findViewById(R.id.start_desc);
            iBackground = itemView.findViewById(R.id.start_view);
            iCard = (CardView) itemView.findViewById(R.id.root_start_option);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
