package co.k2.newsbits.common;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 30/03/19
 */

public class ImageUtils {

    public static void loadImageIntoView(ImageView imageView, String url, int placeHolder) {
        loadImageIntoView(imageView, url, -1, placeHolder);
    }

    public static void loadImageIntoView(ImageView imageView, int resource) {
        loadImageIntoView(imageView, null, resource, -1);
    }

    private static void loadImageIntoView(ImageView imageView, String url, int resource, int placeHolder) {
        GlideRequests glideRequests = GlideApp.with(imageView);
        GlideRequest<Drawable> glideRequest = null;
        if (url != null)
            glideRequest = glideRequests.load(url);
        else if (resource > -1)
            glideRequest = glideRequests.load(resource);
        if (glideRequest != null) {
            if (placeHolder > -1) {
                glideRequest = glideRequest.error(placeHolder);
                glideRequest = glideRequest.placeholder(placeHolder);
            }
            glideRequest.into(imageView);
        }
    }

}
