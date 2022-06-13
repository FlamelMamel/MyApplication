package com.example.myapplication.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView categoryTitle;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryTitle = itemView.findViewById(R.id.categoryTitle);
    }
}
