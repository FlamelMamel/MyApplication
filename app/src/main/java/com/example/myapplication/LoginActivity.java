package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private String email, password;
    private String url = "http://justrelax.kz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = password = "";
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        //fghjkl
    }



    public void login(View view) {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (!email.equals("") && !password.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";

                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;
                    PutData putData = new PutData("http://justrelax.kz/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if(!result.equals("Login failed")){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("id", result);
                                startActivity(intent);
                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }
    }

    public void register(View view){
        Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intentRegister);
        finish();
    }
}