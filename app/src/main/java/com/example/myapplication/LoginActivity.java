package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private String email, password;
    private String URL = "https://192.168.0.121/login/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = password = "";
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
    }



    public void login(View view) {
    }

    public void register(View view){
        Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intentRegister);
        finish();
    }
}