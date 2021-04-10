package co.k2.newsbits.data.models

import androidx.room.TypeConverter

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
class SourceConverter {

    @TypeConverter
    fun toSource(source: String?): Source? {
        return if (source == null) Source(null, "N/A") else Source(null, source)
    }

    @TypeConverter
    fun toString(source: Source?): String? {
        return source?.name
    }

}
