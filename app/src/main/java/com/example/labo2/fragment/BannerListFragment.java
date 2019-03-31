package com.example.labo2.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.labo2.MainActivity;
import com.example.labo2.models.BannersViewModel;
import com.example.labo2.room.Banner;
import com.example.labo2.room.CustomDatabaseRoom;

import java.util.List;

public class BannerListFragment extends ListFragment {

    private Banner[] values = {};
    BannersViewModel bannersViewModel;
    OnClickBannerListener callback;


    public interface OnClickBannerListener{
        public void onBannerSelected(int idCourant);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        aller chercher les infos du room et les mettres dans un adapter
        bannersViewModel = ViewModelProviders.of(this).get(BannersViewModel.class);
        bannersViewModel.getBannerList().observe(this, new Observer<List<Banner>>(){
            @Override
            public void onChanged(@Nullable List<Banner> banners){
            setListAdapter(new ArrayAdapter<Banner>( getActivity(), android.R.layout.simple_list_item_1, banners) );
            }
        });
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            callback = (OnClickBannerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " on doit implementer le onClickBannerListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
//        lance onBannerSelected et set l item a checked
        Banner bannerCourant = (Banner) getListView().getItemAtPosition(position);
        callback.onBannerSelected(bannerCourant.getId());

        getListView().setItemChecked(position, true);
    }

}
