package co.k2.newsbits.common;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class Constants {

    static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static class NewsApi {
        public static final String BASE_URL = "https://newsapi.org";
        private static final String VERSION_PATH = "/v2";
        private static final String END_POINT = "/top-headlines";
        public static final String API_PATH = VERSION_PATH + END_POINT;
        public static final String QUERY_KEY_COUNTRY = "country";
        public static final String QUERY_KEY_API = "apiKey";
        public static final String API_KEY = "";
    }

}
