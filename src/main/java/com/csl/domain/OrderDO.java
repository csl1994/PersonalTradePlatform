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

    private int sellerGrade;

    private int buyerGrade;

    private String sellerStatus;

    private String buyerStatus;

    private String sellerName;

    private String buyerName;

    private String goodsName;

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

    public int getSellerGrade() {
        return sellerGrade;
    }

    public void setSellerGrade(int sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    public int getBuyerGrade() {
        return buyerGrade;
    }

    public void setBuyerGrade(int buyerGrade) {
        this.buyerGrade = buyerGrade;
    }

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public String getBuyerStatus() {
        return buyerStatus;
    }

    public void setBuyerStatus(String buyerStatus) {
        this.buyerStatus = buyerStatus;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
