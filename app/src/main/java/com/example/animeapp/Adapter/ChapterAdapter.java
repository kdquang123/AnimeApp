package com.example.animeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.ChapActivity;
import com.example.animeapp.Model.Chapter;
import com.example.animeapp.R;
import com.example.animeapp.ReadActivity;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Chapter> chapterList;
    private OnItemClickListener onItemClickListener;

    int idStory;
    public ChapterAdapter(Context context, List<Chapter> chapterList,int idStory) {
        this.context = context;
        this.chapterList = new ArrayList<>(chapterList);
        this.idStory=idStory;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = chapterList.get(holder.getAdapterPosition());
        holder.txtChapterName.setText(chapter.getChapterName());
        holder.txtCreatedAt.setText(chapter.getCreateAt().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(view, holder.getAdapterPosition());
//                }
                Bundle b=new Bundle();
                b.putInt("IdChap",chapterList.get(holder.getAdapterPosition()).getId());
                b.putInt("IdStory", idStory);
                Intent intent =new Intent(context, ReadActivity.class);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtChapterName, txtCreatedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChapterName = itemView.findViewById(R.id.txtChapterName);
            txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
        }
    }
}