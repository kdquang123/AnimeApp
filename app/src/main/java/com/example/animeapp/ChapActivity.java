package com.example.animeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Adapter.ChapterAdapter;
import com.example.animeapp.Model.Chapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChapActivity extends AppCompatActivity {
    TextView txtChapterName, txtAuthor, txtSummary;
    ImageView CoverImage;
    Button btnTheoDoi;
    RecyclerView recyclerViewChap;
    ArrayList<Chapter> arrChap;
    ChapterAdapter chapterAdapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        Bundle b = getIntent().getExtras();
        init();
        CoverImage = findViewById(R.id.CoverImage);
        btnTheoDoi = findViewById(R.id.btnTheoDoi);
        txtChapterName = findViewById(R.id.txtChapterName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtSummary = findViewById(R.id.txtSummary);
        recyclerViewChap = findViewById(R.id.recyclerViewChap);
        //recyclerViewChap.setNestedScrollingEnabled(false);
        if (b != null) {
            String name = b.getString("Name");
            String author = b.getString("Author");
            String summary = b.getString("Summary");
            String img = b.getString("Image");
            txtChapterName.setText(name);
            txtAuthor.setText(author);
            //Cái thể loại chưa sửa id nên xin phép ko động tới
            txtSummary.setText(summary);
            Glide.with(this).load(img).into(CoverImage);
            recyclerViewChap.setAdapter(chapterAdapter);
        }
        setUp();
        setClick();
    }

    private void init() {
        //Dữ liệu ảo để tôi test thử giao diện
        arrChap = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            try {
                Date date = dateFormat.parse("2022-02-22");
                arrChap.add(new Chapter(i, "Chapter " + i, date, i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        chapterAdapter = new ChapterAdapter(this, arrChap);
    }

    private void setUp() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChap.setLayoutManager(layoutManager);
    }

    private void setClick() {
        chapterAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(ChapActivity.this, ReadActivity.class));
            }
        });
    }
}