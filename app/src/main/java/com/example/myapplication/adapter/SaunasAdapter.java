package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BookActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Saunas;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class SaunasAdapter extends RecyclerView.Adapter<SaunasAdapter.SaunasViewHolder>{


    private static List<Saunas> favorites;
    private final OnSaunaClickListener onSaunaClickListener;
    private final LayoutInflater inflater;
    private Context context;
    private List<Saunas> saunas = new ArrayList<>();
    Saunas sauna;

    public interface OnSaunaClickListener{
        void onSaunaClick(int position);
    }

    public SaunasAdapter(Context context, List<Saunas> sauna, OnSaunaClickListener onSaunaClickListener) {
        this.saunas = sauna;
        this.onSaunaClickListener = onSaunaClickListener;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getItemViewType(final int position) {
        return R.layout.fragments_list;
    }

    public SaunasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SaunasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SaunasViewHolder holder, int position) {
        sauna = saunas.get(position);
        holder.sauna.setBackgroundResource(sauna.getSaunaImg());
        holder.name.setText(sauna.getName());
        holder.address.setText(sauna.getAddress());
        holder.price.setText(sauna.getPrice());
        holder.description.setText(sauna.getDescription());

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fav.setBackgroundResource(R.drawable.ic_baseline_turned_in_24);
//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Starting Write and Read data with URL
//                        //Creating array for parameters
//                        String[] field = new String[5];
//                        field[0] = "id";
//                        field[1] = "title";
//                        field[2] = "address";
//                        field[3] = "price";
//                        field[4] = "description";
//
//                        String[] data = new String[5];
//                        data[0] = String.valueOf(sauna.getId());
//                        data[1] = sauna.getName();
//                        data[2] = sauna.getAddress();
//                        data[3] = String.valueOf(sauna.getPrice());
//                        data[4] = sauna.getDescription();
//
//                        PutData putData = new PutData("https://justrelax.kz/addFavorite.php", "POST", field, data);
//                        if (putData.startPut()) {
//                            if (putData.onComplete()) {
//
//                            }
//                        }
//                        //End Write and Read data with URL
//                    }
//                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onSaunaClickListener.onSaunaClick(holder.getAdapterPosition());
            }
        });
    }

    private Drawable ImageOperations(Context ctx, String saveFilename, String... url) {
        try {
            String urlLink = url[0];
            InputStream is = new URL(urlLink).openStream();
            Drawable d = Drawable.createFromStream(is, saveFilename);
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }

    public static List<Saunas> getFavorites(){
        return favorites;
    }

    @Override
    public int getItemCount() {
        return saunas.size();
    }

    public class SaunasViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        FrameLayout sauna;
        TextView price;
        TextView description;
        Button fav;
        Button book;
        SaunasAdapter.OnSaunaClickListener onSaunaClickListener;

        public SaunasViewHolder(@NonNull View itemView) {
            super(itemView);

            sauna = itemView.findViewById(R.id.sauna);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            fav = itemView.findViewById(R.id.fav);
            book = itemView.findViewById(R.id.buttonBook);

        }

    }
}