package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etReenterPassword;
    private Button btnRegister;
    private String name, email, password, repassword;
    public String url = "http://192.168.4.9/";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    public void login(View view){
        Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    public void save(View view) {
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etReenterPassword = findViewById(R.id.et_repassword);
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        repassword = etReenterPassword.getText().toString().trim();
        btnRegister = findViewById(R.id.btn_register);
        if (!name.equals("") && !email.equals("") && !password.equals("") && !repassword.equals("")){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[4];
                    field[0] = "username";
                    field[1] = "email";
                    field[2] = "password";
                    field[3] = "repassword";

                    String[] data = new String[4];
                    data[0] = name;
                    data[1] = email;
                    data[2] = password;
                    data[3] = repassword;
                    PutData putData = new PutData(url + "/justRelax/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }
    }
}