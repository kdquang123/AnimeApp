package com.example.animeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.ChapActivity;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

import java.util.List;

public class FollowedStoryAdapter extends RecyclerView.Adapter<FollowedStoryAdapter.FollowedStoryViewHolder>{
    private List<Story> stories;
    private Context context;

    public FollowedStoryAdapter(Context context,List<Story> stories) {
        this.context=context;
        this.stories = stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public FollowedStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new FollowedStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowedStoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.bind(story);
        Glide.with(context).load(story.getCoverImage()).into(holder.coverImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt("Id",story.getId());
                b.putString("Name",story.getName());
                b.putString("Author",story.getAuthor());
                b.putString("Summary",story.getSummary());
                b.putString("Image",story.getCoverImage());
                b.putString("Category",story.getCategory());
                b.putInt("NumOfChapter",story.getNumOfChapter());
                Intent intent=new Intent(context, ChapActivity.class);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class FollowedStoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView coverImageView;
        private TextView nameTextView;
        private TextView authorTextView;
        private TextView categoryTextView;

        public FollowedStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.coverImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }

        public void bind(Story story) {
            nameTextView.setText(story.getName());
            authorTextView.setText(story.getAuthor());
            categoryTextView.setText(story.getCategory());
//            Glide.with(context).load().into();
            // Load the cover image using a library like Glide or Picasso
        }

    }
}
