package com.example.animeapp.Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Adapter.RecyclerViewHomeAdapter;
import com.example.animeapp.Adapter.RecyclerViewHomeAdapter2;
import com.example.animeapp.Api.StoryService;
import com.example.animeapp.LoginActivity;
import com.example.animeapp.MainActivity;
import com.example.animeapp.Model.Story;
import com.example.animeapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    //    FirebaseAuth mAuth;
    int firstVisibleItemPosition = 1;
    private boolean isScrolling = false;
    private List<Story> stories;
    private Timer timer;
    private Handler handler;
    private Runnable runnable;
    private int currentSlide = 0;
    private static final long SLIDE_DELAY = 5000;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerViewHomeAdapter itemadapter;
    private RecyclerViewHomeAdapter2 item2adapter;
    private ImageView btnLogout;
    int idCategory=-1;

    private FirebaseAuth mAuth;
    Retrofit retrofit;
    StoryService storyService;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
            idCategory=getArguments().getInt("IdCategory");
        }

        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storyService = retrofit.create(StoryService.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= view.findViewById(R.id.rcv_home);
        recyclerView2= view.findViewById(R.id.rcv_home2);
        btnLogout=view.findViewById(R.id.iv_Logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent=new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

//        stories = getListStory();

        itemadapter = new RecyclerViewHomeAdapter(getContext());
        item2adapter = new RecyclerViewHomeAdapter2(getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(gridLayoutManager);

        itemadapter.setData(getListSlideShow());
//        item2adapter.setData(getListStory());


        recyclerView.setAdapter(itemadapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    // Khi RecyclerView đang được kéo
                    isScrolling = true;
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && isScrolling) {
                    // Khi RecyclerView dừng kéo và trạng thái kéo trước đó là true
                    isScrolling = false;
                    firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }
            }
        });
        scrollToNextItems();


        //call API
        if(idCategory!=-1){
            getListStoryByCategory();
        }else{
            getListStory();
        }

//        ImageView imglogout=view.findViewById(R.id.iv_Logout);
//        imglogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                mAuth.signOut();
//                Intent intent=new Intent(getActivity(),LoginActivity.class);
//                startActivity(intent);
//            }
//        });
        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, SLIDE_DELAY);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    private void scrollToNextItems(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentSlide == 2) {
                    currentSlide = 0;
                } else {
                    currentSlide = firstVisibleItemPosition;
                    currentSlide++;
                }
                recyclerView.smoothScrollToPosition(currentSlide);
                handler.postDelayed(this, SLIDE_DELAY);
            }
        };
    }

    private List<Story> getListSlideShow(){
        List<Story> list = new ArrayList<>();
        list.add(new Story(1,"name","CoverImage","Author","Summary","ntr",1));
        list.add(new Story(2,"name","CoverImage","Author","Summary","ntr",2));
        list.add(new Story(3,"name","CoverImage","Author","Summary","ntr",3));
        return list;
    }
    private void getListStory(){
//        List<Story> list = new ArrayList<>();
//        list.add(new Story(1,"name","CoverImage","Author","Summary","ntr",1));
//        list.add(new Story(2,"name","CoverImage","Author","Summary","ntr",2));
//        list.add(new Story(3,"name","CoverImage","Author","Summary","ntr",3));
//        list.add(new Story(4,"name","CoverImage","Author","Summary","ntr",4));
//        list.add(new Story(5,"name","CoverImage","Author","Summary","ntr",5));
//        list.add(new Story(6,"name","CoverImage","Author","Summary","ntr",6));
//        list.add(new Story(7,"name","CoverImage","Author","Summary","ntr",7));
//        list.add(new Story(8,"name","CoverImage","Author","Summary","ntr",8));
//        list.add(new Story(9,"name","CoverImage","Author","Summary","ntr",9));
        Call<ArrayList<Story>> callStory = storyService.getStory();
        callStory.enqueue(new Callback<ArrayList<Story>>() {
            @Override
            public void onResponse(Call<ArrayList<Story>> call, Response<ArrayList<Story>> response) {
                if (response.isSuccessful()) {
                    stories= response.body();
                    item2adapter.setData(stories);
                    recyclerView2.setAdapter(item2adapter);
                    // Xử lý dữ liệu người dùng
//                    displayStoryList(storyList);
//                    searchEditText.setText("Ok");
                } else {
                    // Xử lý lỗi
//                    searchEditText.setText("not Ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Story>> call, Throwable t) {
                // Xử lý lỗi kết nối
//                searchEditText.setText("super not Ok");
            }
        });
//        return list;
    }

    private void getListStoryByCategory(){
        Call<ArrayList<Story>> callStory = storyService.getStoryByIdCategory(idCategory);
        callStory.enqueue(new Callback<ArrayList<Story>>() {
            @Override
            public void onResponse(Call<ArrayList<Story>> call, Response<ArrayList<Story>> response) {
                if (response.isSuccessful()) {
                    stories= response.body();
                    item2adapter.setData(stories);
                    recyclerView2.setAdapter(item2adapter);
                } else {
                    // Xử lý lỗi
//                    searchEditText.setText("not Ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Story>> call, Throwable t) {
                // Xử lý lỗi kết nối
//                searchEditText.setText("super not Ok");
            }
        });
    }
}