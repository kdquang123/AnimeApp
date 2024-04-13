package com.example.animeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.animeapp.Adapter.CommentAdapter;
import com.example.animeapp.Api.CategoryService;
import com.example.animeapp.Api.CommentService;
import com.example.animeapp.Api.StoryService;
import com.example.animeapp.Model.Comment;
import com.example.animeapp.Model.Story;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {
    ListView listView;
    TextView txtPost;
    EditText editTextContent;
    ImageView imgBack;
    ArrayList<Comment> listComment;
    CommentAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String nameUser;
    int idStory ;

    Retrofit retrofit;
    CommentService commentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle b=getIntent().getExtras();
        idStory=b.getInt("IdStory");

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        nameUser=user.getEmail();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5070/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentService = retrofit.create(CommentService.class);
        find();

        post();

        back();

        listComment = new ArrayList<>();
//        listComment.add(new Comment(1,"Qua dep zai", "17-9-2003","Tat Loc",1));
//        listComment.add(new Comment(2,"Qua dep zai", "17-9-2003","Tat Loc",1));
//        listComment.add(new Comment(3,"Qua đẹp zai", "17-9-2003","Tat Loc",1));

        Call<ArrayList<Comment>> callComment = commentService.getCommentByIdStory(idStory);
        callComment.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                if (response.isSuccessful()) {
                    listComment= response.body();
                    adapter = new CommentAdapter(CommentActivity.this,listComment);
                    listView.setAdapter(adapter);
                    // Xử lý dữ liệu người dùng
//                    displayStoryList(storyList);
//                    searchEditText.setText("Ok");
                } else {
                    // Xử lý lỗi
//                    searchEditText.setText("not Ok");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                // Xử lý lỗi kết nối
//                searchEditText.setText("super not Ok");
            }
        });


    }

    private void back() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void post() {
        txtPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextContent.getText().toString();
                // Tạo một đối tượng Date
                Date date = new Date();

                // Định dạng ngày theo ý muốn của bạn
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(date);
                if(!content.equals("")){
                    Call<Comment> callComment = commentService.postComment(new Comment(0,content,strDate,nameUser,idStory));
                    callComment.enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            if (response.isSuccessful()) {
                                listComment.add(0,new Comment(0,content,strDate,nameUser,idStory));
                                adapter.notifyDataSetChanged();
                                editTextContent.setText("");
                            } else {
                                editTextContent.setText(strDate);
                            }
                        }

                        @Override
                        public void onFailure(Call<Comment> call, Throwable t) {
//                            listComment.add(0,new Comment(0,content,strDate,nameUser,idStory));
//                            adapter.notifyDataSetChanged();
//                            editTextContent.setText("");
                            editTextContent.setText("super not ok");
                        }
                    });

                }else {
                    Toast.makeText(CommentActivity.this, "Vui lòng viết bình luận !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void find() {
        listView=findViewById(R.id.idListview);
        txtPost=findViewById(R.id.idPost);
        imgBack=findViewById(R.id.idBack);
        editTextContent=findViewById(R.id.idContent);
    }

}