package com.example.cesizen.ui.articles;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.cesizen.R;
import com.example.cesizen.api.ApiClient;
import com.example.cesizen.api.ApiService;
import com.example.cesizen.models.ParagraphDTO;
import com.example.cesizen.models.RessourceDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class ArticleReadActivity extends AppCompatActivity {
    private LinearLayout paragraphsContainer;

    TextView title, headerIntroduction, publicationDate;

    ImageView headerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_article_read);

        title = findViewById(R.id.article_title);
        headerIntroduction = findViewById(R.id.article_intro);
        headerImage = findViewById(R.id.article_image);
        //publicationDate = findViewById(R.id.article_date);

        paragraphsContainer = findViewById(R.id.paragraphs_container);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ARTICLE_ID")) {
            long ressourceId = intent.getLongExtra("ARTICLE_ID", -1);
            long articleId = intent.getLongExtra("ARTICLE_ID", -1);
            Log.d("API", "Je suis dans l'activité ID Article reçu : " + ressourceId);
            if (ressourceId != -1) {
                Log.d("API", "Avant fetchArticleHeader avec ID : " + ressourceId);
                fetchArticleHeader(ressourceId);
                fetchParagraphs(articleId);
            }
        } else {
            Log.e("API", "L'Intent ne contient pas ARTICLE_ID !");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchArticleHeader(Long ressourceId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        Call<RessourceDTO> call = apiService.getArticleById(ressourceId);

        call.enqueue(new Callback<RessourceDTO>() {
            @Override
            public void onResponse(Call<RessourceDTO> call, Response<RessourceDTO> response) {
                if (response.isSuccessful() && response.body() != null) {

                    RessourceDTO ressource = response.body();

                    // Mettre à jour les vues
                    title.setText(ressource.getTitle());
                    headerIntroduction.setText(ressource.getHeaderIntroduction());
                    headerImage.setContentDescription(ressource.getAltHeaderImage());

                    Glide.with(ArticleReadActivity.this)
                            .load(ressource.getHeaderImage())
                            .placeholder(R.drawable.ic_placeholder_image)
                            .error(R.drawable.ic_error_image) // image en cas d’erreur
                            .into(headerImage);

                } else {
                    Log.e("API", "Erreur dans la réponse : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RessourceDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fetchParagraphs(Long articleId) {
        Log.d("API", "Je suis dans la fonction FetchParagraph : " + articleId);

        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        Call<List<ParagraphDTO>> call = apiService.getParagraphsByArticleId(articleId);
        call.enqueue(new Callback<List<ParagraphDTO>>() {
            @Override
            public void onResponse(Call<List<ParagraphDTO>> call, Response<List<ParagraphDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ParagraphDTO> paragraphList = response.body();

                    Log.d("API", "Nombre de paragraphes reçus : " + paragraphList.size());

                    for (ParagraphDTO paragraph : paragraphList) {
                        Log.d("API", "Paragraphe : " + paragraph.getTitle() + " - " + paragraph.getBody());
                    }

                    for (ParagraphDTO paragraph : paragraphList) {
                        // Titre du paragraphe si not null
                        if (paragraph.getTitle() != null && !paragraph.getTitle().trim().isEmpty()) {
                            TextView titleView = new TextView(ArticleReadActivity.this);
                            titleView.setText(paragraph.getTitle());
                            titleView.setTextSize(20);
                            titleView.setTextColor(Color.BLACK);
                            titleView.setTypeface(null, Typeface.BOLD);
                            titleView.setPadding(0, 24, 0, 8);

                            // Ajout uniquement si le titre est valide
                            paragraphsContainer.addView(titleView);
                        }

                        // Contenu du paragraphe
                        TextView contentView = new TextView(ArticleReadActivity.this);
                        contentView.setText(paragraph.getBody());
                        contentView.setTextSize(16);
                        contentView.setTextColor(Color.DKGRAY);
                        contentView.setPadding(0, 0, 0, 16);

                        // Image du paragraphe (si présente)
                        ImageView imageView = null;
                        if (paragraph.getVisualSupport() != null && !paragraph.getVisualSupport().isEmpty()) {
                            imageView = new ImageView(ArticleReadActivity.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    500
                            ));
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            imageView.setContentDescription(paragraph.getAltVisualSupport());

                            // Chargement de l’image avec Glide (ou autre lib)
                            Glide.with(ArticleReadActivity.this)
                                    .load(paragraph.getVisualSupport())
                                    .placeholder(R.drawable.ic_placeholder_image)
                                    .into(imageView);
                        }

                        // Ajout au layout
                        paragraphsContainer.addView(contentView);
                        if (imageView != null) {
                            paragraphsContainer.addView(imageView);
                        }
                    }
                } else {
                    Log.e("API", "Erreur dans la réponse : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ParagraphDTO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}