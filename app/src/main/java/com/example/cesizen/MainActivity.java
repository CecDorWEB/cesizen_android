package com.example.cesizen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            Menu menu = navView.getMenu();
            MenuItem connexionItem = menu.findItem(R.id.navigation_connexion);
            if (connexionItem != null) {
                connexionItem.setTitle("Déconnexion");
            }
        }

        // Configuration des destinations principales
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_articles, R.id.navigation_connexion, R.id.navigation_test)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Ajout du Listener pour gérer la navigation correctement
        navView.setOnItemSelectedListener(item -> {
            int currentDestination = navController.getCurrentDestination().getId();
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_connexion && isLoggedIn) {
                // Déconnexion
                //preferences.edit().clear().apply();
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putBoolean("is_logged_in", false);
                editor.apply();

                // Mettre à jour le label du bouton
                MenuItem connexionItem = navView.getMenu().findItem(R.id.navigation_connexion);
                if (connexionItem != null) {
                    connexionItem.setTitle("Connexion");
                }

                // Afficher un message
                Toast.makeText(this, "Déconnecté", Toast.LENGTH_SHORT).show();

                // 🔁 Rediriger vers la page de connexion
                navController.navigate(R.id.navigation_connexion);

                return true; // Permet la navigation vers ConnexionFragment
            }

            // Navigation normale
            if (item.getItemId() == R.id.navigation_home) {
                if (currentDestination != R.id.navigation_home) {
                    navController.popBackStack(R.id.navigation_home, false);
                }
            } else {
                navController.navigate(item.getItemId());
            }

            return true;
        });
    }
    public void updateConnexionMenuLabel() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("is_logged_in", false);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        MenuItem connexionItem = navView.getMenu().findItem(R.id.navigation_connexion);

        if (connexionItem != null) {
            connexionItem.setTitle(isLoggedIn ? "Déconnexion" : "Connexion");
        }
    }
}