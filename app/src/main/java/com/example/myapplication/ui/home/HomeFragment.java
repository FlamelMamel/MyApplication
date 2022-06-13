package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CardsAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.CardsModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager viewPager;
    private ArrayList<CardsModel> modelArrayList;
    public CardsAdapter cardsAdapter;
    public String url = "https://192.168.4.9/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

    public String getName(int id){
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] res = {null};
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id";

                String[] data = new String[1];
                data[0] = String.valueOf(id);

                PutData putData = new PutData(url + "/justRelax/getName.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        res[0] = result;
                    }
                }
                //End Write and Read data with URL
            }
        });
        return res[0];
    }
}