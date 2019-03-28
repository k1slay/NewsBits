package co.k2.newsbits.data.models;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import co.k2.newsbits.common.Constants;
import co.k2.newsbits.common.Utils;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Entity(tableName = Constants.ARTICLE_CACHE_TABLE_NAME)
public class Article {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @TypeConverters(SourceConverter.class)
    @SerializedName("source")
    @Expose
    private Source source;

    @SerializedName("author")
    @ColumnInfo(name = "author")
    @Expose
    private String author;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    @Expose
    private String title;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    @Expose
    private String description;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @ColumnInfo(name = "content")
    @SerializedName("content")
    @Expose
    private String content;

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getPublishDate(Context context) {
        return Utils.dateToLocalString(Utils.isoToDate(publishedAt), Utils.getLongDateFormat(context));
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            Article n = (Article) obj;
            if (n.getUrl() != null && this.getUrl() != null) {
                return n.getUrl().equals(this.getUrl());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getUrl().hashCode();
    }

}
