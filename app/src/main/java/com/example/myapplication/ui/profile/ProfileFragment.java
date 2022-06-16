package com.example.myapplication.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ProfileFragment extends Fragment {
    TextView textView;
    TextView textView1;
    public String url = "http://justrelax.kz";
    private FragmentProfileBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView = root.findViewById(R.id.user_name);
        textView1 = root.findViewById(R.id.user_phone);
        textView.setText(getUserName(4));
        textView1.setText(getUserEmail(4));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String getUserName(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getUserName.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }

    public String getUserEmail(int id){
        final String[] result = new String[1];
        String[] field = new String[1];
        field[0] = "id";

        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(url + "/getEmail.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result[0] = putData.getResult();
            }
        }
        return result[0];
    }
}