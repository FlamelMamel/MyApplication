package com.example.myapplication.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SaunaActivity;
import com.example.myapplication.adapter.FavoritesAdapter;
import com.example.myapplication.databinding.FragmentFavoriteBinding;
import com.example.myapplication.model.Saunas;


import com.example.myapplication.adapter.SaunasAdapter;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements SaunasAdapter.OnSaunaClickListener {
    public RecyclerView favoritesRecycler;

    private FragmentFavoriteBinding binding;
    List<Saunas> favorites = new ArrayList<>();
    Saunas favorite;
    public String url = "http://justrelax.kz";

    public SaunasAdapter.OnSaunaClickListener onClickListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favoritesRecycler = root.findViewById(R.id.list);

        FavoritesAdapter.OnSaunaClickListener saunaClickListener = new FavoritesAdapter.OnSaunaClickListener() {
            @Override
            public void onSaunaClick(int position) {
                favorite = favorites.get(position);

                Intent intent = new Intent(getActivity(), SaunaActivity.class);
                intent.putExtra("name", favorite.getName());
                startActivity(intent);
            }
        };

        setInitialData(favorites);

        favoritesRecycler.setHasFixedSize(true);
        FavoritesAdapter adapter = new FavoritesAdapter(getActivity(), favorites, saunaClickListener);

        favoritesRecycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        favoritesRecycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSaunaClick(int position) {
        Intent intent = new Intent(getActivity(), SaunaActivity.class);
        intent.putExtra("name", favorite.getName());
        startActivity(intent);
    }
    private void setInitialData(List<Saunas> favorites) {
        int i = 1;
        while (!getTitleFromData(i).equals("")) {
            String name = getTitleFromData(i);
            String address = getAddressFromData(i);
            String price = getPriceFromData(i);
            String description = getDescriptionFromData(i);
            favorites.add(new Saunas(1, name, address, R.drawable.untitled, price + " tg/hour", description));
            i++;
        }
    }


    public String getTitleFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getNameFavorite.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getAddressFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getAddressFavorite.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getPriceFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getPriceFavorite.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getDescriptionFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getDescriptionFavorite.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

}