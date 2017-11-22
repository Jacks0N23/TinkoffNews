package ru.andrroider.apps.tinkoffnews.newsList.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jackson on 22/11/2017.
 */

public class Article {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private Payload payload = null;
    @SerializedName("trackingId")
    @Expose
    private String trackingId; //50800640588

    public String getResultCode() {
        return resultCode;
    }

    public Payload getPayload() {
        return payload;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
