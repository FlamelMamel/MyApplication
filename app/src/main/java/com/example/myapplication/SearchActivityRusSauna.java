package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchActivityRusSauna extends AppCompatActivity {


    ListView listView;
    String[] bath = {"Rus Bath", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rus_sauna);

        listView = findViewById(R.id.listview_rus_sauna);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bath);
        listView.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nav_menu_rus_sauna, menu);

        MenuItem menuItem = menu.findItem(R.id.nav_search_rus_sauna);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Поиск бани...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}