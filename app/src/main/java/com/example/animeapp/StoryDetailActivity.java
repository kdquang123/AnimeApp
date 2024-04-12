package com.example.animeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.animeapp.Fragments.BookcaseFragment;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;

public class StoryDetailActivity extends AppCompatActivity {
    private Story story;

    private BookcaseFragment bookcaseFragment;
    private StoryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_detail);

        // Lấy thông tin truyện từ Intent
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String coverImage = getIntent().getStringExtra("coverImage");
        String author = getIntent().getStringExtra("author");
        String summary = getIntent().getStringExtra("summary");
        String category = getIntent().getStringExtra("category");
        int numOfChapter = getIntent().getIntExtra("numOfChapter", 0);

        // Khởi tạo database helper
        databaseHelper = new StoryDatabaseHelper(this);

        // Lấy instance của BookcaseFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.bookcase_fragment);
        if (fragment instanceof BookcaseFragment) {
            bookcaseFragment = (BookcaseFragment) fragment;
        }

        // Hiển thị thông tin chi tiết của truyện
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView authorTextView = findViewById(R.id.authorTextView);
        TextView summaryTextView = findViewById(R.id.summaryTextView);
        TextView chapterCountTextView = findViewById(R.id.chapterCountTextView);
        ImageView coverImageView = findViewById(R.id.ImageStory);

        titleTextView.setText(name);
        authorTextView.setText(author);
        summaryTextView.setText(summary);
        chapterCountTextView.setText(String.valueOf(numOfChapter) + " chương");
        Glide.with(this)
                .load(coverImage)
                .into(coverImageView);

        // Thêm nút "Theo dõi"
        Button followButton = findViewById(R.id.followButton);
        story = new Story(id, name, coverImage, author, summary, category, numOfChapter);
        if (databaseHelper.isStoryFollowed(id)) {
            followButton.setText("Hủy theo dõi");
        } else {
            followButton.setText("Theo dõi");
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFollow(story);
                // Cập nhật danh sách truyện đã theo dõi ở BookcaseFragment
                if (bookcaseFragment != null) {
                    bookcaseFragment.updateFollowedStories();
                }
            }
        });
    }

    private void toggleFollow(Story story) {
        if (databaseHelper.isStoryFollowed(story.getId())) {
            databaseHelper.removeFollowedStory(story.getId());
            Toast.makeText(this, "Đã hủy theo dõi truyện " + story.getName(), Toast.LENGTH_SHORT).show();
            // Cập nhật lại nút "Theo dõi"
            Button followButton = findViewById(R.id.followButton);
            followButton.setText("Theo dõi");
        } else {
            databaseHelper.addFollowedStory(story);
            Toast.makeText(this, "Đã theo dõi truyện " + story.getName(), Toast.LENGTH_SHORT).show();
            // Cập nhật lại nút "Theo dõi"
            Button followButton = findViewById(R.id.followButton);
            followButton.setText("Hủy theo dõi");
        }
    }
}