package co.k2.newsbits.headlines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.k2.newsbits.R;
import co.k2.newsbits.common.ImageUtils;
import co.k2.newsbits.data.models.Article;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 30/3/2019
 */

public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.HeadlineViewHolder> {

    private final List<Article> articles;
    public static final int TYPE_MAIN = Integer.MAX_VALUE;

    public HeadlinesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == TYPE_MAIN ? R.layout.item_headline_main : R.layout.item_headline;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                layout, parent, false
        );
        return new HeadlineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.subTitle.setText(article.getDescription());
        if (article.getUrlToImage() != null && article.getUrlToImage().startsWith("https://")) {
            holder.thumbnail.setVisibility(View.VISIBLE);
            ImageUtils.loadImageIntoView(holder.thumbnail, article.getUrlToImage(), R.drawable.ic_outline_photo);
        } else {
            holder.thumbnail.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class HeadlineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.subtitle)
        TextView subTitle;

        @BindView(R.id.headline_image)
        ImageView thumbnail;

        public HeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.holder)
        void onHeadlineClick() {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_MAIN;
        }
        return super.getItemViewType(position);
    }

    public static class HlDiffUtil extends DiffUtil.Callback {

        private final List<Article> newArticles;
        private final List<Article> oldArticles;

        public HlDiffUtil(final List<Article> newArticles, final List<Article> oldArticles) {
            this.newArticles = new ArrayList<>(newArticles);
            this.oldArticles = new ArrayList<>(oldArticles);
        }

        @Override
        public int getOldListSize() {
            return oldArticles.size();
        }

        @Override
        public int getNewListSize() {
            return newArticles.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldArticles.get(oldItemPosition).equals(newArticles.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldArticles.get(oldItemPosition).hashCode() == newArticles.get(newItemPosition).hashCode();
        }
    }

    void update(List<Article> newArticles) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new HlDiffUtil(newArticles, articles));
        this.articles.clear();
        this.articles.addAll(newArticles);
        diffResult.dispatchUpdatesTo(this);
    }


}
