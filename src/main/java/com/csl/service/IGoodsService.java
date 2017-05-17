package com.csl.service;

import com.csl.domain.ActionPage;
import com.csl.domain.GoodsDO;
import com.csl.domain.GoodsKind;
import com.csl.domain.SortBasis;

import java.util.List;

/**
 * Created by csl on 2017/3/8.
 */
public interface IGoodsService {
    boolean save(GoodsDO goodsDO, String userID);

    boolean remove(String ID);

    boolean update(GoodsDO goodsDO);

    GoodsDO getByID(String goodsID, String userID);

    List<GoodsDO> getGoods(String userID, String region, GoodsKind kind, SortBasis sortBasis, int page, String text);

    List<GoodsDO> getGoodsByUserID(String ID, ActionPage actionPage);

    boolean attentionGoods(String userID, String goodsID);

    boolean removeAttention(String userID, String goodsID);

    int getCount(String userID, String region, GoodsKind goodsKind, String text);
}