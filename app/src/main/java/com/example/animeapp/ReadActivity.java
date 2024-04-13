package com.example.animeapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Adapter.ChapterAdapter;
import com.example.animeapp.Api.ChapterService;
import com.example.animeapp.Api.PageService;
import com.example.animeapp.Model.Chapter;
import com.example.animeapp.Model.Page;
import com.example.animeapp.Adapter.PageAdapter;
import com.example.animeapp.Model.Story;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadActivity extends AppCompatActivity {
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;

    Retrofit retrofit;
    PageService pageService;
    RecyclerView recyclerView;

    ArrayList<Page> arrayList;
    PageAdapter pageAdapter;

    int idChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
//        init();
        Bundle b=getIntent().getExtras();
        if (b != null) {
            int id = b.getInt("IdChap");
            idChap=id;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pageService = retrofit.create(PageService.class);



        recyclerView = (RecyclerView) findViewById(R.id.rcvpage);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);



        arrayList = new ArrayList<>();
//        arrayList.add(new Page(1, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 1, 1));
//        arrayList.add(new Page(2, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 2, 1));
//        arrayList.add(new Page(3, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 3, 1));
        Call<ArrayList<Page>> callPage = pageService.getPageByIdChapter(idChap);
        callPage.enqueue(new Callback<ArrayList<Page>>() {
            @Override
            public void onResponse(Call<ArrayList<Page>> call, Response<ArrayList<Page>> response) {
                if (response.isSuccessful()) {
                    arrayList = response.body();
                    pageAdapter = new PageAdapter(arrayList, getApplicationContext());
                    recyclerView.setAdapter(pageAdapter);
//                    txtCategory.setText("Ok");
                }else{
//                    txtCategory.setText("Not ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Page>> call, Throwable t) {

            }
        });

        setUp();
        setClick();
    }

    public void left(View view) {
        //Chuyển sang chap trước
    }

    public void right(View view) {
        //Chuyển sang chap sau
    }

    private void init() {
        //arrUrlAnh = new ArrayList<>();
        //arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        //arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        //arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        //arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        //soTrangDangDoc = 1;
        //soTrang = arrUrlAnh.size();
    }

    private void setUp() {
        docTheoTrang(0);
    }

    private void setClick() { }

    private void docTheoTrang(int i) {
        //soTrangDangDoc += i;
        //if (soTrangDangDoc == 0) {
        //    soTrangDangDoc = 1;
        //}
        //if (soTrangDangDoc > soTrang) {
        //    soTrangDangDoc = soTrang;
        //}
    }
}