package com.example.useenator.myasyncloaderapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmed on 02/03/16.
 */
public class LoaderAdapter extends BaseAdapter {

    List<String> mData = new ArrayList<>();
    LayoutInflater mLayoutInflater;

    public LoaderAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= mLayoutInflater.inflate(R.layout.list_item_loder,parent,false);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.text_view);
        textView.setText(mData.get(position));
        return convertView;
    }

    public void swapData(List<String> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
