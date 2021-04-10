package co.k2.newsbits.data.models

import com.google.gson.annotations.SerializedName

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

data class ApiResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("code") val errorCode: String?,
    @SerializedName("message") val errorMessage: String?,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("articles") val articles: List<Article>?
) {

    inline val success: Boolean
        get() = STATUS_OK == status

    companion object {
        const val STATUS_OK = "ok"
    }

}
