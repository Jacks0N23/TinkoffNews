package ru.andrroider.apps.tinkoffnews.newsList.model;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import ru.andrroider.apps.tinkoffnews.newsList.api.TinkoffNewsApi;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.repository.NewsListDataStore;
import ru.andrroider.apps.tinkoffnews.newsList.repository.NewsListDataStoreFactory;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.NewsDatabase;

/**
 * Created by Jackson on 21/11/2017.
 */

public class NewsModel {

    private static NewsModel sNewsModel;

    private String mArticleId;

    public static NewsModel getsNewsModel() {
        return sNewsModel;
    }

    public NewsModel(TinkoffNewsApi api, NewsDatabase newsDatabase) {
        new NewsListDataStoreFactory(api, newsDatabase);
        sNewsModel = this;
    }

    public Single<List<Payload>> getNews(boolean forceNetwork) {
        return getDataStore(forceNetwork).getNews();
    }

    public Maybe<News> getArticle() {
        Maybe<News> request = getDataStore(false).getArticle(mArticleId);
        if (request == null)
            return getDataStore(true).getArticle(mArticleId);
        else
            return request;
    }

    private NewsListDataStore getDataStore(boolean forceNetwork) {
        return NewsListDataStoreFactory.getDataStore(forceNetwork);
    }

    public void setArticleId(String mArticleId) {
        this.mArticleId = mArticleId;
    }

    public String getArticleId() {
        return mArticleId;
    }
}
