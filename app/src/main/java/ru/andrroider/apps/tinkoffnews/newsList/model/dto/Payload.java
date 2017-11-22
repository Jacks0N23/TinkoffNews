
package ru.andrroider.apps.tinkoffnews.newsList.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("publicationDate")
    @Expose
    private PublicationDate publicationDate;
    @SerializedName("bankInfoTypeId")
    @Expose
    private byte bankInfoTypeId;

    private String humanReadableDate;

    public Payload(String id, String name, String text, String publicationDate) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.humanReadableDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(PublicationDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(byte bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getHumanReadableDate() {
        return humanReadableDate;
    }

    public void setHumanReadableDate(String humanReadableDate) {
        this.humanReadableDate = humanReadableDate;
    }

    public String getContent() {
        return content;
    }
}
