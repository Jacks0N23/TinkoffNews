package ru.andrroider.apps.tinkoffnews.newsList.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.andrroider.apps.tinkoffnews.R;

public class NewsListActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Новости Тинькофф");

        if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new NewsListFragment()).commit();
        }
    }

}
