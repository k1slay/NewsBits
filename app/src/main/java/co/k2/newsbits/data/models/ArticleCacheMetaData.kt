package co.k2.newsbits.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.k2.newsbits.common.Constants

@Entity(tableName = Constants.ARTICLE_METADATA_TABLE_NAME)
data class ArticleCacheMetaData(

    @ColumnInfo(name = "lastRefresh")
    val lastRefreshTime: Long,

    @ColumnInfo(name = "country")
    val country: String

) {
    @PrimaryKey
    var id: Int = 1
        set(value) {
            field = 1
        }

}
