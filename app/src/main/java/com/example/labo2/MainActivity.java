package com.example.labo2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.labo2.fragment.BannerListFragment;
import com.example.labo2.fragment.PreferenceFragment;
import com.example.labo2.fragment.StoreFragment;
import com.example.labo2.room.Banner;
import com.example.labo2.room.CustomDatabaseRoom;

import java.util.List;

public class MainActivity extends AppCompatActivity
                          implements BannerListFragment.OnClickBannerListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        aller chercher le DrawerLayout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        aller chercher ma toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        mettre l icone de menu sur mon toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
//        finaliser mon drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//  aller chercher navigation_view
        NavigationView navView = findViewById(R.id.nav_view);
//        voir la surcharge de onNavigationItemSelectedListener plus bas
        navView.setNavigationItemSelectedListener(this);

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
    public void onBannerSelected(int idCourant) {
        // Redefinir la methode pour le behavior -> quand un usager selectionne une banner

        // Prendre le fragment du activity layout
        StoreFragment storeFragment = (StoreFragment) getSupportFragmentManager().findFragmentById(R.id.storeFragment);

        if (storeFragment != null) {
            // Si il existe -> on est en landscape -> on l update
            storeFragment.getStore(idCourant);

        } else {
            // Si il n est pas la -> on le modifie avec le nouveau fragment et on lui passe les infos voulues
            StoreFragment nouveauFragment = new StoreFragment();
            Bundle args = new Bundle();
            args.putInt(StoreFragment.ARG_ID, idCourant);
            nouveauFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Change le fragment courant pour le nouveau fragment  + rajouter addToBackStack pour naviguer en arriere
            transaction.replace(R.id.contenant, nouveauFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        si je selectionne mon item settings
        if(menuItem.getItemId() == R.id.itemSettings){

            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
//        fermer menu navigation_view
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
