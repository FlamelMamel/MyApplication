package com.example.myapplication.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class FavoritesViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        ImageView sauna;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            sauna = itemView.findViewById(R.id.sauna);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
        }
}

