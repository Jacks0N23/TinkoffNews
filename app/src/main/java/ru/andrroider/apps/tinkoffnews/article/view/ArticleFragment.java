package ru.andrroider.apps.tinkoffnews.article.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.andrroider.apps.tinkoffnews.article.presenter.ArticlePresenter;
import ru.andrroider.apps.tinkoffnews.databinding.FragmentArticleBinding;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;

/**
 * Created by Jackson on 22/11/2017.
 */

public class ArticleFragment extends MvpAppCompatFragment implements ArtivleView {

    private static final String TAG = ArticleFragment.class.getSimpleName();
    private FragmentArticleBinding mBinding;

    @InjectPresenter
    ArticlePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mBinding == null) {
            setRetainInstance(true);
            mBinding = FragmentArticleBinding.inflate(inflater, container, false);
        }
        return mBinding.getRoot();
    }

    @Override
    public void showContent(News articleContent) {
        mBinding.progress.setVisibility(View.GONE);
        mBinding.title.setText(articleContent.getTitle());
        mBinding.content.setText(articleContent.getContent());
    }

    @Override
    public void showError(Throwable throwable) {
        new AlertDialog.Builder(getContext()).setTitle("ОШИБКА")
                .setMessage(throwable.getLocalizedMessage()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
