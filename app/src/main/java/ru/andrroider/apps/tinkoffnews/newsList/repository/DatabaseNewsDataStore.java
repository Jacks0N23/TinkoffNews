package ru.andrroider.apps.tinkoffnews.newsList.repository;

import android.text.Html;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Maybe;
import io.reactivex.Single;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.NewsDatabase;

/**
 * Created by Jackson on 22/11/2017.
 */

public class DatabaseNewsDataStore implements NewsListDataStore {

    private static final String TAG = DatabaseNewsDataStore.class.getSimpleName();
    private NewsDatabase mNewsDatabase;

    public DatabaseNewsDataStore(NewsDatabase mNewsDatabase) {
        this.mNewsDatabase = mNewsDatabase;
    }

    @Override
    public Single<List<Payload>> getNews() {
        return Single.fromCallable(new Callable<List<Payload>>() {
            @Override
            public List<Payload> call() throws Exception {
                List<News> savedNews = mNewsDatabase.newsDao().getAll();
                List<Payload> payloads = new LinkedList<>();
                for (News article : savedNews) {
                    payloads.add(new Payload(article.getArticleId(), article.getName(), article.getTitle(),
                            article.getHumanReadableDate()));
                }
                Log.e(TAG, "getNews: ");
                return payloads;
            }
        });
    }

    @Override
    public Maybe<News> getArticle(final String id) {
        Log.e(TAG, "getArticle: ");
        final News byId = mNewsDatabase.newsDao().findById(id);
        if (byId != null && byId.getContent() != null) {
            return Maybe.fromCallable(new Callable<News>() {
                @Override
                public News call() throws Exception {
                    byId.setContent(Html.fromHtml(byId.getContent().toString()));
                    return byId;
                }
            });

        }
        return null;
    }
}
