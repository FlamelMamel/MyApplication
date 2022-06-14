package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Saunas;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>{


    private final OnSaunaClickListener onSaunaClickListener;
    private final LayoutInflater inflater;
    Context context;
    private List<Saunas> favorites = new ArrayList<>();
    Saunas favorite;

    public interface OnSaunaClickListener{
        void onSaunaClick(int position);
    }

    public FavoritesAdapter(Context context, List<Saunas> favorites, OnSaunaClickListener onSaunaClickListener) {
        this.favorites = favorites;
        this.onSaunaClickListener = onSaunaClickListener;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getItemViewType(final int position) {
        return R.layout.fragments_list_favorite;
    }

    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.FavoritesViewHolder holder, int position) {
        favorite = favorites.get(position);
        holder.sauna.setBackgroundResource(favorite.getSaunaImg());
        holder.name.setText(favorite.getName());
        holder.address.setText(favorite.getAddress());
        holder.price.setText(favorite.getPrice());
        holder.description.setText(favorite.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onSaunaClickListener.onSaunaClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        FrameLayout sauna;
        TextView price;
        TextView description;
        FavoritesAdapter.OnSaunaClickListener onSaunaClickListener;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            sauna = itemView.findViewById(R.id.sauna);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
        }

    }
}