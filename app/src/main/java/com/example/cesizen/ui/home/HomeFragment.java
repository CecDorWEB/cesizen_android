package com.example.cesizen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cesizen.R;
import com.example.cesizen.databinding.FragmentHomeBinding;
import com.example.cesizen.ui.articles.ArticlesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.titlePresentationHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Clique du bouton Articles vers le fragment Articles
        LinearLayout btnArticles = binding.btnArticles; // grâce à ViewBinding

        btnArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Accède à l’activité parente et à la barre de navigation
                BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
                navView.setSelectedItemId(R.id.navigation_articles);
            }
        });

        //Clique du bouton Test vers le fragment Test
        LinearLayout btnTest = binding.btnTest;

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.navigation_test);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}