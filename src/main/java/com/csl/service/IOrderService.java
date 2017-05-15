package com.csl.service;

import com.csl.domain.OrderDO;

import java.util.List;

/**
 * Created by csl on 2017/5/12.
 */
public interface IOrderService {
    OrderDO find(String ID);

    boolean save(OrderDO orderDO);

    boolean update(OrderDO orderDO);

    List<OrderDO> getBySeller(String ID);

    List<OrderDO> getByBuyer(String ID);

    boolean updateSellerStatus(String ID, String status);

    boolean updateBuyerStatus(String ID, String status);
}
