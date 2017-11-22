package ru.andrroider.apps.tinkoffnews.newsList.repository;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;

/**
 * Created by Jackson on 21/11/2017.
 */

public interface NewsListDataStore {

    Single<List<Payload>> getNews();

    Maybe<News> getArticle(String id);
}
