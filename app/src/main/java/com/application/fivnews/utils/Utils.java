package com.application.fivnews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    /**
     *  @param avatarImageView
     * @param avatarUrl
     */
    public static void renderCardViewImage(ImageView avatarImageView, String avatarUrl, RequestListener listener) throws MalformedURLException {
        if (avatarUrl == null) {
//            Glide.clear(avatarImageView);
            return;
        }

        //clear HTTP protocol
        URL oldUrl = new URL(avatarUrl);
        avatarUrl = new URL("https", oldUrl.getHost(), oldUrl.getPort(), oldUrl.getFile()).toString();

        //glide process
        Glide.with(avatarImageView.getContext())
                .asBitmap()
                .load(avatarUrl)
                .listener(listener)
                .into(avatarImageView);
    }

    /**
     *
     * @param resource
     * @return
     */
    public static int getMutedColorFromBitmap(Context context, Bitmap resource) {
        if (resource != null) {
            Palette p = Palette.from(resource).generate();
            // Use generated instance
            return p.getMutedColor(ContextCompat.getColor(context, android.R.color.background_light));
        }
        return android.R.color.background_light;
    }

    /**
     *
     * @param resource
     * @return
     */
    public static int getVibrantColorFromBitmap(Context context, Bitmap resource) {
        if (resource != null) {
            Palette p = Palette.from(resource).generate();
            // Use generated instance
            return p.getVibrantColor(ContextCompat.getColor(context, android.R.color.background_light));
        }
        return android.R.color.background_light;
    }

    /**
     *
     * @param resource
     * @return
     */
    public static int getDarkVibrantColorFromBitmap(Context context, Bitmap resource) {
        if (resource != null) {
            Palette p = Palette.from(resource).generate();
            // Use generated instance
            return p.getDarkVibrantColor(ContextCompat.getColor(context, android.R.color.background_light));
        }
        return android.R.color.background_light;
    }

    /**
     *
     * @param view
     * @param imageUrl
     */
    public static void renderCircleImage(ImageView view, String imageUrl) {
        if (imageUrl == null) {
//            Glide.clear(avatarImageView);
            return;
        }

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }
}
