package co.k2.newsbits.headlines;

import android.content.Context;
import android.net.Uri;

import javax.inject.Inject;

import androidx.browser.customtabs.CustomTabsIntent;
import co.k2.newsbits.common.Constants;
import co.k2.newsbits.common.Utils;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 31/03/19
 */

public class Navigator {

    @Inject
    public Navigator() {
    }

    void navigateToWebPage(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        if (Utils.isChromeInstalled(context))
            customTabsIntent.intent.setPackage(Constants.CHROME_PACKAGE);
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

}
