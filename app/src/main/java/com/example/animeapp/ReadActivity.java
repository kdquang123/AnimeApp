package com.example.animeapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Model.Page;
import com.example.animeapp.adapter.PageAdapter;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
        recyclerView = (RecyclerView) findViewById(R.id.rcvpage);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Page> arrayList = new ArrayList<>();
        arrayList.add(new Page(1, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 1, 1));
        arrayList.add(new Page(2, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 2, 1));
        arrayList.add(new Page(3, "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", 3, 1));
        PageAdapter pageAdapter = new PageAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(pageAdapter);
        setUp();
        setClick();
    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);
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