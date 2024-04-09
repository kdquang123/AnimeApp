package com.example.animeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends ArrayAdapter<Story> {
    private Context ct;
    private ArrayList<Story> arr;
    public StoryAdapter(Context context, int resource, List<Story> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }
        if (arr.size() > 0) {
            Story story = this.arr.get(position);
            TextView storyName = convertView.findViewById(R.id.txvTenTruyen);
            TextView author = convertView.findViewById(R.id.txvTenTG);
            ImageView imgAnhtruyen = convertView.findViewById(R.id.imgAnhTruyen);

            storyName.setText(story.getName());
            author.setText(story.getSummary());
            Glide.with(this.ct).load(story.getCoverImage()).into(imgAnhtruyen);
        }
        return convertView;
    }
}
