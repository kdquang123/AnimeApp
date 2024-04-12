package com.example.animeapp.Api;

import com.example.animeapp.Model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("Category")
    Call<ArrayList<Category>>getAllCategory();
}
