package com.su.conso.estoque.actvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.su.conso.estoque.R;
import com.su.conso.estoque.databinding.ActivityMainBinding;
import com.su.conso.estoque.fragment.HomeFragment;
import com.su.conso.estoque.fragment.ListasFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        habilitarNavegacao();

    }

    @Override
    protected void onStart() {
        home();
        super.onStart();
    }

    public void home(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage,new HomeFragment()).commit();
 }

    private void habilitarNavegacao(){

        binding.buttonNavegation.setOnItemSelectedListener(item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()){
                case R.id.menu_home:
                    fragmentTransaction.replace(R.id.viewPage,new HomeFragment()).commit();
                    return true;

                case R.id.menu_list:
                    fragmentTransaction.replace(R.id.viewPage,new ListasFragment()).commit();
                    return true;
            }
            return false;
        });
    }

}