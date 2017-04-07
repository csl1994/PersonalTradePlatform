package com.csl.domain;

import java.io.Serializable;

/**
 * Created by csl on 2017/3/7.
 */
public class GoodsDO implements Serializable {
    private String ID;

    private String name;

    private String description;

    private int price;

    private String kind;

    private Long datetime;

    private String status;

    private int attentionDegree;

    private String imageUrl;

    private Boolean isCurrentUserAttend;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAttentionDegree() {
        return attentionDegree;
    }

    public void setAttentionDegree(int attentionDegree) {
        this.attentionDegree = attentionDegree;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getCurrentUserAttend() {
        return isCurrentUserAttend;
    }

    public void setCurrentUserAttend(Boolean currentUserAttend) {
        isCurrentUserAttend = currentUserAttend;
    }
}