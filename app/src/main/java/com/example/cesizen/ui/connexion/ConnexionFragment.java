package com.example.cesizen.ui.connexion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cesizen.databinding.FragmentConnexionBinding;

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}