package com.example.cesizen.ui.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cesizen.R;
import com.example.cesizen.api.ApiClient;
import com.example.cesizen.api.ApiService;
import com.example.cesizen.models.ResultDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score_total", 0);
        long ressourceId = getIntent().getLongExtra("ressource_id", -1);

        // 🖋️ Affichage immédiat du score
        TextView scoreView = findViewById(R.id.score_view);
        scoreView.setText("Score final : " + score);

        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<ResultDTO> call = apiService.getResult(ressourceId, score);

        call.enqueue(new Callback<ResultDTO>() {
            @Override
            public void onResponse(Call<ResultDTO> call, Response<ResultDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResultDTO result = response.body();

                    TextView titleResult = findViewById(R.id.title_result);
                    TextView contentResult = findViewById(R.id.content_result);

                    titleResult.setText(result.getTitle());
                    contentResult.setText(result.getContent());
                } else {
                    Log.e("API", "Erreur dans la réponse du résultat : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResultDTO> call, Throwable t) {
                Log.e("API", "Erreur réseau : " + t.getMessage());
            }
        });
    }
}