package com.example.labo2.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labo2.R;
import com.example.labo2.models.StoresViewModel;
import com.example.labo2.room.Banner;
import com.example.labo2.room.CustomDatabaseRoom;
import com.example.labo2.room.Store;

import java.util.List;

public class StoreFragment  extends Fragment {

    public final static String ARG_ID = "id";
    int idCourant = -1;
    StoresViewModel storesViewModel;
    TextView labelName = null;
    TextView labelAddresse = null;
    TextView labelSite = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        au cas ou l application se met en mode portrait ou landscape
        if(savedInstanceState != null){
            idCourant = savedInstanceState.getInt(ARG_ID);
        }
        View viewCourante = inflater.inflate(R.layout.activity_store, container, false);
        labelName = viewCourante.findViewById(R.id.labelName);
        labelAddresse = viewCourante.findViewById(R.id.labelAddresse);
        labelSite = viewCourante.findViewById(R.id.labelSite);

        return viewCourante;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            mettreAJourInfoView(args.getInt(ARG_ID));
        } else if (idCourant != -1) {
            // Set article based on saved instance state defined during onCreateView
            mettreAJourInfoView(idCourant);
        }
    }
    public void mettreAJourInfoView(int id) {
        storesViewModel = ViewModelProviders.of(this).get(StoresViewModel.class);
        storesViewModel.getAllStoresForBanner(id).observe(this, new Observer<List<Store>>(){
            @Override
            public void onChanged(@Nullable List<Store> stores){
//                faire pour afficher les infos dans un listAdapter
                if(labelName != null) {

                    labelName.setText(stores.get(0).getName());
                    labelAddresse.setText(stores.get(0).getAddress());
                    labelSite.setText(stores.get(0).getWebsite());

                }

            }
        });
        idCourant = id;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_ID, idCourant);
    }
}
