package ru.andrroider.apps.tinkoffnews.newsList.api;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Article;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.NewsDto;

public interface TinkoffNewsApi {
    @GET("v1/news")
    Single<NewsDto> getNews();

    @GET("/v1/news_content")
    Maybe<Article> getNewsDetails(@Query("id") String id);

    class TinkoffNewsApiCreator implements TinkoffNewsApi {

        private TinkoffNewsApi mApi;

        @Override
        public Single<NewsDto> getNews() {
            return getApi().getNews();
        }

        @Override
        public Maybe<Article> getNewsDetails(String id) {
            return getApi().getNewsDetails(id);
        }

        private TinkoffNewsApi getApi() {
            if (mApi == null) {
                mApi = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.tinkoff.ru/")
                        .build().create(TinkoffNewsApi.class);
            }
            return mApi;
        }
    }
}