package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.model.CardsModel;

import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends PagerAdapter {

    ArrayList<CardsModel> modelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public CardsAdapter(Context context, ArrayList<CardsModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.card_item, view, false);

        ImageView bannerTV = myImageLayout.findViewById(R.id.bannerTv);
        TextView titleTV = myImageLayout.findViewById(R.id.titleTv);
        TextView descriptionTV = myImageLayout.findViewById(R.id.descriptionTv);
        TextView dateTV = myImageLayout.findViewById(R.id.dateTv);
        titleTV.setText(modelArrayList.get(position).getTitle());
        descriptionTV.setText(modelArrayList.get(position).getDescription());
        dateTV.setText(modelArrayList.get(position).getDate());
        bannerTV.setImageResource(modelArrayList.get(position).getImage());

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


}