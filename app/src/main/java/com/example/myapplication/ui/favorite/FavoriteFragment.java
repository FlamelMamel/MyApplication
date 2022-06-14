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
    String urlrelax = "http://justrelax.kz/";

    public SaunasAdapter.OnSaunaClickListener onClickListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SaunasAdapter.getFavorites();

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
    private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, saveFilename);
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }


    public String getName(int id){
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] res = {null};
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id";

                String[] data = new String[1];
                data[0] = String.valueOf(id);

                PutData putData = new PutData(urlrelax + "/getName.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        res[0] = result;
                    }
                }
                //End Write and Read data with URL
            }
        });
        return res[0];
    }

}