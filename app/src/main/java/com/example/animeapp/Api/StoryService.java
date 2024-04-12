package com.example.animeapp.Api;

import com.example.animeapp.Model.Story;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoryService {
    @GET("Story")
    Call<ArrayList<Story>> getStory();

    @GET("Story/page/{pageNum}")
    Call<ArrayList<Story>> getStoryByPage(@Path("pageNum") int pageNum);

    @GET("Story/name/{name}")
    Call<ArrayList<Story>> getStoryByName(@Path("name") String name);

    @GET("Story/{id}")
    Call<Story>getStoryById(@Path("id")int id);

    @GET("Story/category/{idCategory}")
    Call<ArrayList<Story>>getStoryByIdCategory(@Path("idCategory")int idCategory);
}
