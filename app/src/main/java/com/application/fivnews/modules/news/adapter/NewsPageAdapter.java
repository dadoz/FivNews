package com.application.fivnews.modules.news.adapter;

import android.support.design.card.MaterialCardView;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import com.application.fivnews.R;
import com.application.fivnews.data.model.News;
import com.application.fivnews.utils.Utils;

public class NewsPageAdapter extends PagerAdapter {
    private final List<News> items;
    private final WeakReference<View> parentView;

    public NewsPageAdapter(List<News> items, View parentView) {
        this.items = items;
        this.parentView = new WeakReference<>(parentView);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.news_item, collection, false);
        onBindView(layout, position);
        onBindHeaderView(position);
        collection.addView(layout);
        return layout;
    }

    /**
     *
     * @param position
     */
    private void onBindHeaderView(int position) {
        if (parentView.get() != null) {
            ImageView newsPublisherIcon =  parentView.get().findViewById(R.id.newsPublisherIconId);
            TextView newsPublisherNameTextView =  parentView.get().findViewById(R.id.newsPublisherNameTextViewId);

            News news = items.get(position);
            Utils.renderCircleImage(newsPublisherIcon, "https://images-eu.ssl-images-amazon.com/images/I/31B%2BQMn-V2L.png");
            newsPublisherNameTextView.setText(news.getSource().getName());
        }
    }

    /**
     *
     * @param layout
     * @param position
     */
    private void onBindView(ViewGroup layout, int position) {
        //get view by item
        TextView newsTitleTextview = layout.findViewById(R.id.artistNameTextViewId);
        TextView newsContentTextview =  layout.findViewById(R.id.trackNameTextViewId);
        ImageView avatarImageView =  layout.findViewById(R.id.avatarImageViewId);

        //new item
        News news = items.get(position);
        Utils.renderCardViewImage(avatarImageView, ((MaterialCardView) layout), news.getUrlToImage());
        newsTitleTextview.setText(news.getTitle());
        newsContentTextview.setText(news.getDescription());
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