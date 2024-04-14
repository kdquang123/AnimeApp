package com.example.animeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.Locale;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    static final int ERR_BLANK=1;
    static final int ERR_EMAIL_FORMAT=2;
    static final int ERR_SHORTPASS=3;
    static final int ERR_PASSWORD_MATCH=4;
    static final int VALIDATE_OK=0;
    FirebaseAuth mAuth;
    AppCompatButton btnSignUp;
    AppCompatButton btnDirectSignUp;
    ImageView btnViewPassRg;
    ImageView btnViewConPass;
    TextView btnDirectSignIn;
    EditText edtEmailRg;
    EditText edtPassRg;
    EditText edtConPass;
    TextView txtInformRg;
    ProgressBar prbarRg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        btnSignUp=findViewById(R.id.btnSignUp);
        btnViewPassRg=findViewById(R.id.btnViewPassRg);
        btnViewConPass=findViewById(R.id.btnViewConPass);
        btnDirectSignIn=findViewById(R.id.btnDirectSignIn);
        edtEmailRg=findViewById(R.id.edtEmailRg);
        edtPassRg=findViewById(R.id.edtPasswordRg);
        edtConPass=findViewById(R.id.edtConPassword);
        txtInformRg=findViewById(R.id.txtInformRegister);
        prbarRg=findViewById(R.id.progressBarRg);
        btnViewPassRg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnViewPassRg.setImageResource(R.drawable.show_pasword_icon);
                edtPassRg.setInputType(InputType.TYPE_CLASS_TEXT);
                return false;
            }
        });

        btnViewPassRg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnViewPassRg.setImageResource(R.drawable.hide_password_icon);
                    edtPassRg.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    return true;
                }
                return false;
            }
        });

        btnViewConPass.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnViewConPass.setImageResource(R.drawable.show_pasword_icon);
                edtConPass.setInputType(InputType.TYPE_CLASS_TEXT);
                return false;
            }
        });

        btnViewConPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnViewConPass.setImageResource(R.drawable.hide_password_icon);
                    edtConPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    return true;
                }
                return false;
            }
        });




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,conPassword;
                email=edtEmailRg.getText().toString().trim();
                password=edtPassRg.getText().toString().trim();
                conPassword=edtConPass.getText().toString().trim();
                switch (validate(email,password,conPassword)){
                    case ERR_BLANK:
                        txtInformRg.setText("Không đươc bỏ trống!");
                        break;
                    case ERR_EMAIL_FORMAT:
                        txtInformRg.setText("Email không đúng định dạng!");
                        break;
                    case ERR_SHORTPASS:
                        txtInformRg.setText("Mật khẩu phải tối thiểu 6 ký tự!");
                        break;
                    case ERR_PASSWORD_MATCH:
                        txtInformRg.setText("Mật khẩu và mật khẩu xác thực không khớp!");
                        break;
                    case VALIDATE_OK:
                        prbarRg.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            prbarRg.setVisibility(View.GONE);
                                            // Sign in success, update UI with the signed-in user's information
                                            txtInformRg.setTextColor(getResources().getColor(R.color.success));
                                            txtInformRg.setText("Đăng ký thành công!");
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            prbarRg.setVisibility(View.GONE);
                                            txtInformRg.setText("Đăng ký thất bại!");
                                        }
                                    }
                                });
                        break;
                }

            }
        });


        btnDirectSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }



    public int validate(String email,String password,String conPassword){
        if(email.equals("") || password.equals("") || conPassword.equals("")){
            return ERR_BLANK;
        }
        if(password.length()<6){
            return ERR_SHORTPASS;
        }
        if(Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches()==false){
            return ERR_EMAIL_FORMAT;
        }
        if(password.equals(conPassword)==false){
            return ERR_PASSWORD_MATCH;
        }
        return VALIDATE_OK;
    }
}