package com.example.animeapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.MainActivity;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;
import com.example.animeapp.StoryAdapter;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animeapp.R;
import com.example.animeapp.StoryDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private StoryAdapter adapter;
    private List<Story> stories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load the stories data (you can fetch it from a database or API)
        loadStoriesData();

        adapter = new StoryAdapter(stories);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadStoriesData() {
        stories.add(new Story(1, "Truyền võ", "https://img.nettruyenfull.com/story/20919/avatar.png", "", "", "Action", 6));
        stories.add(new Story(2, "Toàn Dân Chuyển Chức : Duy Ta Vô Chức Tán Nhân", "https://img.nettruyenfull.com/story/22469/avatar.png", "", "", "Action", 6));
        stories.add(new Story(3, "Tsuyoshi", "https://img.nettruyenfull.com/story/6814/avatar.png", "Maruyama Kyosuke", "", "Action", 6));
        stories.add(new Story(4, "Hội Pháp Sư và Thánh Thạch Rave", "https://img.nettruyenfull.com/story/9107/avatar.png", "", "", "Action", 4));
        stories.add(new Story(5, "Thợ Săn Top 1 Trở Lại", "https://img.nettruyenfull.com/story/9522/avatar.png", "", "", "Action", 4));
        // Thêm các truyện khác vào danh sách stories
    }

    private void openStoryDetailActivity(Story story) {
        // Mở StoryDetailActivity và truyền thông tin của truyện
        Intent intent = new Intent(getContext(), StoryDetailActivity.class);
        intent.putExtra("id", story.getId());
        intent.putExtra("name", story.getName());
        intent.putExtra("coverImage", story.getCoverImage());
        intent.putExtra("author", story.getAuthor());
        intent.putExtra("summary", story.getSummary());
        intent.putExtra("category", story.getCategory());
        intent.putExtra("numOfChapter", story.getNumOfChapter());
        startActivity(intent);
    }

}