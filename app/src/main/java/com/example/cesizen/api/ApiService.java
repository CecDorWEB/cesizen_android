package com.example.cesizen.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.cesizen.models.RessourceDTO;

public interface ApiService {

    @GET("/ressource/article")
    Call<List<RessourceDTO>> getArticles();
}