package com.csl.serviceImpl;

import com.csl.daoImpl.GoodsDaoImpl;
import com.csl.domain.*;
import com.csl.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csl on 2017/3/9.
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsDaoImpl goodsDao;

    public boolean save(GoodsDO goodsDO, String userID) {
        return this.goodsDao.save(userID, goodsDO) > 0;
    }

    public boolean remove(String ID) {
        return this.goodsDao.remove(ID) > 0;
    }

    public boolean removeSoldGoods(String ID) {
        return this.goodsDao.removeSoldGoods(ID) > 0;
    }

    public boolean update(GoodsDO goodsDO) {
        return this.goodsDao.update(goodsDO) > 0;
    }

    public GoodsDO find(String goodsID) {
        return this.goodsDao.find(goodsID);
    }

    public GoodsDO getByID(String goodsID, String userID) {
        GoodsDO goodsDO = this.goodsDao.find(goodsID);
        if (goodsDO != null) {
            goodsDO.setCurrentUserAttend(this.isAttendByUser(userID, goodsID));
        }
        return goodsDO;
    }

    public List<GoodsDO> getTop5(String userID) {
        return this.goodsDao.getTop5(userID);
    }

    public List<GoodsDO> getGoods(String userID, String region, GoodsKind kind, SortBasis sortBasis, int page, String text) {
        boolean isInit = false;
        if (page <= 1) {
            isInit = true;
        }
        List<GoodsDO> goodsDOList = new ArrayList<GoodsDO>();
        switch (sortBasis) {
            case NONE:
                goodsDOList = this.goodsDao.getGoods(region, kind, isInit, page, userID, text);
                break;
            case ATTENTIONDESC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "ATTENTIONDEGREE", SortKind.DESC, isInit, page, userID, text);
                break;
            case ATTENTIONASC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "ATTENTIONDEGREE", SortKind.ASC, isInit, page, userID, text);
                break;
            case PRICEDESC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "PRICE", SortKind.DESC, isInit, page, userID, text);
                break;
            case PRICEASC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "PRICE", SortKind.ASC, isInit, page, userID, text);
                break;
            case CREDITDESC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "CREDIT", SortKind.DESC, isInit, page, userID, text);
                break;
            case CREDITASC:
                goodsDOList = this.goodsDao.getGoods(region, kind, "CREDIT", SortKind.ASC, isInit, page, userID, text);
                break;
            default:
                break;
        }
        for (int item = goodsDOList.size() - 1; item >= 0; item--) {
            goodsDOList.get(item).setCurrentUserAttend(this.isAttendByUser(userID, goodsDOList.get(item).getID()));
        }
        return goodsDOList;
    }

    public List<GoodsDO> getGoodsByUserID(String ID, ActionPage actionPage) {
        List<GoodsDO> goodsDOList = new ArrayList<GoodsDO>();
        switch (actionPage) {
            case own:
                goodsDOList = this.goodsDao.getByOwnerID(ID);
                break;
            case sell:
                goodsDOList = this.goodsDao.getBySellerID(ID);
                break;
            case buy:
                goodsDOList = this.goodsDao.getByBuyerID(ID);
                break;
            case attention:
                goodsDOList = this.goodsDao.getByAttentionID(ID);
                break;
            default:
                break;
        }
        for (int item = goodsDOList.size() - 1; item >= 0; item--) {
            goodsDOList.get(item).setCurrentUserAttend(this.isAttendByUser(ID, goodsDOList.get(item).getID()));
        }
        return goodsDOList;
    }

    public boolean attentionGoods(String userID, String goodsID) {
        return this.goodsDao.attentionGoods(userID, goodsID) > 0;
    }

    public boolean removeAttention(String userID, String goodsID) {
        return this.goodsDao.removeAttention(userID, goodsID) > 0;
    }

    public boolean isAttendByUser(String userID, String goodsID) {
        return this.goodsDao.isAttendByUser(userID, goodsID) > 0;
    }

    public int getCount(String userID, String region, GoodsKind goodsKind, String text) {
        return this.goodsDao.getCount(userID, region, goodsKind, text);
    }

    public boolean updateStatus(String goodsID, GoodsStatus status) {
        return this.goodsDao.updateStatus(goodsID, status.name()) > 0;
    }
}