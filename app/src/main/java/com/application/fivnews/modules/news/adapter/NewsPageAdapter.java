package com.application.fivnews.modules.news.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.List;
import com.application.fivnews.R;
import com.application.fivnews.data.model.News;
import com.application.fivnews.utils.Utils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static com.application.fivnews.utils.Utils.getDarkVibrantColorFromBitmap;
import static com.application.fivnews.utils.Utils.getMutedColorFromBitmap;

public class NewsPageAdapter extends PagerAdapter {
    private final List<News> items;

    public NewsPageAdapter(List<News> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.news_item, view, false);
        onBindView(layout, position);
        onBindHeaderView(layout, position);
        view.addView(layout);
        return layout;
    }

    /**
     *
     * @param position
     */
    private void onBindHeaderView(ViewGroup view, int position) {
        ImageView newsPublisherIcon =  view.findViewById(R.id.newsPublisherIconId);
        TextView newsPublisherNameTextView =  view.findViewById(R.id.newsPublisherNameTextViewId);

        News news = items.get(position);
        Utils.renderCircleImage(newsPublisherIcon, news.getSource().getNewspaperLogoUrl());
        newsPublisherNameTextView.setText(news.getSource().getNewspaperName());
    }

    /**
     *
     * @param view
     * @param position
     */
    private void onBindView(ViewGroup view, int position) {
        //get view by item
        TextView newsContentTextview = view.findViewById(R.id.artistNameTextViewId);
        TextView newsTitleTextview = view.findViewById(R.id.trackNameTextViewId);
        ImageView avatarImageView = view.findViewById(R.id.avatarImageViewId);
        MaterialCardView newsMaterialCardview =  view.findViewById(R.id.newsMaterialCardviewId);

        //new item
        News news = items.get(position);
        newsTitleTextview.setText(news.getTitle());
        newsContentTextview.setText(news.getDescription());
        newsMaterialCardview.setOnClickListener(v -> newsContentTextview.getContext()
                .startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(news.getUrl()))));
        try {
            Utils.renderCardViewImage(avatarImageView,
                    news.getUrlToImage(), new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                avatarImageView.setImageDrawable(ContextCompat.getDrawable(avatarImageView.getContext(), R.drawable.no_newspaper));
                                Log.e(getClass().getName(), e.getMessage());
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                newsMaterialCardview.setCardBackgroundColor(getMutedColorFromBitmap(avatarImageView.getContext(), resource));
                                newsTitleTextview.setTextColor(ContextCompat.getColor(newsMaterialCardview.getContext(), R.color.colorPrimary));
//                                newsTitleTextview.setTextColor(getVibrantColorFromBitmap(avatarImageView.getContext(), resource));
                                newsContentTextview.setTextColor(getDarkVibrantColorFromBitmap(avatarImageView.getContext(), resource));
                                return false;
                            }
                        });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "news item";
    }

}