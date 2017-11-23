package com.coopmart.kurir.coopkurir.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coopmart.kurir.coopkurir.R;

public class MainMenuFragment extends Fragment {

    public static final String TAG = "Menu Utama";

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(TAG);
        return view;
    }

}
