package co.k2.newsbits.data.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import co.k2.newsbits.common.Constants
import co.k2.newsbits.common.Utils
import co.k2.newsbits.common.toLocalDateFormat
import com.google.gson.annotations.SerializedName

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
@Entity(tableName = Constants.ARTICLE_CACHE_TABLE_NAME)
@TypeConverters(SourceConverter::class)
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("source")
    @ColumnInfo(name = "source")
    val source: Source? = null,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String? = null

) {

    inline val publishDate: String
        get() = Utils.isoToDate(publishedAt).toLocalDateFormat(Utils.dateFormat)

    fun getPublishedSince(context: Context): String {
        val date = Utils.isoToDate(publishedAt)
        return Utils.dateToTimeSinceText(context, date)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Article) {
            return other.url == url
        }
        return false
    }

    override fun hashCode(): Int {
        return url.hashCode()
    }

}
