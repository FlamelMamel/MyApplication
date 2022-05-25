package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SearchActivityEastSauna;
import com.example.myapplication.SearchActivityFinSauna;
import com.example.myapplication.SearchActivityJapSauna;
import com.example.myapplication.SearchActivityRusSauna;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        int imageId = context.getResources().getIdentifier(courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, SearchActivityFinSauna.class);
                    context.startActivity(intent);
                }
                else if (position == 1) {
                    Intent intent = new Intent(context, SearchActivityJapSauna.class);
                    context.startActivity(intent);
                }
                else if (position == 2) {
                    Intent intent = new Intent(context, SearchActivityEastSauna.class);
                    context.startActivity(intent);
                }
                else if (position == 3) {
                    Intent intent = new Intent(context, SearchActivityRusSauna.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder {

        ImageView courseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.courseImage);

        }
    }

}
