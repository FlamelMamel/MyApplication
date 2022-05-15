package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    private String URL = "http://10.202.0.122/login/register.php";
    private String name, email, password, repassword;

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
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        repassword = etReenterPassword.getText().toString();
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setText(name);
        if (!name.equals("") && !email.equals("") && !password.equals("") && !repassword.equals("")){
            Handler handler = new Handler();
            handler.post(new Runnable(){
                @Override
                public void run(){
                    String[] field = new String[4];
                    field[0] = "username";
                    field[1] = "email";
                    field[2] = "password";
                    field[3] = "repassword";

                    String[] data = new String[4];
                    data[0] = etName.getText().toString();
                    data[1] = etEmail.getText().toString();
                    data[2] = etPassword.getText().toString();
                    data[3] = etReenterPassword.getText().toString();
                    PutData putData = new PutData(URL, "POST", field, data);
                    if (putData.startPut()){
                        if (putData.onComplete()) {
                            String result = putData.getResult();

                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
                        }
                    }
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG);
                }
            });
        }
    }
}