package com.example.labo2.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labo2.R;
import com.example.labo2.databinding.ActivityStoreBinding;
import com.example.labo2.models.StoresViewModel;
import com.example.labo2.room.Banner;
import com.example.labo2.room.CustomDatabaseRoom;
import com.example.labo2.room.Store;

import java.util.List;

public class StoreFragment  extends Fragment {

    public final static String ARG_ID = "id";
    int idCourant = -1;
    StoresViewModel storesViewModel;
    ActivityStoreBinding binding;
//    private Store storeCourant = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        au cas ou l application se met en mode portrait ou landscape
        if(savedInstanceState != null){
            idCourant = savedInstanceState.getInt(ARG_ID);
        }
//        aller chercher l id passer en argument
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            getStore(args.getInt(ARG_ID));
        } else if (idCourant != -1) {
            // Set article based on saved instance state defined during onCreateView
            getStore(idCourant);
        }
//        bind l objet store a la vue
        binding = DataBindingUtil.inflate( inflater, R.layout.activity_store, container, false);
        View viewCourante = binding.getRoot();

        return viewCourante;
    }

    public void getStore(int id) {

        storesViewModel = ViewModelProviders.of(this).get(StoresViewModel.class);
        storesViewModel.getAllStoresForBanner(id).observe(this, new Observer<List<Store>>(){
            @Override
            public void onChanged(@Nullable List<Store> stores){
                if(stores.get(0) != null){
                    binding.setStore(stores.get(0));
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
