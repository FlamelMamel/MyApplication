package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.MyAdapter;
import com.example.myapplication.model.MyModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.model.Category;

import java.util.ArrayList;
import java.util.List;


import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    private ViewPager viewPager;
    private ArrayList<MyModel> modelArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_favorite, R.id.navigation_visited)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Финская"));
        categoryList.add(new Category(2, "Римская"));
        categoryList.add(new Category(3, "Русская"));
        categoryList.add(new Category(4, "Восточная"));
        categoryList.add(new Category(5, "Японская "));

        setCategoryRecycler(categoryList);

        viewPager = findViewById(R.id.viewPager);
        loadCards();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }
    private void loadCards() {
        modelArrayList = new ArrayList<>();
        modelArrayList.add(new MyModel(
                "Алиш","100","3.5",R.drawable.sauna1
        ));
        modelArrayList.add(new MyModel(
                "Сауна Север","1500$","12/01/2030",R.drawable.sauna2
        ));
        modelArrayList.add(new MyModel(
                "Cауна ЮГ для Алишера","Бесплатно","mala,",R.drawable.sauna3
        ));
        modelArrayList.add(new MyModel(
                "Cdsауна ЮdsГ для Алишdsера","Бесплатsdно","masdla,",R.drawable.sauna1
        ));

        myAdapter = new MyAdapter(this, modelArrayList);

        viewPager.setAdapter(myAdapter);

        viewPager.setPadding(100,0,100,0);
    }

}