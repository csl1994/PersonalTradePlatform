package com.csl.dao;

import com.csl.domain.OrderDO;

import java.util.List;

/**
 * Created by csl on 2017/3/8.
 */
public interface IOrderDao extends IBaseDao<OrderDO>{
    List<OrderDO> getBySellerID(final String ID);

    List<OrderDO> getByBuyerID(final String ID);
}