package com.application.sfy.modules.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.application.sfy.R;
import com.application.sfy.data.model.News;
import com.application.sfy.ui.EmptyView;

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
    private static final String LYRICS_PARAMS_KEY = "LYRICS_PARAMS_KEY";
//    @BindView(R.id.artistNameTextViewId)
//    TextView artistNameTextView;
//    @BindView(R.id.trackNameTextViewId)
//    TextView trackNameTextView;
//    @BindView(R.id.avatarImageViewId)
//    ImageView avatarImageView;
//    @BindView(R.id.lyricsTextViewId)
//    TextView lyricsTextView;

    @BindView(R.id.newsRecyclerViewId)
    RecyclerView newsRecyclerView;

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
//        params = Utils.getLyricsParamsFromBundle(getIntent().getExtras().getBundle(LYRICS_PARAMS_KEY));
        presenter.bindView(this);
        presenter.retrieveItems(params);
    }

    /**
     * TODO mv to base activity
     * actionbar set listener and back arrow
     */
    private void initActionbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
        Log.e(getClass().getName(), "hey --->");
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        newsRecyclerView.setAdapter(new NewsListAdapter(list, null, null));
//        artistNameTextView.setText(params.get(1));
//        trackNameTextView.setText(params.get(2));
//        Utils.renderIcon(avatarImageView, params.get(3));
//        lyricsTextView.setText(news.getDescription());
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
//        Bundle bundle = Utils.buildLyricsParams(Integer.toString(trackId), artistName, trackName, avatarUrl);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra(LYRICS_PARAMS_KEY, bundle);
        return intent;
    }
}
