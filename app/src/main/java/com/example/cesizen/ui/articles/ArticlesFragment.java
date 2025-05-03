package com.example.cesizen.ui.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cesizen.databinding.FragmentArticlesBinding;

public class ArticlesFragment extends Fragment {

    private FragmentArticlesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArticlesViewModel articlesViewModel =
                new ViewModelProvider(this).get(ArticlesViewModel.class);

        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textArticles;
        articlesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}