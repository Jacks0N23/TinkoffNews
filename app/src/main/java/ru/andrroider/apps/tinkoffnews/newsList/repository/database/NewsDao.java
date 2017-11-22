package ru.andrroider.apps.tinkoffnews.newsList.repository.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Jackson on 22/11/2017.
 */

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Query("SELECT * FROM news limit 5")
    List<News> checkIsDbEmpty();

    @Query("SELECT * FROM news where article_id LIKE :id")
    News findById(String id);

    @Insert
    void insertAll(List<News> news);

    @Update(onConflict = REPLACE)
    void update(News article);

    @Delete
    void eraseTable(List<News> news);
}
