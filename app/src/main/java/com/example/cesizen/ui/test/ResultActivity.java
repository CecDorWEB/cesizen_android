package com.example.cesizen.ui.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cesizen.R;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score_total", 0);

        TextView scoreView = findViewById(R.id.score_view);
        scoreView.setText("Score final : " + score);
    }
}
