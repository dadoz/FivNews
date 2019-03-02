package com.application.fivnews.modules.news.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.fivnews.R;

class NewsPaperContentViewHolder {
//    final ImageView newsPublisherIcon;
    final TextView newsPublisherNameTextView;
    final TextView newsPublisherDescriptionTextView;

    public NewsPaperContentViewHolder(View view) {
//        newsPublisherIcon =  view.findViewById(R.id.newsPublisherIconId);
        newsPublisherNameTextView =  view.findViewById(R.id.newsPublisherNameTextViewId);
        newsPublisherDescriptionTextView =  view.findViewById(R.id.newsPublisherDescriptionTextViewId);
    }
}
