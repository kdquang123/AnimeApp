package com.example.animeapp.Api;

import com.example.animeapp.Model.Chapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChapterService {
    @GET("Chapter/{id}")
    Call<ArrayList<Chapter>>getChapterByIdStory(@Path("id")int id);
}
