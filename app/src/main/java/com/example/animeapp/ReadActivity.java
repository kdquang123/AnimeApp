package com.example.animeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.Adapter.ChapterAdapter;
import com.example.animeapp.Adapter.PageAdapter;
import com.example.animeapp.Api.ChapterService;
import com.example.animeapp.Api.PageService;
import com.example.animeapp.Model.Chapter;
import com.example.animeapp.Model.Page;
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
    ChapterAdapter chapterAdapter;
    Retrofit retrofit;
    PageService pageService;
    ChapterService chapterService;
    RecyclerView recyclerView;
    Spinner spinnerChapters;
    Button btnLeft, btnRight;
    ArrayList<Page> arrayList;
    ArrayList<Chapter> chapters = new ArrayList<>();
    PageAdapter pageAdapter;
    int firstChapterId;
    int lastChapterId;
    int idChap, idStory;
    boolean isFirstSelection = true;

    ArrayAdapter<Chapter> spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        btnLeft=findViewById(R.id.btnLeft);
        btnRight=findViewById(R.id.btnRight);
        spinnerChapters=findViewById(R.id.spinnerChapters);


//        init();
        Bundle b=getIntent().getExtras();
        if (b != null) {
            int id = b.getInt("IdChap");
            idChap=id;
            idStory = b.getInt("IdStory");
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pageService = retrofit.create(PageService.class);
        chapterService = retrofit.create(ChapterService.class);




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



        Call<ArrayList<Chapter>> callChap = chapterService.getChapterByIdStory(idStory);
        callChap.enqueue(new Callback<ArrayList<Chapter>>() {
            @Override
            public void onResponse(Call<ArrayList<Chapter>> call, Response<ArrayList<Chapter>> response) {
                if (response.isSuccessful()) {
                    chapters = response.body();
                    // Tiếp tục xử lý dữ liệu nhận được, ví dụ: cập nhật danh sách chapter trong adapter và cập nhật lại Spinner
                    spinnerAdapter = new ArrayAdapter<>(ReadActivity.this, android.R.layout.simple_spinner_item, chapters);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerChapters.setAdapter(spinnerAdapter);

                    firstChapterId = ((Chapter) spinnerChapters.getItemAtPosition(0)).getId();

                    // Lấy ID của chapter cuối cùng
                    int lastPosition = spinnerChapters.getCount() - 1;
                    lastChapterId = ((Chapter) spinnerChapters.getItemAtPosition(lastPosition)).getId();
                    spinnerChapters.setSelection(idChap-firstChapterId);
                    spinnerChapters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(isFirstSelection){
                                isFirstSelection=false;
                                return;
                            }
                            Bundle b=new Bundle();
                            b.putInt("IdChap", chapters.get(position).getId());
                            b.putInt("IdStory", idStory);
                            Intent intent =new Intent(ReadActivity.this, ReadActivity.class);
                            intent.putExtras(b);
                            startActivity(intent);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    btnLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (idChap != firstChapterId) {
                                Bundle b=new Bundle();
                                b.putInt("IdChap", idChap - 1);
                                b.putInt("IdStory", idStory);
                                Intent intent =new Intent(ReadActivity.this, ReadActivity.class);
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        }
                    });

                    btnRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (idChap != lastChapterId) {
                                Bundle b=new Bundle();
                                b.putInt("IdChap", idChap + 1);
                                b.putInt("IdStory", idStory);
                                Intent intent =new Intent(ReadActivity.this, ReadActivity.class);
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    // Xử lý khi gặp lỗi trong phản hồi từ API
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chapter>> call, Throwable t) {
                // Xử lý khi gặp lỗi trong quá trình gửi yêu cầu API
            }
        });


    }

}