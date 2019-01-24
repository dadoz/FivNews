package com.application.fivnews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class Utils {

    /**
     *  @param avatarImageView
     * @param itemView
     * @param avatarUrl
     */
    public static void renderCardViewImage(ImageView avatarImageView, MaterialCardView itemView, String avatarUrl) {
        if (avatarUrl == null) {
//            Glide.clear(avatarImageView);
            return;
        }

        Glide.with(avatarImageView.getContext())
                .asBitmap()
                .load(avatarUrl)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                        avatarImageView.setImageDrawable(R.drawable.no_newspaper);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        itemView.setCardBackgroundColor(getColorFromBitmap(avatarImageView.getContext(), resource));
                        return false;
                    }
                })
                .into(avatarImageView);
    }

    /**
     *
     * @param resource
     * @return
     */
    public static int getColorFromBitmap(Context context, Bitmap resource) {
        if (resource != null) {
            Palette p = Palette.from(resource).generate();
            // Use generated instance
            return p.getMutedColor(ContextCompat.getColor(context, android.R.color.background_light));
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
