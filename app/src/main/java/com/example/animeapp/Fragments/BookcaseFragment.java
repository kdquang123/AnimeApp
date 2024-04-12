package com.example.animeapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animeapp.FollowedStoryAdapter;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;
import com.example.animeapp.StoryDatabaseHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookcaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookcaseFragment extends Fragment {

    private RecyclerView recyclerView;
    private FollowedStoryAdapter adapter;
    private List<Story> followedStories;
    private StoryDatabaseHelper databaseHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookcaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookcaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookcaseFragment newInstance(String param1, String param2) {
        BookcaseFragment fragment = new BookcaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookcase, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new StoryDatabaseHelper(getContext());
        followedStories = databaseHelper.getAllFollowedStories();

        adapter = new FollowedStoryAdapter(followedStories);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void updateFollowedStories() {
        followedStories = databaseHelper.getAllFollowedStories();
        adapter.setStories(followedStories);
        adapter.notifyDataSetChanged();
    }
}