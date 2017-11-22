package ru.andrroider.apps.tinkoffnews;

import android.app.Application;

import ru.andrroider.apps.tinkoffnews.newsList.api.TinkoffNewsApi;
import ru.andrroider.apps.tinkoffnews.newsList.model.NewsModel;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.NewsDatabase;

/**
 * Created by Jackson on 22/11/2017.
 */

public class TinkoffNewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //это называется лень впиливать даггер
        new NewsModel(new TinkoffNewsApi.TinkoffNewsApiCreator(),
                NewsDatabase.getNewsDatabase(this));
    }


    @Override
    public void onTerminate() {
        NewsDatabase.destroyInstance();
        super.onTerminate();
    }
}
