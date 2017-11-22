
package ru.andrroider.apps.tinkoffnews.newsList.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsDto {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private List<Payload> payload = null;
    @SerializedName("trackingId")
    @Expose
    private String trackingId; //50794047017

    public String getResultCode() {
        return resultCode;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
