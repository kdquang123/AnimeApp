package com.example.animeapp.Api;

import com.example.animeapp.Model.Page;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PageService {
    @GET("Page/{idChapter}")
    Call<ArrayList<Page>>getPageByIdChapter(@Path("idChapter")int idChapter);

    @GET("Page/{idChapter}/{pageNum}")
    Call<ArrayList<Page>>getPageByIdChapterAndPageNum(@Path("idChapter")int idChapter,@Path("pageNum")int pageNum);
}
