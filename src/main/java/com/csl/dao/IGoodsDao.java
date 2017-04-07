package com.csl.dao;

import com.csl.domain.GoodsDO;
import com.csl.domain.GoodsKind;
import com.csl.domain.SortKind;

import java.util.List;

/**
 * Created by csl on 2017/3/7.
 */
public interface IGoodsDao extends IBaseDao<GoodsDO> {
    int save(final String userID, final GoodsDO goodsDO);

    int attentionGoods(final String userID, final String goodsID);

    int removeAttention(final String userID, final String goodsID);

    int isAttendByUser(final String userID, final String goodsID);

    int remove(final String ID);

    int removeSoldGoods(final String ID);

    int getCount(final String userID,final String region, final GoodsKind goodsKind);

    List<GoodsDO> getByOwnerID(final String ID);

    List<GoodsDO> getBySellerID(final String ID);

    List<GoodsDO> getByBuyerID(final String ID);

    List<GoodsDO> getByAttentionID(final String ID);

    List<GoodsDO> getGoods(final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final GoodsKind kind, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final String region, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final String orderBy, final SortKind sortKind, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final String region, final GoodsKind kind, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final GoodsKind kind, final String orderBy, final SortKind sortKind, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final String region, final String orderBy, final SortKind sortKind, final boolean isInit, final int page, final String userID);

    List<GoodsDO> getGoods(final String region, final GoodsKind kind, final String orderBy, final SortKind sortKind, final boolean isInit, final int page, final String userID);
}
