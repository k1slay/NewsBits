package co.k2.newsbits.common;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class Utils {

    /**
     * @param isoDate String representation of ISO date format
     * @return Java Date object
     */
    public static Date isoToDate(String isoDate) {
        Date d = new Date(0);
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(Constants.ISO8601_DATE_FORMAT, Locale.getDefault());
        df.setTimeZone(tz);
        try {
            d = df.parse(isoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String dateToLocalString(Date date, DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static DateFormat getLongDateFormat(Context context) {
        return android.text.format.DateFormat.getLongDateFormat(context);
    }

}
