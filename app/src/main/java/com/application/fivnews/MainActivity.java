package com.application.fivnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.application.fivnews.modules.news.NewsActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.application.fivnews.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.trackInputDataViewId)
//    TrackInputDataView trackInputDataView;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        onInitView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    /**
     * init view to handle button in custom view interaction
     */
    private void onInitView() {
//        trackInputDataView.setFindButtonOnClickListener(this);
        Intent intent = NewsActivity.buildIntent(this);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
//        if (!trackInputDataView.isValidInputData()) {
//            trackInputDataView.setErrorInputData();
//            return;
//        }

        Intent intent = NewsActivity.buildIntent(this);
        startActivity(intent);
    }
}
