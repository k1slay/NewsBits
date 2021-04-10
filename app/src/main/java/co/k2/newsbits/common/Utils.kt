package co.k2.newsbits.common

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import co.k2.newsbits.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
object Utils {

    /**
     * @param isoDate String representation of ISO date format
     * @return Java Date object
     */
    @JvmStatic
    fun isoToDate(isoDate: String?): Date {
        var d = Date(0)
        val tz = TimeZone.getTimeZone(Constants.TIMEZONE_UTC)
        val df: DateFormat = SimpleDateFormat(Constants.ISO8601_DATE_FORMAT, Locale.getDefault())
        df.timeZone = tz
        try {
            d = df.parse(isoDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return d
    }

    @JvmStatic
    fun dateToLocalString(date: Date?, dateFormat: DateFormat): String {
        return dateFormat.format(date)
    }

    inline val dateFormat: DateFormat
        get() = DateFormat.getDateTimeInstance(
            DateFormat.DEFAULT,
            DateFormat.SHORT,
            Locale.getDefault()
        )

    fun isInternetConnected(context: Context): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conMgr.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun isChromeInstalled(context: Context): Boolean {
        return getPackageInfo(context, Constants.CHROME_PACKAGE) != null
    }

    private fun getPackageInfo(context: Context, appPackage: String): PackageInfo? {
        var pi: PackageInfo? = null
        val pm = context.packageManager
        try {
            pi = pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES)
        } catch (e: NameNotFoundException) {
            e.message
        }
        return pi
    }

    fun dateToTimeSinceText(context: Context, date: Date): String {
        val res = context.resources
        val publishDiff = System.currentTimeMillis() - date.time
        val minutes = TimeUnit.MILLISECONDS.toMinutes(publishDiff)
        if (minutes < 1) {
            return context.getString(R.string.just_now)
        } else if (minutes < 60) {
            return String.format(
                res.getQuantityString(R.plurals.minutes_ago, minutes.toInt()),
                minutes
            )
        }
        val hours = TimeUnit.MINUTES.toHours(minutes)
        if (hours < 24) {
            return String.format(res.getQuantityString(R.plurals.hours_ago, hours.toInt()), hours)
        }
        val days = TimeUnit.HOURS.toDays(hours)
        return String.format(res.getQuantityString(R.plurals.days_ago, days.toInt()), days)
    }

    fun getCountry(resources: Resources): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales[0].country
        } else {
            resources.configuration.locale.country
        }
    }

}