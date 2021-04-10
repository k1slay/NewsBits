package co.k2.newsbits.common

import android.content.Context
import android.os.Build
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

inline val Context.countryIsoCode: String
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales[0].country
        } else {
            resources.configuration.locale.country
        }
    }

fun Date.toLocalDateFormat(dateFormat: DateFormat): String {
    return dateFormat.format(this)
}

inline val Long.isLessThanADayOld: Boolean
    get() {
        return System.currentTimeMillis() - this < TimeUnit.DAYS.toMillis(1)
    }
