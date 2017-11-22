
package ru.andrroider.apps.tinkoffnews.newsList.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicationDate {

    @SerializedName("milliseconds")
    @Expose
    private long milliseconds;

    public long getMilliseconds() {
        return milliseconds;
    }
}
