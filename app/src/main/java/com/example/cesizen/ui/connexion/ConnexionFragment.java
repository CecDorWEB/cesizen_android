package com.example.cesizen.ui.connexion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cesizen.MainActivity;
import com.example.cesizen.R;
import com.example.cesizen.api.ApiClient;
import com.example.cesizen.api.ApiService;
import com.example.cesizen.databinding.FragmentConnexionBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionFragment extends Fragment {

    private FragmentConnexionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConnexionViewModel connexionViewModel =
                new ViewModelProvider(this).get(ConnexionViewModel.class);

        binding = FragmentConnexionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textConnexion;
        connexionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // 1. Récupération des valeurs quand le bouton est cliqué
        binding.buttonLogin.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2. Préparation de la requête
            Map<String, String> loginData = new HashMap<>();
            loginData.put("email", email);
            loginData.put("password", password);

            // 3. Appel Retrofit
            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
            Call<Map<String, Object>> call = apiService.login(loginData);

            call.enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    if (response.isSuccessful()) {
                        Map<String, Object> userData = response.body();
                        String firstname = (String) userData.get("firstname");
                        Toast.makeText(getContext(), "Bienvenue " + firstname, Toast.LENGTH_SHORT).show();

                        SharedPreferences preferences = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("is_logged_in", true);
                        editor.apply();

                        ((MainActivity) requireActivity()).updateConnexionMenuLabel();


                        // Redirection vers le HomeFragment
                        NavController navController = NavHostFragment.findNavController(ConnexionFragment.this);
                        navController.navigate(R.id.navigation_home);

                    } else if (response.code() == 401) {
                        Toast.makeText(getContext(), "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 403) {
                        Toast.makeText(getContext(), "Compte suspendu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Erreur : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                    Toast.makeText(getContext(), "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}