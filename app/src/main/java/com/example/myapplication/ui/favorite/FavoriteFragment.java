package com.example.myapplication.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentFavoriteBinding;
import com.example.myapplication.model.Favorites;

import com.example.myapplication.adapter.FavoritesAdapter;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    public RecyclerView favoritesRecycler;

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Favorites> favorites = new ArrayList<>();
        setInitialData(favorites);

        favoritesRecycler = root.findViewById(R.id.list);
        favoritesRecycler.setHasFixedSize(true);
        favoritesRecycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        favoritesRecycler.setAdapter(new FavoritesAdapter(favorites));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setInitialData(List<Favorites> favorites){

        favorites.add(new Favorites ("Russian", "Kabanbay Batyr", R.drawable.sauna_test1));
        favorites.add(new Favorites ("Japan", "Uly Dala", R.drawable.jap_ban));
    }
}