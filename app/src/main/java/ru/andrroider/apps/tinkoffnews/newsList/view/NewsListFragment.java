package ru.andrroider.apps.tinkoffnews.newsList.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.andrroider.apps.tinkoffnews.R;
import ru.andrroider.apps.tinkoffnews.article.view.ArticleFragment;
import ru.andrroider.apps.tinkoffnews.databinding.FragmentNewsListBinding;
import ru.andrroider.apps.tinkoffnews.newsList.adapter.NewsListAdapter;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.presenter.NewsListPresenter;

/**
 * Created by Jackson on 22/11/2017.
 */

public class NewsListFragment extends MvpAppCompatFragment implements
        SwipeRefreshLayout.OnRefreshListener, NewsListView, NewsListRouter {

    private static final String TAG = NewsListActivity.class.getSimpleName();
    private FragmentNewsListBinding mBinding;

    @InjectPresenter
    NewsListPresenter mPresenter;
    private NewsListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBinding == null) {
            setRetainInstance(true);
            mBinding = FragmentNewsListBinding.inflate(inflater, container, false);
            setupRecycler();
            setupRefresher();
        }
        return mBinding.getRoot();
    }

    private void setupRecycler() {
        mAdapter = new NewsListAdapter(this);
        mBinding.newsList.setHasFixedSize(true);
        mBinding.newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.newsList.setAdapter(mAdapter);
    }

    private void setupRefresher() {
        mBinding.refresher.setOnRefreshListener(this);
        mBinding.refresher.setColorSchemeResources(
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark, android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark);
        mBinding.refresher.setSize(SwipeRefreshLayout.DEFAULT);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadNews(true);
    }

    @Override
    public void setList(List<Payload> payloads) {
        mAdapter.setList(payloads);
        mBinding.refresher.setRefreshing(false);
    }

    @Override
    public void showError(Throwable throwable) {
        mBinding.refresher.setRefreshing(false);
        new AlertDialog.Builder(getContext()).setTitle("ОШИБКА")
                .setMessage(throwable.getLocalizedMessage()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    @Override
    public void loadArticle(String id) {
        mPresenter.setArticleId(id);
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new ArticleFragment()).addToBackStack("tag").commit();
    }
}
