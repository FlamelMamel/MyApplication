package com.example.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.CardsAdapter;
import com.example.myapplication.model.CardsModel;
import com.example.myapplication.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;


import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String id;
    TextView textView;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id = null;
            } else {
                id = extras.getString("id");
            }
        } else {
            id = (String) savedInstanceState.getSerializable("id");
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        HomeFragment datas = new HomeFragment();
        datas.setArguments(bundle);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_visited, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        imageButton =(ImageButton) findViewById(R.id.profilePic);

//        imageButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivity(intent);
//            }
//        });
    }
//
//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        super.onActivityReenter(resultCode, data);
//        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//        imageButton.setImageBitmap(bitmap);
//    }

    public void scan(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(
                MainActivity.this
        );

        intentIntegrator.setPrompt("For flash use volume key");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.initiateScan();
    }


    public String getMyData() {
        return id;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );


        if (result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this
            );
            builder.setTitle("result");

            builder.setMessage(result.getContents());

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        }else{
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    public void soundPlay(MediaPlayer sound){
        sound.start();
    }
}