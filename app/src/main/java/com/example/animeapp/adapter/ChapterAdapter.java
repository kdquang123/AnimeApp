package com.example.animeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.animeapp.Model.Chapter;
import com.example.animeapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends ArrayAdapter<Chapter> {
    private Context ct;
    private ArrayList<Chapter> arr;
    public ChapterAdapter(Context context, int resource, List<Chapter> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chapter, null);
        }
        if (arr.size() > 0) {
            TextView txtChapterName, txtCreatedAt;
            txtChapterName = convertView.findViewById(R.id.txtChapterName);
            txtCreatedAt = convertView.findViewById(R.id.txtCreatedAt);
            Chapter chapter = arr.get(position);
            txtChapterName.setText(chapter.getChapterName());
            txtCreatedAt.setText(chapter.getCreateAt().toString());
        }
        return convertView;
    }
}