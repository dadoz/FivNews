package com.application.fivnews.modules.news.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.List;
import com.application.fivnews.R;
import com.application.fivnews.data.model.News;
import com.application.fivnews.modules.news.NewsPresenter;
import com.application.fivnews.ui.ProgressLoader;
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

    @Override
    public float getPageWidth(int position) {
        return(0.8f);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.news_item, view, false);
        onBindView(layout, position);
        view.addView(layout);
        layout.setTag(position);
        return layout;
    }

    /**
     *
     * @param position
     */
    public void onBindHeaderView(View view, int position) {
        NewsPaperContentViewHolder viewHolder = new NewsPaperContentViewHolder(view);

        News news = items.get(position);
//        Utils.renderCircleImage(viewHolder.newsPublisherIcon, news.getSource().getNewspaperLogoUrl());
        viewHolder.newsPublisherNameTextView.setText(news.getSource().getNewspaperName());
        viewHolder.newsPublisherDescriptionTextView.setText("Google news");
    }

    /**
     *
     * @param view
     * @param position
     */
    private void onBindView(ViewGroup view, int position) {
        //get view by item
        NewsContentViewHolder viewHolder = new NewsContentViewHolder(view);        
        //new item
        News news = items.get(position);
        viewHolder.newsTitleTextview.setText(news.getTitle());
        viewHolder.newsContentTextview.setText(news.getDescription());
        try {
            Utils.renderCardViewImage(viewHolder.avatarImageView,
                    news.getUrlToImage(), new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                viewHolder.avatarImageView.setImageDrawable(ContextCompat.getDrawable(viewHolder.avatarImageView.getContext(), R.drawable.no_newspaper));
                                Log.e(getClass().getName(), e.getMessage());
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                                viewHolder.newsMaterialCardview.setCardBackgroundColor(getMutedColorFromBitmap(viewHolder.avatarImageView.getContext(), resource));
//                                viewHolder.newsTitleTextview.setTextColor(ContextCompat.getColor(viewHolder.newsMaterialCardview.getContext(), R.color.colorPrimary));
//                                viewHolder.newsContentTextview.setTextColor(getDarkVibrantColorFromBitmap(viewHolder.avatarImageView.getContext(), resource));
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

    public void showLoader(View view) {
        view.findViewById(R.id.newspaperProgressbarId).setVisibility(View.VISIBLE);
    }

    public void hideLoader(View view) {
        view.findViewById(R.id.newspaperProgressbarId).setVisibility(View.GONE);

    }

    class NewsContentViewHolder{
        private final TextView newsContentTextview;
        private final TextView newsTitleTextview;
        private final ImageView avatarImageView;
        private final MaterialCardView newsMaterialCardview;

        NewsContentViewHolder(ViewGroup view) {
            newsContentTextview = view.findViewById(R.id.artistNameTextViewId);
            newsTitleTextview = view.findViewById(R.id.trackNameTextViewId);
            avatarImageView = view.findViewById(R.id.avatarImageViewId);
            newsMaterialCardview =  view.findViewById(R.id.newsMaterialCardviewId);
        }
    }

}