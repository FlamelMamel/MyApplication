package com.example.myapplication.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import org.w3c.dom.Text;

public class CardsViewHolder extends RecyclerView.ViewHolder {
    ImageView bannerTV;
    TextView titleTV;
    TextView descriptionTV;
    TextView dateTV;

    public CardsViewHolder(@NonNull View itemView) {
        super(itemView);

        bannerTV = itemView.findViewById(R.id.bannerTv);
        titleTV = itemView.findViewById(R.id.titleTv);
        descriptionTV = itemView.findViewById(R.id.descriptionTv);
        dateTV = itemView.findViewById(R.id.dateTv);
    }
}
