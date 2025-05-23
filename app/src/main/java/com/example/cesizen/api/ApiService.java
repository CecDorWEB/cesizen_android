package com.example.cesizen.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.example.cesizen.models.ParagraphDTO;
import com.example.cesizen.models.QuestionDTO;
import com.example.cesizen.models.RessourceDTO;
import com.example.cesizen.models.ResultDTO;

public interface ApiService {

    //Récupérer toutes les ressources de type Articles
    @GET("/ressource/article")
    Call<List<RessourceDTO>> getArticles();

    //récupérer le header d'un seul article
    @GET("/ressource/{ressourceId}")
    Call<RessourceDTO> getArticleById(@retrofit2.http.Path("ressourceId") Long ressourceId);

    //Récupérer les paragraphes d'un article
    @GET("/ressource/article/{articleId}/paragraph")
    Call<List<ParagraphDTO>> getParagraphsByArticleId(@retrofit2.http.Path("articleId") Long articleId);

    //Récupérer tous les tests
    @GET("/ressource/test")
    Call<List<RessourceDTO>> getTests();

    //Récupérer les questions liées au test
    @GET("/ressource/test/{testId}/question")
    Call<List<QuestionDTO>> getQuestionById(@retrofit2.http.Path("testId") Long testId);

    //Récupérer les textes de résultat en fonction du score de l'utilisateur
    @GET("/results/search")
    Call<ResultDTO> getResult(@Query("ressourceId") Long ressourceId, @Query("score") Integer score);

    // Connexion utilisateur
    @POST("/user/login")
    Call<Map<String, Object>> login(@Body Map<String, String> loginInfo);
}