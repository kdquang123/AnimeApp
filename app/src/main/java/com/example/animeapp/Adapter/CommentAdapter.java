package com.example.animeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.animeapp.Model.Comment;
import com.example.animeapp.R;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Comment> listComment;
    private LayoutInflater inflater;
    public CommentAdapter(Context context, ArrayList<Comment> data){
        this.mContext = context;
        this.listComment = data;
    }

    @Override
    public int getCount() {
        return listComment.size();
    }

    @Override
    public Object getItem(int position) {
        return listComment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listComment.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            inflater = LayoutInflater.from(mContext);
            convertView=inflater.inflate(R.layout.comment_item,parent,false);
        }
        Comment comment = listComment.get(position);
        TextView name = convertView.findViewById(R.id.idUserName);
        TextView content = convertView.findViewById(R.id.idContentItem);
        TextView date = convertView.findViewById(R.id.idDate);

        name.setText(comment.getUserName());
        content.setText(comment.getContent());
        date.setText(String.valueOf(comment.getCreateAt()));

        convertView.setPadding(20, 20, 0, 20);

        return convertView;
    }
}
