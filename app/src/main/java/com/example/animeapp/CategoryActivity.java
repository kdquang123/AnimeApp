package com.example.animeapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Adapter.RecyclerViewCategoryAdapter;
import com.example.animeapp.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private List<Category> categories;
    private RecyclerView rcvcategory;
    private RecyclerViewCategoryAdapter recyclerViewCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rcvcategory = findViewById(R.id.rcv_category);

        categories = getcategory();
        recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapter(this);
        recyclerViewCategoryAdapter.setData(categories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcvcategory.setAdapter(recyclerViewCategoryAdapter);
        rcvcategory.setLayoutManager(gridLayoutManager);
    }
    private List<Category> getcategory(){
        List<Category> a = new ArrayList<>();
        a.add(new Category(1,"Đam mĩ"));
        a.add(new Category(2,"yuri"));
        a.add(new Category(3,"gay"));
        a.add(new Category(4,"furi"));
        a.add(new Category(5,"incest"));
        a.add(new Category(6,"ntr"));

        return a;
    }
}