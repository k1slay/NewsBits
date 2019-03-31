package co.k2.newsbits.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import co.k2.newsbits.R;

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

    public static String dateToTimeSinceText(Context context, Date date) {
        Resources res = context.getResources();
        long publishDiff = System.currentTimeMillis() - date.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(publishDiff);
        if (minutes < 1) {
            return (context.getString(R.string.just_now));
        } else if (minutes < 60) {
            return String.format(res.getQuantityString(R.plurals.minutes_ago, (int) minutes), minutes);
        }
        long hours = TimeUnit.MINUTES.toHours(minutes);
        if (hours < 24) {
            return String.format(res.getQuantityString(R.plurals.hours_ago, (int) hours), hours);
        }
        long days = TimeUnit.HOURS.toDays(hours);
        return String.format(res.getQuantityString(R.plurals.days_ago, (int) days), days);
    }

}
