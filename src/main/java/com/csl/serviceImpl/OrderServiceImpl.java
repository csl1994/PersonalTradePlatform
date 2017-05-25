package com.csl.serviceImpl;

import com.csl.daoImpl.OrderDaoImpl;
import com.csl.domain.OrderDO;
import com.csl.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by csl on 2017/5/12.
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderDaoImpl orderDao;

    public OrderDO find(String ID) {
        return this.orderDao.find(ID);
    }

    public boolean save(OrderDO orderDO) {
        return this.orderDao.save(orderDO) > 0;
    }

    public boolean update(OrderDO orderDO) {
        return this.orderDao.update(orderDO) > 0;
    }

    public List<OrderDO> getBySeller(String ID) {
        return this.orderDao.getBySellerID(ID);
    }

    public List<OrderDO> getByBuyer(String ID) {
        return this.orderDao.getByBuyerID(ID);
    }

    public boolean updateSellerStatus(String ID, String status) {
        return this.orderDao.updateSellerStatus(ID, status) > 0;
    }

    public boolean updateBuyerStatus(String ID, String status) {
        return this.orderDao.updateBuyerStatus(ID, status) > 0;
    }

    public boolean newSellOrder(String sellerID) {
        return this.orderDao.newSellOrder(sellerID) > 0;
    }

    public boolean newBuyOrder(String buyerID) {
        return this.orderDao.newBuyOrder(buyerID) > 0;
    }

    public boolean updateSellerGrade(String ID, int grade) {
        return this.orderDao.updateSellerGrade(ID, grade) > 0;
    }

    public boolean updateBuyerGrade(String ID, int grade) {
        return this.orderDao.updateBuyerGrade(ID, grade) > 0;
    }
}