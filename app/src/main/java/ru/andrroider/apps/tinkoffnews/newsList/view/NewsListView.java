package ru.andrroider.apps.tinkoffnews.newsList.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;

/**
 * Created by Jackson on 21/11/2017.
 */

public interface NewsListView extends MvpView {

    void setList(List<Payload> payloads);

    void showError(Throwable throwable);

}
