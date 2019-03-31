package com.example.labo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.labo2.fragment.PreferenceFragment;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//placer le fragment settings dans le container de l activity
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenant_settings, new PreferenceFragment()).commit();
    }
}
