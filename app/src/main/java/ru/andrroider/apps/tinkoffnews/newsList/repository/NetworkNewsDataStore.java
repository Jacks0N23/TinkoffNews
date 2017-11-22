package ru.andrroider.apps.tinkoffnews.newsList.repository;

import android.text.Html;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.andrroider.apps.tinkoffnews.newsList.api.TinkoffNewsApi;
import ru.andrroider.apps.tinkoffnews.newsList.model.PayloadComaparator;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Article;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.NewsDto;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.NewsDatabase;

/**
 * Created by Jackson on 22/11/2017.
 */

public class NetworkNewsDataStore implements NewsListDataStore {

    private static final String TAG = NetworkNewsDataStore.class.getSimpleName();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy",
            Locale.getDefault());

    private TinkoffNewsApi mApi;

    private NewsDatabase mNewsDatabase;

    public NetworkNewsDataStore(TinkoffNewsApi mApi, NewsDatabase newsDatabase) {
        this.mApi = mApi;
        this.mNewsDatabase = newsDatabase;
    }

    public Single<List<Payload>> getNews() {
        return mApi.getNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(new Function<NewsDto, List<Payload>>() {
                    @Override
                    public List<Payload> apply(NewsDto newsDto) throws Exception {
                        Collections.sort(newsDto.getPayload(), new PayloadComaparator());
                        Log.e("TAG", "apply: ");
                        mNewsDatabase.newsDao().eraseTable(mNewsDatabase.newsDao().getAll());
                        List<News> newsToDb = new LinkedList<>();
                        for (Payload payload : newsDto.getPayload()) {
                            payload.setHumanReadableDate(getStringDate(payload.getPublicationDate()
                                    .getMilliseconds()));

                            newsToDb.add(new News(payload.getId(), payload.getName(),
                                    payload.getText(), payload.getHumanReadableDate()));
                        }
                        mNewsDatabase.newsDao().insertAll(newsToDb);
                        Log.e(TAG, "getNews: ");
                        return newsDto.getPayload();
                    }
                });

    }


    private String getStringDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return simpleDateFormat.format(calendar.getTime());
    }


    @Override
    public Maybe<News> getArticle(final String articleId) {
        return mApi.getNewsDetails(articleId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Article, News>() {
                    @Override
                    public News apply(Article newsDto) throws Exception {
                        if (newsDto.getPayload() != null && newsDto.getPayload().getContent() != null) {
                            final CharSequence content = Html.fromHtml(newsDto.getPayload().getContent());
                            News article = mNewsDatabase.newsDao().findById(articleId);
                            if (article == null) {
                                return null;
                            }
                            article.setContent(newsDto.getPayload().getContent());
                            mNewsDatabase.newsDao().update(article);
                            Log.e(TAG, "getArticle: ");
                            article.setContent(content);
                            return article;
                        }
                        return null;
                    }
                });
    }
}
