package com.application.fivnews.modules.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.application.fivnews.R;
import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;
import com.application.fivnews.modules.news.adapter.NewsPageAdapter;
import com.application.fivnews.ui.EmptyView;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * stargazer activity
 */
public class NewsActivity extends DaggerAppCompatActivity implements NewsContract.NewsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.newsSwipeToRefreshLayoutId)
    SwipeRefreshLayout newsSwipeToRefreshLayout;
    @BindView(R.id.newsViewpagerId)
    ViewPager newsViewpager;
    @BindView(R.id.newsProgressbarId)
    ProgressBar progressBar;
    @BindView(R.id.emptyViewId)
    EmptyView emptyView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        newsSwipeToRefreshLayout.setOnRefreshListener(this);
        presenter.bindView(this);
        presenter.retrieveItems(params);
    }

    /**
     * TODO mv to base activity
     * actionbar set listener and back arrow
     */
    private void initActionbar() {
        setSupportActionBar(toolbar);
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

        newsViewpager.setAdapter(new NewsPageAdapter(list));
        newsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {
                presenter.retrieveNewspaperInfo(list.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                enableDisableSwipeRefresh( state == ViewPager.SCROLL_STATE_IDLE );
            }

            private void enableDisableSwipeRefresh(boolean b) {
                newsSwipeToRefreshLayout.setEnabled(b);
            }
        });

        newsViewpager.setClipChildren(false);
        //load first header
        presenter.retrieveNewspaperInfo(list.get(0));
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
    public void onRenderNewspaperInfo(NewspaperInfo item) {
        ((NewsPageAdapter) newsViewpager.getAdapter())
                .onBindHeaderView(getCurrentViewInViewpager(), newsViewpager.getCurrentItem());
    }

    /**
     * get current in viewpager
     * @return
     */
    private View getCurrentViewInViewpager() {
        int position  = newsViewpager.getCurrentItem();
        return newsViewpager.findViewWithTag(position);
    }

    @Override
    public void onErrorNewspaperInfo(String error) {
        Snackbar.make(findViewById(R.id.activity_main), R.string.retrieve_error,
                Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showStandardLoading() {
        progressBar.setVisibility(View.VISIBLE);

        View view = getCurrentViewInViewpager();
        if (view != null)
            ((NewsPageAdapter) newsViewpager.getAdapter())
                .showLoader(view);
    }

    @Override
    public void hideStandardLoading() {
        progressBar.setVisibility(View.GONE);

        View view = getCurrentViewInViewpager();
        if (view != null)
            ((NewsPageAdapter) newsViewpager.getAdapter())
                    .hideLoader(view);

    }


    /**
     * build intent
     * @param context
     * @return
     */
    public static Intent buildIntent(Context context) {
        return new Intent(context, NewsActivity.class);
    }

    @Override
    public void onRefresh() {
        presenter.retrieveItems(params);
    }
}
