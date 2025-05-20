package com.example.cesizen.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cesizen.R;
import com.example.cesizen.api.ApiClient;
import com.example.cesizen.api.ApiService;
import com.example.cesizen.models.AnswerDTO;
import com.example.cesizen.models.QuestionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    TextView question, rule;
    private LinearLayout answerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_test);
        question = findViewById(R.id.question_title);
        rule = findViewById(R.id.rule_title);
        answerContainer = findViewById(R.id.answer_container);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("TEST_ID")) {
            long testId = intent.getLongExtra("TEST_ID", -1);
            Log.d("API", "Je suis dans l'activité ID Test reçu : " + testId);
            if (testId != -1) {
                Log.d("API", "Avant fetchArticleHeader avec ID : " + testId);
                fetchQuestion(testId);
            }
        } else {
            Log.e("API", "L'Intent ne contient pas TEST_ID !");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.test_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchQuestion(Long testId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        Call<List<QuestionDTO>> call = apiService.getQuestionById(testId);

        call.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    QuestionDTO questionDto = response.body().get(0);

                    // Affiche la question et la règle
                    question.setText(questionDto.getQuestion());
                    rule.setText(questionDto.getRule());

                    // Nettoie les réponses précédentes s'il y en avait
                    answerContainer.removeAllViews();

                    // Ajoute chaque réponse dynamiquement
                    List<AnswerDTO> answers = questionDto.getListOfAnswers();
                    if (answers != null) {
                        for (AnswerDTO answer : answers) {
                            CheckBox checkBox = new CheckBox(TestActivity.this);
                            checkBox.setText(answer.getTitle());
                            answerContainer.addView(checkBox);
                        }
                    } else{
                        Log.e("API", "Erreur dans la réponse : " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Log.e("API", "Échec de l'appel API : " + t.getMessage());
            }
        });
    }

}
