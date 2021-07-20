package com.example.helloworld.recycleview;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<WebsiteData> mDataset = new ArrayList<>();
    private IOnItemClickListener mItemClickListener;

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.onBind(position, mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
    }

    private void sortList() {
        Collections.sort(mDataset, new Comparator<WebsiteData>() {
            public int compare(WebsiteData u1, WebsiteData u2) {
                int diff = u1.getCount() - u2.getCount();
                if (diff > 0)   return -1;
                else if (diff < 0) return 1;
                return 0;
            }
        });
        Log.d("debug", "sort called");
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {
        void onItemCLick(int position, WebsiteData data);
    }

    public MyAdapter(List<WebsiteData> myDataset) {
        mDataset.addAll(myDataset);
        sortList();
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
        notifyDataSetChanged();
    }

    public void addData(WebsiteData data) {
        mDataset.add(mDataset.size(), data);
        notifyDataSetChanged();
        sortList();
    }

    public void addData(int position, WebsiteData data) {
        mDataset.add(position, data);
        notifyItemInserted(position);
        if (position != mDataset.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mDataset.size() - position);
        }
        sortList();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIndex;
        private TextView tvTitle;
        private TextView tvHot;
        private View contentView;
        
        public MyViewHolder(View v) {
            super(v);
            contentView = v;
            tvIndex = v.findViewById(R.id.tv_index);
            tvTitle = v.findViewById(R.id.tv_title);
            tvHot = v.findViewById(R.id.tv_hot);
        }

        public void onBind(int position, WebsiteData data) {
            tvIndex.setText(new StringBuilder().append(position + 1).append(".  ").toString());
            tvTitle.setText(data.title);
            tvHot.setText(String.valueOf(data.count));
            if (position < 3) {
                tvIndex.setTextColor(Color.parseColor("#FFD700"));
            } else {
                tvIndex.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }
    }
}
