package co.k2.newsbits.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static DateFormat getDateFormat() {
        return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, Locale.getDefault());
    }

    public static boolean isInternetConnected(Context context) {
        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static boolean isChromeInstalled(Context context) {
        return getPackageInfo(context, Constants.CHROME_PACKAGE) != null;
    }

    private static PackageInfo getPackageInfo(Context context, String appPackage) {
        PackageInfo pi = null;
        PackageManager pm = context.getPackageManager();
        try {
            pi = pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.getMessage();
        }
        return pi;
    }

}
