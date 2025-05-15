package com.example.cesizen.ui.articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cesizen.R;
import com.example.cesizen.models.RessourceDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<RessourceDTO> articles;
    private Context context;

    public ArticlesAdapter(Context context,List<RessourceDTO> articles) {
        this.context = context;
        this.articles = articles;
    }

    // ViewHolder
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title, headerIntroduction, publicationDate, updateDate;

        ImageView headerImage;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            headerIntroduction = itemView.findViewById(R.id.article_header_introduction);
            publicationDate = itemView.findViewById(R.id.article_publication_date);
            updateDate = itemView.findViewById(R.id.article_update_date);
            headerImage = itemView.findViewById(R.id.article_header_image);
        }
    }

    private String formatDate(Date date) {
        if (date == null) return "Date invalide";

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return outputFormat.format(date);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        RessourceDTO article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.headerImage.setContentDescription(article.getAltHeaderImage());
        holder.headerIntroduction.setText(article.getHeaderIntroduction());
        holder.headerImage.setContentDescription(article.getAltHeaderImage());

        // Convertir les dates si elles ne sont pas nulles
        if (article.getPublicationDate() != null)
            holder.publicationDate.setText(formatDate(article.getPublicationDate()));

        if (article.getUpdateDate() != null)
            holder.updateDate.setText(formatDate(article.getUpdateDate()));

        // Chargement de l’image avec Glide
        Glide.with(context)
                .load(article.getHeaderImage()) // URL de l’image
                .placeholder(R.drawable.ic_placeholder_image) // image par défaut si chargement
                .error(R.drawable.ic_error_image) // image en cas d’erreur
                .into(holder.headerImage);
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}
