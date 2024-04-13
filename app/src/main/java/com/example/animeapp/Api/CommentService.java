package com.example.animeapp.Api;

import com.example.animeapp.Model.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {
    @GET("Comment/{idStory}")
    Call<ArrayList<Comment>>getCommentByIdStory(@Path("idStory")int idStory);

    @POST("Comment")
    Call<Comment>postComment(@Body Comment comment);
}
