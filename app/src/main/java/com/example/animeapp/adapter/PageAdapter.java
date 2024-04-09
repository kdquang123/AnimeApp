package com.example.animeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Model.Page;
import com.example.animeapp.R;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends ArrayAdapter<Page> {
    private Context ct;
    private ArrayList<Page> arr;
    public PageAdapter(Context context, int resource, List<Page> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_page, null);
        }
        if (arr.size() > 0) {
            Page page = this.arr.get(position);
            ImageView imgPage;
            imgPage = convertView.findViewById(R.id.img1Page);
            Glide.with(this.ct).load(page.getImage()).into(imgPage);
        }
        return convertView;
    }
}
