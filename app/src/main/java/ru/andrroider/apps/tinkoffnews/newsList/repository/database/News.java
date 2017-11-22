package ru.andrroider.apps.tinkoffnews.newsList.repository.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news")
public class News {

    @PrimaryKey(autoGenerate = true)
    private long uid;
    @ColumnInfo(name = "article_id")
    private String articleId;
    @ColumnInfo(name = "post_name")
    private String name;
    @ColumnInfo(name = "post_title")
    private String title;
    @ColumnInfo(name = "post_content")
    private CharSequence content;
    @ColumnInfo(name = "publication_date")
    private String humanReadableDate;

    public News(String articleId, String name, String title, String humanReadableDate) {
        this.articleId = articleId;
        this.name = name;
        this.title = title;
        this.humanReadableDate = humanReadableDate;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public String getHumanReadableDate() {
        return humanReadableDate;
    }

    public void setHumanReadableDate(String humanReadableDate) {
        this.humanReadableDate = humanReadableDate;
    }
}