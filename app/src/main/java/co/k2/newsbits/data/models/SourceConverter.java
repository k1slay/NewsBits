package co.k2.newsbits.data.models;

import androidx.room.TypeConverter;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
public class SourceConverter {

    @TypeConverter
    public static Source toSource(String source) {
        return source == null ? new Source(null, "N/A") : new Source(null, source);
    }

    @TypeConverter
    public static String toString(Source source) {
        return source == null ? null : source.getName();
    }

}
