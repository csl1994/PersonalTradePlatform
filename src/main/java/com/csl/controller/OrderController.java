package com.csl.controller;

import com.csl.domain.GoodsDO;
import com.csl.domain.OrderDO;
import com.csl.domain.OrderStatus;
import com.csl.domain.UserDO;
import com.csl.serviceImpl.GoodsServiceImpl;
import com.csl.serviceImpl.OrderServiceImpl;
import com.csl.serviceImpl.RecordsServiceImpl;
import com.csl.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by csl on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private RecordsServiceImpl recordsService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    @ResponseBody
    public boolean createOrder(String sellerID, String buyerID, String goodsID) {
        UserDO seller = this.userService.getUserByID(sellerID);
        UserDO buyer = this.userService.getUserByID(buyerID);
        GoodsDO goods = this.goodsService.find(goodsID);
        OrderDO orderDO = new OrderDO();
        orderDO.setID(UUID.randomUUID().toString());
        orderDO.setSellerID(sellerID);
        orderDO.setBuyerID(buyerID);
        orderDO.setGoodsID(goodsID);
        orderDO.setDatetime(new Date().getTime());
        orderDO.setSellerGrade(0);
        orderDO.setBuyerGrade(0);
        orderDO.setSellerStatus(OrderStatus.unread.name());
        orderDO.setBuyerStatus(OrderStatus.start.name());
        orderDO.setSellerName(seller.getName());
        orderDO.setBuyerName(buyer.getName());
        orderDO.setGoodsName(goods.getName());
        return this.orderService.save(orderDO);
    }

    @RequestMapping(value = "/getAllSell", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDO> getAllSellOrder(String sellerID) {
        return this.orderService.getBySeller(sellerID);
    }

    @RequestMapping(value = "/getAllBuy", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDO> getAllBuyOrder(String buyerID) {
        return this.orderService.getByBuyer(buyerID);
    }

    @RequestMapping(value = "/updateSellerStatus", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateSellerStatus(String sellerID, String status) {
        return this.orderService.updateSellerStatus(sellerID, status);
    }

    @RequestMapping(value = "/updateBuyerStatus", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateBuyerStatus(String buyerID, String status) {
        return this.orderService.updateBuyerStatus(buyerID, status);
    }

    @RequestMapping(value = "/newSellOrder", method = RequestMethod.GET)
    @ResponseBody
    public boolean newSellOrder(String sellerID) {
        return this.orderService.newSellOrder(sellerID);
    }

    @RequestMapping(value = "/newBuyOrder", method = RequestMethod.GET)
    @ResponseBody
    public boolean newBuyOrder(String buyerID) {
        return this.orderService.newBuyOrder(buyerID);
    }

    @RequestMapping(value = "/updateSellerGrade", method = RequestMethod.GET)
    @ResponseBody
    public boolean updateSellerGrade(String ID, String userID, int grade) {
        boolean result = true;
        result = this.orderService.updateSellerGrade(ID, grade);
        if (result) {
            this.recordsService.updateUserCredit(userID, grade);
            this.orderService.updateSellerStatus(ID, OrderStatus.unread.name());
            this.orderService.updateBuyerStatus(ID, OrderStatus.end.name());
        }
        return true;
    }

    @RequestMapping(value = "/updateBuyerGrade", method = RequestMethod.GET)
    @ResponseBody
    public boolean updateBuyerGrade(String ID, String userID, int grade) {
        boolean result = true;
        result = this.orderService.updateBuyerGrade(ID, grade);
        if (result) {
            this.recordsService.updateUserCredit(userID, grade);
            this.orderService.updateSellerStatus(ID, OrderStatus.end.name());
            this.orderService.updateBuyerStatus(ID, OrderStatus.unread.name());
        }
        return true;
    }
}
