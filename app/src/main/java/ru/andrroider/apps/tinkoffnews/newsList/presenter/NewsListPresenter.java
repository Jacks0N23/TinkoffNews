package ru.andrroider.apps.tinkoffnews.newsList.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.view.NewsListView;

import static ru.andrroider.apps.tinkoffnews.newsList.model.NewsModel.getsNewsModel;

/**
 * Created by Jackson on 21/11/2017.
 */

@InjectViewState
public class NewsListPresenter extends MvpPresenter<NewsListView> {

    private Disposable mDisposable;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadNews(false);
    }

    public void loadNews(final boolean forceNetwork) {
        dispose();
        getsNewsModel().getNews(forceNetwork).subscribe(new SingleObserver<List<Payload>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(List<Payload> payloads) {
                getViewState().setList(payloads);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(e);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose();
    }

    private void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void setArticleId(String articleId) {
        getsNewsModel().setArticleId(articleId);
    }
}
