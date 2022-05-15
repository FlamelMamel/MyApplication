package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<SaunaPage> {


    public ListAdapter(Context context, ArrayList<SaunaPage> saunaPageArrayList){

        super(context,R.layout.list_item, saunaPageArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SaunaPage saunaPage = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        ImageView imageView = convertView.findViewById(R.id.saunaImage);
        TextView title = convertView.findViewById(R.id.title_sauna);
        TextView visitedTime = convertView.findViewById(R.id.visited);

        imageView.setImageResource(saunaPage.imageId);
        title.setText(saunaPage.title);
        visitedTime.setText(saunaPage.visitedTime);


        return convertView;
    }
}
