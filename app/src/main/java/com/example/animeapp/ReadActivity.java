package com.example.animeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    ImageView imgPage;
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;
    TextView txtSoTrang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
        imgPage = findViewById(R.id.imgPage);
        txtSoTrang = findViewById(R.id.txtSoTrang);
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
        arrUrlAnh = new ArrayList<>();
        arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        arrUrlAnh.add("https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg");
        soTrangDangDoc = 1;
        soTrang = arrUrlAnh.size();
    }

    private void setUp() {
        docTheoTrang(0);
    }

    private void setClick() { }

    private void docTheoTrang(int i) {
        soTrangDangDoc += i;
        if (soTrangDangDoc == 0) {
            soTrangDangDoc = 1;
        }
        if (soTrangDangDoc > soTrang) {
            soTrangDangDoc = soTrang;
        }
        txtSoTrang.setText(soTrangDangDoc + "/" + soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc - 1)).into(imgPage);
    }
}