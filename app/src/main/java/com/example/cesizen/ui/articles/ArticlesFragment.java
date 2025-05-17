package com.example.cesizen.ui.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cesizen.databinding.FragmentArticlesBinding;
import com.example.cesizen.api.ApiClient;
import com.example.cesizen.api.ApiService;
import com.example.cesizen.models.RessourceDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesFragment extends Fragment {

    private FragmentArticlesBinding binding;
    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private List<RessourceDTO> articleList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewArticles;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ArticlesAdapter(requireActivity(), articleList); // Passe la liste initiale (vide)
        recyclerView.setAdapter(adapter);

        fetchArticles(); // Appel à l'API

        return root;
    }

    private void fetchArticles() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<RessourceDTO>> call = apiService.getArticles();

        call.enqueue(new Callback<List<RessourceDTO>>() {
            @Override
            public void onResponse(Call<List<RessourceDTO>> call, Response<List<RessourceDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articleList.clear();
                    articleList.addAll(response.body());
                    adapter.notifyDataSetChanged(); // Notifie l'adapter
                } else {
                    Toast.makeText(getContext(), "Erreur de réponse serveur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RessourceDTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Erreur : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
