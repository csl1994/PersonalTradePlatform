package com.csl.domain;

import java.io.Serializable;

/**
 * Created by csl on 2017/3/7.
 */
public class GoodsDO implements Serializable {
    private String ID;

    private String name;

    private String description;

    private double price;

    private String kind;

    private Long datetime;

    private String status;

    private int attentionDegree;

    private String imageUrl;

    private boolean isCurrentUserAttend;

    private Long createDate;

    private int length;

    private int width;

    private int height;

    private String color;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public Boolean isCurrentUserAttend() {
        return isCurrentUserAttend;
    }

    public void setCurrentUserAttend(boolean currentUserAttend) {
        isCurrentUserAttend = currentUserAttend;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}