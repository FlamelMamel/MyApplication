package com.example.myapplication.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SaunaActivity;
import com.example.myapplication.databinding.FragmentFavoriteBinding;
import com.example.myapplication.model.Favorites;


import com.example.myapplication.adapter.FavoritesAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoritesAdapter.OnSaunaClickListener {
    public RecyclerView favoritesRecycler;

    private FragmentFavoriteBinding binding;
    List<Favorites> favorites = new ArrayList<>();
    Favorites favorite;

    public FavoritesAdapter.OnSaunaClickListener onClickListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setInitialData(favorites);

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

    private void setInitialData(List<Favorites> favorites){
        favorites.add(new Favorites ("Russian", "Kabanbay Batyr", R.drawable.untitled, "5000tg/hour","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        favorites.add(new Favorites ("Japan", "Uly Dala", R.drawable.jap_ban, "6000tg/hour", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        favorites.add(new Favorites ("Kotak", "Am Dala", R.drawable.jap_ban, "6939tg/hour", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
    }


    @Override
    public void onSaunaClick(int position) {
        Intent intent = new Intent(getActivity(), SaunaActivity.class);
        intent.putExtra("name", favorite.getName());
        startActivity(intent);
    }
}