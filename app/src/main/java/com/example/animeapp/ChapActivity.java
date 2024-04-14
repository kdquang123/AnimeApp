package com.example.animeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Adapter.ChapterAdapter;
import com.example.animeapp.Api.ChapterService;
import com.example.animeapp.Api.StoryService;
import com.example.animeapp.Database.StoryDatabaseHelper;
import com.example.animeapp.Model.Chapter;
import com.example.animeapp.Model.Story;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChapActivity extends AppCompatActivity {
    TextView txtChapterName, txtAuthor, txtSummary;
    ImageView CoverImage;
    Button btnTheoDoi;
    RecyclerView recyclerViewChap;
    ArrayList<Chapter> arrChap;
    ChapterAdapter chapterAdapter;
    TextView txtCategory;
    Retrofit retrofit;
    ChapterService chapterService;
    ImageView btnComment;
    private StoryDatabaseHelper databaseHelper;
    private Story story;
    private boolean isFollowed;
    int idStory;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);

        databaseHelper = new StoryDatabaseHelper(this);

        Bundle b = getIntent().getExtras();
        CoverImage = findViewById(R.id.CoverImage);
        btnTheoDoi = findViewById(R.id.btnTheoDoi);
        txtChapterName = findViewById(R.id.txtChapterName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtSummary = findViewById(R.id.txtSummary);
        recyclerViewChap = findViewById(R.id.recyclerViewChap);
        txtCategory=findViewById(R.id.txtCategory);
        btnComment=findViewById(R.id.btnComment);
        //recyclerViewChap.setNestedScrollingEnabled(false);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        chapterService = retrofit.create(ChapterService.class);

        if (b != null) {
            int id = b.getInt("Id");
            idStory=id;
            String name = b.getString("Name");
            String author = b.getString("Author");
            String summary = b.getString("Summary");
            String img = b.getString("Image");
            String category = b.getString("Category");
            story=new Story(id,name,img,author,summary,category,0);
            txtChapterName.setText(name);
            txtAuthor.setText(author);
            //Cái thể loại chưa sửa id nên xin phép ko động tới
            txtCategory.setText("Thể loại :" + category);
            txtSummary.setText(summary);
            Glide.with(this).load(img).into(CoverImage);
            initData(id);
            recyclerViewChap.setAdapter(chapterAdapter);
            isFollowed = databaseHelper.isStoryFollowed(story.getId());
        }
        // Cập nhật nội dung và trạng thái của nút btnTheoDoi
        if (isFollowed) {
            btnTheoDoi.setText("Huỷ Theo Dõi");
        } else {
            btnTheoDoi.setText("Theo Dõi");
        }
        setUp();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt("IdStory",idStory);
                Intent intent=new Intent(ChapActivity.this,CommentActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnTheoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFollowed) {
                    databaseHelper.removeFollowedStory(story.getId());
                    isFollowed = false;
                    btnTheoDoi.setText("Theo Dõi");
                    Toast.makeText(ChapActivity.this, "Đã huỷ theo dõi truyện " + story.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.addFollowedStory(story);
                    isFollowed = true;
                    btnTheoDoi.setText("Huỷ Theo Dõi");
                    Toast.makeText(ChapActivity.this, "Đã theo dõi truyện " + story.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cập nhật nội dung và trạng thái của nút btnTheoDoi
        if (isFollowed) {
            btnTheoDoi.setText("Huỷ Theo Dõi");
        } else {
            btnTheoDoi.setText("Theo Dõi");
        }
//        setClick();
    }

    private void initData(int idStory) {
        //Dữ liệu ảo để tôi test thử giao diện
        arrChap = new ArrayList<>();
//        for (int i = 1; i < 20; i++) {
//            try {
//                Date date = dateFormat.parse("2022-02-22");
//                arrChap.add(new Chapter(i, "Chapter " + i, date, i));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        Call<ArrayList<Chapter>> callChapter = chapterService.getChapterByIdStory(idStory);
        callChapter.enqueue(new Callback<ArrayList<Chapter>>() {
            @Override
            public void onResponse(Call<ArrayList<Chapter>> call, Response<ArrayList<Chapter>> response) {
                if (response.isSuccessful()) {
                    arrChap = response.body();
                    chapterAdapter = new ChapterAdapter(ChapActivity.this, arrChap,idStory);
                    recyclerViewChap.setAdapter(chapterAdapter);
//                    txtCategory.setText("Ok");
                }else{
//                    txtCategory.setText("Not ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chapter>> call, Throwable t) {
//                chapterAdapter=new ChapterAdapter(ChapActivity.this,arrChap,idStory);

                txtCategory.setText("Super not ok");
            }
        });
    }

    private void setUp() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChap.setLayoutManager(layoutManager);
    }

    private void setClick() {
        chapterAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle b=new Bundle();
                b.putInt("IdChap",arrChap.get(position).getId());
                b.putInt("IdStory", idStory);
                Intent intent =new Intent(ChapActivity.this, ReadActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}