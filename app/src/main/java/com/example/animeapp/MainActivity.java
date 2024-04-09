package com.example.animeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animeapp.Fragments.BookcaseFragment;
import com.example.animeapp.Fragments.HomeFragment;
import com.example.animeapp.Fragments.SearchFragment;
import com.example.animeapp.Model.Story;
import com.example.animeapp.adapter.StoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnSignOut;
    FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    GridView gdvDSStory;
    StoryAdapter adapter;
    ArrayList<Story> storyArrayList;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
//        btnSignOut=findViewById(R.id.btnSignOut);
//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.mnu_home){
                    loadFragment(new HomeFragment());
                }else if(item.getItemId()==R.id.mnu_book){
                    loadFragment(new BookcaseFragment());
                }else if(item.getItemId()==R.id.mnu_search){
                    loadFragment(new SearchFragment());
                }else{

                }
                return true;
            }
        });
        loadFragment(new HomeFragment());

        init();
        gdvDSStory = findViewById(R.id.gdvDSTruyen);
        gdvDSStory.setAdapter(adapter);
        setClick();
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void init() {
        storyArrayList = new ArrayList<>();

        //Tôi tạo dữ liệu ảo để test
        storyArrayList.add(new Story(1, "Lmao", "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", "LTA", "Lmao", 1));
        storyArrayList.add(new Story(2, "Lmao", "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", "LTA", "Lmao", 1));
        storyArrayList.add(new Story(3, "Lmao", "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/IMAO.jpg", "LTA", "Lmao", 1));
        adapter = new StoryAdapter(this, 0, storyArrayList);
    }

    private void setClick() {
        gdvDSStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Story story = storyArrayList.get(i);
                Bundle b = new Bundle();
                b.putString("Name", story.getName());
                b.putString("Author", story.getAuthor());
                b.putString("Summary", story.getSummary());
                b.putString("Image", story.getCoverImage());
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}