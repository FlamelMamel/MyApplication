package com.example.myapplication.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SaunaActivity;
import com.example.myapplication.adapter.CardsAdapter;
import com.example.myapplication.adapter.SaunasAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.CardsModel;
import com.example.myapplication.model.Saunas;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements SaunasAdapter.OnSaunaClickListener {

    private FragmentHomeBinding binding;

    private ViewPager viewPager;
    private ArrayList<CardsModel> modelArrayList;
    public CardsAdapter cardsAdapter;
    public String url = "http://justrelax.kz";
    String id;

    public RecyclerView saunasRecycler;

    List<Saunas> favorites = new ArrayList<>();
    Saunas favorite;

    public SaunasAdapter.OnSaunaClickListener onClickListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        saunasRecycler = root.findViewById(R.id.homeRecycler);
        setInitialData(favorites);

        SaunasAdapter.OnSaunaClickListener saunaClickListener = new SaunasAdapter.OnSaunaClickListener() {
            @Override
            public void onSaunaClick(int position) {
                favorite = favorites.get(position);

                Intent intent = new Intent(getActivity(), SaunaActivity.class);
                intent.putExtra("name", favorite.getName());
                startActivity(intent);
            }
        };

        saunasRecycler.setHasFixedSize(true);
        SaunasAdapter adapter = new SaunasAdapter(getActivity(), favorites, saunaClickListener);

        saunasRecycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        saunasRecycler.setAdapter(adapter);

        viewPager = root.findViewById(R.id.viewPager);
        loadCards();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return root;
    }

    private void setInitialData(List<Saunas> favorites) {
        int i = 1;
        while (!getTitleFromData(i).equals("")) {
            String name = getTitleFromData(i);
            String address = getAddressFromData(i);
            String price = getPriceFromData(i);
            String description = getDescriptionFromData(i);
            favorites.add(new Saunas(i, name, address, R.drawable.untitled, price + " tg/hour", description));
            i++;
        }
    }

    protected Bitmap doInBackground(String... urls) {
        String imageURL=urls[0];
        Bitmap bimage=null;
        try {
            InputStream in=new java.net.URL(imageURL).openStream();
            bimage=BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimage;
    }

    @Override
    public void onSaunaClick(int position) {
        Intent intent = new Intent(getActivity(), SaunaActivity.class);
        intent.putExtra("name", favorite.getName());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadCards() {
        modelArrayList = new ArrayList<>();
        modelArrayList.add(new CardsModel(
                "Алиш","100","3.5",R.drawable.sauna1
        ));
        modelArrayList.add(new CardsModel(
                "Сауна Север","1500$","12/01/2030",R.drawable.sauna2
        ));
        modelArrayList.add(new CardsModel(
                "Cауна ЮГ для Алишера","Бесплатно","mala,",R.drawable.sauna3
        ));
        modelArrayList.add(new CardsModel(
                "Cdsауна ЮdsГ для Алишdsера","Бесплатsdно","masdla,",R.drawable.sauna1
        ));

        cardsAdapter = new CardsAdapter(getActivity(), modelArrayList);

        viewPager.setAdapter(cardsAdapter);

        viewPager.setPadding(100,0,100,0);
    }

    public String getTitleFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getName.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getAddressFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getAddress.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getPriceFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getPrice.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getDescriptionFromData(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getDescription.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

}