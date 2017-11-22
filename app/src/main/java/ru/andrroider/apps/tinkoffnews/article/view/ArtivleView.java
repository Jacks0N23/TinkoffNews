package ru.andrroider.apps.tinkoffnews.article.view;

import com.arellomobile.mvp.MvpView;

import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;

/**
 * Created by Jackson on 22/11/2017.
 */

public interface ArtivleView extends MvpView {

    void showContent(News articleContent);

    void showError(Throwable throwable);
}
