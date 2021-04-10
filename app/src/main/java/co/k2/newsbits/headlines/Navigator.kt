package co.k2.newsbits.headlines

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import co.k2.newsbits.common.Constants
import co.k2.newsbits.common.Utils.isChromeInstalled
import javax.inject.Inject

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 31/03/19
 */
class Navigator @Inject constructor() {

    fun navigateToWebPage(context: Context, url: String?) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        if (isChromeInstalled(context)) {
            customTabsIntent.intent.setPackage(Constants.CHROME_PACKAGE)
        }
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

}
