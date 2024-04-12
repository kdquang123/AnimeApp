package com.example.animeapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<Story> stories;

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(view);
    }

    private void openStoryDetailActivity(StoryViewHolder holder, Story story) {
        // Mở StoryDetailActivity và truyền thông tin của truyện
        Intent intent = new Intent(holder.itemView.getContext(), StoryDetailActivity.class);
        intent.putExtra("id", story.getId());
        intent.putExtra("name", story.getName());
        intent.putExtra("coverImage", story.getCoverImage());
        intent.putExtra("author", story.getAuthor());
        intent.putExtra("summary", story.getSummary());
        intent.putExtra("category", story.getCategory());
        intent.putExtra("numOfChapter", story.getNumOfChapter());
        holder.itemView.getContext().startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.bind(story);

        // Thêm click listener cho từng item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở StoryDetailActivity khi người dùng click vào một item
                openStoryDetailActivity(holder, story);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class StoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImageView;
        private TextView nameTextView;
        private TextView authorTextView;
        private TextView categoryTextView;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.coverImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }

        public void bind(Story story) {
            // Set the data to the views
            nameTextView.setText(story.getName());
            authorTextView.setText(story.getAuthor());
            categoryTextView.setText(story.getCategory());
            // Load the cover image using a library like Glide or Picasso
        }
    }
}