package com.example.animeapp.Api;

import com.example.animeapp.Model.Chapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChapterService {
    @GET("Chapter/{idStory}")
    Call<ArrayList<Chapter>>getChapterByIdStory(@Path("idStory")int idStory);
}
