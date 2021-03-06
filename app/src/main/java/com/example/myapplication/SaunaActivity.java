package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaunaActivity extends AppCompatActivity {

    ImageView imageView;
    TextView sizeno;
    final int[] number = {0};

    Integer[] images = {
            R.drawable.img,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("name");

        Random r = new Random();

        TextView textView = findViewById(R.id.name);
        textView.setText(fName);

        imageView = (ImageView) findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-17.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-9.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-14.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/sauni/main.jpeg"));
        slideModels.add(new SlideModel(R.drawable.img));


        imageView.setImageResource(images[r.nextInt(images.length)]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.share_menu, menu);

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // We are using switch case because multiple icons can be kept
        switch (item.getItemId()) {
            case R.id.shareButton:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                // type of the content to be shared
                sharingIntent.setType("text/plain");
                Intent intent = getIntent();
                String fName = intent.getStringExtra("name");
                // Body of the content
                String shareBody = "Recommend you " + fName + "'s sauna";

                // subject of the content. you can share anything
                String shareSubject = "from JustRelax!";

                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void plus(View view){
        sizeno = findViewById(R.id.sizeno);
        if (number[0] == 9) {
            sizeno.setText("" + number[0]+"Hour");
        }
        if (number[0] < 9) {
            number[0] = number[0] + 1;
            sizeno.setText("" + number[0]+"Hour");
        }
    }

    public void minus(View view) {
        sizeno = findViewById(R.id.sizeno);
        if (number[0] == 0) {
            sizeno.setText("" + number[0]+"Hour");
        }
        if (number[0] > 0) {
            number[0] = number[0] - 1;
            sizeno.setText("" + number[0]+" Hour");
        }
    }


    public void book(View view) {
        Intent intent = new Intent(SaunaActivity.this, BookActivity.class);
        startActivity(intent);
    }
}