package com.csl.domain;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by csl on 2017/3/13.
 */
public class OrderDO implements Serializable {
    private String ID;

    private String sellerID;

    private String buyerID;

    private String goodsID;

    private Long datetime;

    private String comment;

    private int sellerLevel;

    private int bugerLevel;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(int sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public int getBugerLevel() {
        return bugerLevel;
    }

    public void setBugerLevel(int bugerLevel) {
        this.bugerLevel = bugerLevel;
    }
}
