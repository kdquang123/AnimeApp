package com.example.animeapp;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.Adapter.RecyclerViewCategoryAdapter;
import com.example.animeapp.Api.CategoryService;
import com.example.animeapp.Api.OnItemClickListener;
import com.example.animeapp.Api.StoryService;
import com.example.animeapp.Model.Category;
import com.example.animeapp.Model.Story;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity implements OnItemClickListener {
    private List<Category> categories;
    private int selectedItemId;
    private RecyclerView rcvcategory;
    private Button btnCancel;
    private Button btnApply;
    private RecyclerViewCategoryAdapter recyclerViewCategoryAdapter;
    Retrofit retrofit;
    CategoryService categoryService;


    int selectedItem=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryService = retrofit.create(CategoryService.class);
        rcvcategory = findViewById(R.id.rcv_category);

        btnApply=findViewById(R.id.btnApply);
        btnCancel=findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                int idCategory=categories.get(selectedItem).getId();
                b.putInt("IdCategory",idCategory);
                Intent intent=new Intent(CategoryActivity.this,MainActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        getcategory();
//        categories = getcategory();
//        recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapter(this);
//        recyclerViewCategoryAdapter.setData(categories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcvcategory.setAdapter(recyclerViewCategoryAdapter);
        rcvcategory.setLayoutManager(gridLayoutManager);
    }
    private void getcategory(){
//        List<Category> a = new ArrayList<>();
//        a.add(new Category(1,"Đam mĩ"));
//        a.add(new Category(2,"yuri"));
//        a.add(new Category(3,"gay"));
//        a.add(new Category(4,"furi"));
//        a.add(new Category(5,"incest"));
//        a.add(new Category(6,"ntr"));
        Call<ArrayList<Category>> callCategory = categoryService.getAllCategory();
        callCategory.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    categories= response.body();
                    recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapter(CategoryActivity.this,CategoryActivity.this);
                    recyclerViewCategoryAdapter.setData(categories);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryActivity.this,3);
                    rcvcategory.setAdapter(recyclerViewCategoryAdapter);
                    rcvcategory.setLayoutManager(gridLayoutManager);
                    // Xử lý dữ liệu người dùng
//                    displayStoryList(storyList);
//                    searchEditText.setText("Ok");
                } else {
                    // Xử lý lỗi
//                    searchEditText.setText("not Ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                // Xử lý lỗi kết nối
//                searchEditText.setText("super not Ok");
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        selectedItem=position;

    }

}