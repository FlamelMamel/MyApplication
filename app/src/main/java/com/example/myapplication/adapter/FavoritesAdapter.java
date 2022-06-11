package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Favorites;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder>{

    List<Favorites> favorites;

    public FavoritesAdapter(List<Favorites> favorites) {
        this.favorites = favorites;
    }

    public int getItemViewType(final int position) {
        return R.layout.fragments_list;
    }

    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        Favorites state = favorites.get(position);
        holder.sauna.setImageResource(state.getSaunaImg());
        holder.name.setText(state.getName());
        holder.address.setText(state.getAddress());
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }
}