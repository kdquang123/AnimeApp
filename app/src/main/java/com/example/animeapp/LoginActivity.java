package com.example.animeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    static final int ERR_BLANK=1;
    static final int ERR_EMAIL_FORMAT=2;
    static final int ERR_SHORTPASS=3;
    static final int ERR_PASSWORD_MATCH=4;
    static final int VALIDATE_OK=0;
    FirebaseAuth mAuth;
    TextView btnDirectSignUp;
    ImageView btnViewPass;
    AppCompatButton btnSignIn;
    EditText edtEmail;
    EditText edtPass;
    TextView txtInformLogin;
    ProgressBar prgBarLg;
    @Override
    protected void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        btnDirectSignUp=findViewById(R.id.btnDirectSignUp);
        btnViewPass=findViewById(R.id.btnViewPass);
        edtEmail=findViewById(R.id.edtEmail);
        edtPass=findViewById(R.id.edtPassword);
        txtInformLogin=findViewById(R.id.txtInformLogin);
        btnSignIn=findViewById(R.id.btnSignIn);
        prgBarLg=findViewById(R.id.progressBarLg);
        btnDirectSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btnViewPass.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnViewPass.setImageResource(R.drawable.show_pasword_icon);
                edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                return true;
            }
        });

        btnViewPass.setOnClickListener(null);
        btnViewPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnViewPass.setImageResource(R.drawable.hide_password_icon);
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    return true;
                }
                return false;
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=edtEmail.getText().toString().trim();
                password=edtPass.getText().toString().trim();
                switch (validate(email,password)){
                    case ERR_BLANK:
                        txtInformLogin.setText("Không được bỏ trống!");
                        break;
                    case ERR_EMAIL_FORMAT:
                        txtInformLogin.setText("Email không đúng định dạng!");
                        break;
                    case ERR_SHORTPASS:
                        txtInformLogin.setText("Mật khẩu phải tối thiểu 6 ký tự!");
                        break;
                    case VALIDATE_OK:
                        prgBarLg.setVisibility(View.VISIBLE);
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            prgBarLg.setVisibility(View.GONE);
                                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                            startActivity(intent);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            prgBarLg.setVisibility(View.GONE);
                                            txtInformLogin.setText("Email hoặc mật khẩu chưa chính xác!");
                                        }
                                    }
                                });
                        break;
                }
            }
        });
    }

    public int validate(String email,String password){
        if(email.equals("") || password.equals("")){
            return ERR_BLANK;
        }
        if(password.length()<6){
            return ERR_SHORTPASS;
        }
        if(Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches()==false){
            return ERR_EMAIL_FORMAT;
        }
        return VALIDATE_OK;
    }
}