package com.example.cesizen;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cesizen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Configuration des destinations principales
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_articles, R.id.navigation_connexion)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Ajout du Listener pour gérer la navigation correctement
        navView.setOnItemSelectedListener(item -> {
            int currentDestination = navController.getCurrentDestination().getId();

            // Si l'utilisateur clique sur "Home" et qu'il n'y est pas déjà
            if (item.getItemId() == R.id.navigation_home) {
                if (currentDestination != R.id.navigation_home) {
                    navController.popBackStack(R.id.navigation_home, false);
                }
            } else {
                // Navigation normale pour les autres boutons
                navController.navigate(item.getItemId());
            }
            return true;
        });
    }

}