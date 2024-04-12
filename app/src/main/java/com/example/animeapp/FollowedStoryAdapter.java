package com.example.animeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Model.Story;

import java.util.List;

public class FollowedStoryAdapter extends RecyclerView.Adapter<FollowedStoryAdapter.FollowedStoryViewHolder> {
    private List<Story> stories;

    public FollowedStoryAdapter(List<Story> stories) {
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
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class FollowedStoryViewHolder extends RecyclerView.ViewHolder {
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
            // Load the cover image using a library like Glide or Picasso
        }
    }
}
