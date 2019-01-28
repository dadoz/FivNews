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
        return(0.9f);
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


        newsMaterialCardview.setOnTouchListener((v, event) -> new GestureDetectorCompat(view.getContext(), new CustomOnGestureListener(v)).onTouchEvent(event));
        //new item
        News news = items.get(position);
        newsTitleTextview.setText(news.getTitle());
        newsContentTextview.setText(news.getDescription());
//        newsMaterialCardview.setOnClickListener(v -> newsContentTextview.getContext()
//                .startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(news.getUrl()))));
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

    public void showLoader(View view) {
        view.findViewById(R.id.newspaperProgressbarId).setVisibility(View.VISIBLE);
    }

    public void hideLoader(View view) {
        view.findViewById(R.id.newspaperProgressbarId).setVisibility(View.GONE);

    }

    class CustomOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SLIDE_THRESHOLD = 100;
        private final View view;

        CustomOnGestureListener(View view) {
            this.view = view;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            try {
                float deltaY = e2.getY() - e1.getY();
                float deltaX = e2.getX() - e1.getX();

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return Math.abs(deltaX) > SLIDE_THRESHOLD ?
                            deltaX > 0 ? onSlideRight() : onSlideLeft() :
                            deltaY > 0 ? onSlideDown() : onSlideUp();
                }
            } catch (Exception exception) {
                Log.e(getClass().getName(), exception.getMessage());
            }

            return false;
        }
        public boolean onSlideRight() {
            return false;
        }

        public boolean onSlideLeft() {
            return false;
        }

        public boolean onSlideUp() {
            view.setBackgroundColor(Color.MAGENTA);
            return false;
        }

        public boolean onSlideDown() {
            return false;
        }
    }
}