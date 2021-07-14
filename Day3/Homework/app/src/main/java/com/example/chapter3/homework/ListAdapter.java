package com.example.chapter3.homework;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<ListItem> items = new ArrayList<>();
    public ListAdapter() {
        for (int i = 0; i < 3; i++) {
            items.add(new ListItem("SFXuan1", R.drawable.my_icon));
            items.add(new ListItem("SFXuan2", R.drawable.another_icon));
            items.add(new ListItem("SFXuan3", R.drawable.my_icon_trans));
            items.add(new ListItem("Honor", R.drawable.honor));
            items.add(new ListItem("Chuan1", R.drawable.chuan1));
            items.add(new ListItem("Chuan2", R.drawable.chuan2));
            items.add(new ListItem("View", R.drawable.view));
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItem li = items.get(i);

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_item, viewGroup, false);
        }

        ImageView img = view.findViewById(R.id.img);
        img.setImageResource(li.getSrcID());
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(li.getName());

        return view;
    }

    public static class MyViewHolder {
        ImageView img;
        TextView tv;
    }
}
