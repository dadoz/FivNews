package com.application.fivnews.modules.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.application.fivnews.R;
import com.application.fivnews.data.model.News;
import com.application.fivnews.modules.news.adapter.NewsPageAdapter;
import com.application.fivnews.ui.EmptyView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * stargazer activity
 */
public class NewsActivity extends DaggerAppCompatActivity implements NewsContract.NewsView {

//    @BindView(R.id.newsRecyclerViewId)
//    RecyclerView newsRecyclerView;

    @BindView(R.id.newsDotIndicatorId)
    WormDotsIndicator newsDotIndicator;
    @BindView(R.id.newsViewpagerId)
    ViewPager newsViewpager;
    @BindView(R.id.newsProgressbarId)
    ProgressBar progressBar;
    @BindView(R.id.emptyViewId)
    EmptyView emptyView;

    @Inject
    NewsPresenter presenter;

    private Unbinder unbinder;
    private SparseArray<String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        unbinder = ButterKnife.bind(this);
        params = new SparseArray<>();
        params.put(0, "google-news-it");
        onInitView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    /**
     * iit view and retrieve stargazers data
     */
    private void onInitView() {
        initActionbar();
        presenter.bindView(this);
        presenter.retrieveItems(params);
    }

    /**
     * TODO mv to base activity
     * actionbar set listener and back arrow
     */
    private void initActionbar() {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
    }

    /**
     * todo mv on base
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (presenter != null)
                    presenter.unsubscribe();
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRenderData(List<News> list) {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        newsViewpager.setAdapter(new NewsPageAdapter(list, findViewById(R.id.newsHeaderLayoutId)));
        newsDotIndicator.setViewPager(newsViewpager);
//        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        newsRecyclerView.setAdapter(new NewsListAdapter(list, null, null));
    }


    @Override
    public void onError(String error) {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        Log.e(getClass().getName(), "-->" + error);
//        Snackbar.make(findViewById(R.id.activity_main), R.string.retrieve_error,
//                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showStandardLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideStandardLoading() {
        progressBar.setVisibility(View.GONE);
    }


    /**
     * build intent
     * @param context
     * @return
     */
    public static Intent buildIntent(Context context) {
        return new Intent(context, NewsActivity.class);
    }
}
