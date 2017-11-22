package ru.andrroider.apps.tinkoffnews.article.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import ru.andrroider.apps.tinkoffnews.article.view.ArtivleView;
import ru.andrroider.apps.tinkoffnews.newsList.repository.database.News;

import static ru.andrroider.apps.tinkoffnews.newsList.model.NewsModel.getsNewsModel;

/**
 * Created by Jackson on 22/11/2017.
 */

@InjectViewState
public class ArticlePresenter extends MvpPresenter<ArtivleView> {

    private Disposable mDisposable;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadArticle();
    }

    private void loadArticle() {
        dispose();
        if (getsNewsModel().getArticleId() != null) {
            getsNewsModel().getArticle().subscribe(new MaybeObserver<News>() {
                @Override
                public void onSubscribe(Disposable d) {
                    mDisposable = d;
                }

                @Override
                public void onSuccess(News content) {
                    getViewState().showContent(content);
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().showError(e);
                }

                @Override
                public void onComplete() {
                    getsNewsModel().setArticleId(null);
                }
            });
        } else {
            getViewState().showError(new IllegalArgumentException("Неверный идентификатор записи"));
        }
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
}
