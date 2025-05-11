package com.example.cesizen.ui.articles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cesizen.R;
import com.example.cesizen.models.RessourceDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<RessourceDTO> articles;

    public ArticlesAdapter(List<RessourceDTO> articles) {
        this.articles = articles;
    }

    // ViewHolder
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title, headerImage, altHeaderImage, headerIntroduction, publicationDate, updateDate;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            headerImage = itemView.findViewById(R.id.article_header_image);
            altHeaderImage = itemView.findViewById(R.id.article_alt_header_image);
            headerIntroduction = itemView.findViewById(R.id.article_header_introduction);
            publicationDate = itemView.findViewById(R.id.article_publication_date);
            updateDate = itemView.findViewById(R.id.article_update_date);
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
        holder.headerImage.setText(article.getHeaderImage());
        holder.altHeaderImage.setText(article.getAltHeaderImage());
        holder.headerIntroduction.setText(article.getHeaderIntroduction());

        // Convertir les dates si elles ne sont pas nulles
        if (article.getPublicationDate() != null)
            holder.publicationDate.setText(formatDate(article.getPublicationDate()));

        if (article.getUpdateDate() != null)
            holder.updateDate.setText(formatDate(article.getUpdateDate()));
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}
