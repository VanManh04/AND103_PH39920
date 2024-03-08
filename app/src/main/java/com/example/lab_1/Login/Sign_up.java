package com.example.lab_1.Login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_up extends AppCompatActivity {
    private  FirebaseAuth mAuth;
    String email,password;
    EditText edtEmail,edtPassword;
    Button btn_Signup;
    TextView txt_Login;

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            // neu user da dang nhap vao tu phien truoc thi su dung user luon
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtUsername_Signup);
        edtPassword = findViewById(R.id.edtPassword_Signup);
        txt_Login = findViewById( R.id.txt_Login);
        btn_Signup = findViewById(R.id.btn_Signup);



        txt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up.this, Login.class);
                startActivity(i);
            }
        });
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();
                CreateAccount();
            }
        });
    }
    public  void CreateAccount() {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(this, "Không bỏ trống thông tin !", Toast.LENGTH_SHORT).show();
//        } else if (check.checkPassword(password)) {
//            Toast.makeText(this, "Mật khẩu quá yếu !", Toast.LENGTH_SHORT).show();
//        } else if (check.checkGmailFormat(email)) {
//            Toast.makeText(this, "Email không đúng định dạng !", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //lấy thông tin tài khoản vừa mới đăng kí
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(Sign_up.this, "Đăng kí thành công !", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(Sign_up.this, "Đăng kí thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}