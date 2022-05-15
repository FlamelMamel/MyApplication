package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class SaunaActivity extends AppCompatActivity {

    TextView sizeno;
    final int[] number = {0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-17.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-9.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/dopphoto/image-04-08-21-11-00-14.jpeg"));
        slideModels.add(new SlideModel("https://sauni-moskva.ru/sites/default/files/sauni/main.jpeg"));
        slideModels.add(new SlideModel(R.drawable.img));

        imageSlider.setImageList(slideModels, true);
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
}