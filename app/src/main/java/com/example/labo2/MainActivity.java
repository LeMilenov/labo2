package com.example.labo2;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.labo2.fragment.BannerListFragment;
import com.example.labo2.fragment.StoreFragment;
import com.example.labo2.room.Banner;
import com.example.labo2.room.CustomDatabaseRoom;

import java.util.List;

public class MainActivity extends FragmentActivity
                          implements BannerListFragment.OnClickBannerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        voir orientation app
        if (findViewById(R.id.contenant) != null) {

            if (savedInstanceState != null) {
                return;
            }

            BannerListFragment fragmentListe = new BannerListFragment();

            // Passer l id recu dans mon intent au fragmentListe
            fragmentListe.setArguments(getIntent().getExtras());

            // rajouter fragment liste a mon frameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenant, fragmentListe).commit();
        }
    }

    @Override
    public void onBannerSelected(int idCOurant) {
        // Redefinir la methode pour le behavior -> quand un usager selectionne une banner

        // Prendre le fragment du activity layout
        StoreFragment storeFragment = (StoreFragment) getSupportFragmentManager().findFragmentById(R.id.storeFragment);

        if (storeFragment != null) {
            // Si il existe -> on est en landscape -> on l update
            storeFragment.mettreAJourInfoView(idCOurant);

        } else {
            // Si il n est pas la -> on le modifie avec le nouveau fragment et on lui passe les infos voulues
            StoreFragment nouveauFragment = new StoreFragment();
            Bundle args = new Bundle();
            args.putInt(StoreFragment.ARG_ID, idCOurant);
            nouveauFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Change le fragment courant pour le nouveau fragment  + rajouter addToBackStack pour naviguer en arriere
            transaction.replace(R.id.contenant, nouveauFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
