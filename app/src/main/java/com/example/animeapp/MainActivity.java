package com.example.animeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.animeapp.Fragments.BookcaseFragment;
import com.example.animeapp.Fragments.HomeFragment;
import com.example.animeapp.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnSignOut;
    FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;

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
        Bundle b=getIntent().getExtras();
        HomeFragment homefragment = new HomeFragment();
        if(b!=null){
            homefragment.setArguments(b);
// Sau đó, thêm Fragment vào Activity bằng cách sử dụng FragmentManager
//            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
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
                    Intent intent=new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        loadFragment(homefragment);
    }


    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}