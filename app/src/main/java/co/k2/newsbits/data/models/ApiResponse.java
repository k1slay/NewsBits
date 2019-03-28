package co.k2.newsbits.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class ApiResponse {

    private static final String STATUS_OK = "ok";

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("code")
    @Expose
    private String errorCode;

    @SerializedName("message")
    @Expose
    private String errorMessage;

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean success() {
        return STATUS_OK.equals(status);
    }

    public boolean hasArticles() {
        return articles != null && !articles.isEmpty();
    }

}
