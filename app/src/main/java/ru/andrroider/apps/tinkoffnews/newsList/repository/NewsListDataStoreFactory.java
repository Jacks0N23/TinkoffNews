package ru.andrroider.apps.tinkoffnews.newsList.repository;

import java.util.List;

import ru.andrroider.apps.tinkoffnews.newsList.api.TinkoffNewsApi;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.NewsDatabase;

/**
 * Created by Jackson on 21/11/2017.
 */

public class NewsListDataStoreFactory {

    private static TinkoffNewsApi mApi;

    private static NewsDatabase mNewsDatabase;

    private static DatabaseNewsDataStore databaseNewsDataStore;
    private static NetworkNewsDataStore networkNewsDataStore;

    public NewsListDataStoreFactory(TinkoffNewsApi api, NewsDatabase newsDatabase) {
        mNewsDatabase = newsDatabase;
        mApi = api;
    }

    public static NewsListDataStore getDataStore(boolean forceNetwork) {
        if (!forceNetwork && isNewsWasLoadedPreviously()) {
            return getDatabase();
        } else {
            return getNetwork();
        }
    }

    private static NewsListDataStore getDatabase() {
        if (databaseNewsDataStore == null) {
            databaseNewsDataStore = new DatabaseNewsDataStore(mNewsDatabase);
        }
        return databaseNewsDataStore;
    }

    private static NewsListDataStore getNetwork() {
        if (networkNewsDataStore == null) {
            networkNewsDataStore = new NetworkNewsDataStore(mApi, mNewsDatabase);
        }
        return networkNewsDataStore;
    }

    private static boolean isNewsWasLoadedPreviously() {
        final List<News> newsList = mNewsDatabase.newsDao().checkIsDbEmpty();
        return newsList != null && !newsList.isEmpty();
    }
}
