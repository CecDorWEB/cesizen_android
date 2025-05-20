package com.example.cesizen.ui.test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cesizen.R;
import com.example.cesizen.models.RessourceDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder> {

    private List<RessourceDTO> tests;
    private Context context;

    public TestsAdapter(Context context,List<RessourceDTO> tests) {
        this.context = context;
        this.tests = tests;
    }

    // ViewHolder
    public static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView title, headerIntroduction;

        ImageView headerImage;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.test_title);
            headerIntroduction = itemView.findViewById(R.id.test_header_introduction);
            headerImage = itemView.findViewById(R.id.test_header_image);
        }
    }

    @NonNull
    @Override
    public com.example.cesizen.ui.test.TestsAdapter.TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_test, parent, false);
        return new com.example.cesizen.ui.test.TestsAdapter.TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.cesizen.ui.test.TestsAdapter.TestViewHolder holder, int position) {
        RessourceDTO test = tests.get(position);

        holder.title.setText(test.getTitle());
        holder.headerImage.setContentDescription(test.getAltHeaderImage());
        holder.headerIntroduction.setText(test.getHeaderIntroduction());
        holder.headerImage.setContentDescription(test.getAltHeaderImage());


        // Chargement de l’image avec Glide
        Glide.with(context)
                .load(test.getHeaderImage()) // URL de l’image
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                //.skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder_image) // image par défaut si chargement
                .error(R.drawable.ic_error_image) // image en cas d’erreur
                .into(holder.headerImage);

        //Au clique sur le test de son choix
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, TestStartActivity.class);
            intent.putExtra("ARTICLE_ID", test.getId()); // ou autre info utile
            Log.d("Adapter", "Contexte actuel : " + context.getClass().getName());
            v.getContext().startActivity(intent);
            Log.d("Adapter", "ID Article envoyé : " + test.getId());
        });

    }

    @Override
    public int getItemCount() {
        return tests != null ? tests.size() : 0;
    }
}

