package com.csl.controller;

import com.csl.domain.ActionPage;
import com.csl.domain.GoodsDO;
import com.csl.domain.UserDO;
import com.csl.serviceImpl.GoodsServiceImpl;
import com.csl.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by csl on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/goodsSpecific/{goodsID}&{userID}", method = RequestMethod.GET)
    public ModelAndView goodsSpecific(@PathVariable("goodsID") String goodsID, @PathVariable String userID) {
        GoodsDO goodsDO = this.goodsService.getByID(goodsID, userID);
        UserDO userDO = this.userService.getOwner(goodsID);
        ModelAndView modelAndView = new ModelAndView("GoodsSpecific");
        modelAndView.addObject("goods", goodsDO);
        modelAndView.addObject("user", userDO);
        return modelAndView;
    }

    @RequestMapping(value = "/attentionGoods", method = RequestMethod.POST)
    @ResponseBody
    public int attentionGoods(String goodsID, String userID, boolean isAttention) {
        try {
            if (isAttention) {
                this.goodsService.attentionGoods(userID, goodsID);
                return 1;
            } else {
                this.goodsService.removeAttention(userID, goodsID);
                return 2;
            }
        } catch (Exception exception) {

        }
        return -1;
    }

    @RequestMapping(value = "/getTop5", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsDO> getTop5() {
        return this.goodsService.getTop5();
    }

    @RequestMapping(value = "/viewCollection", method = RequestMethod.GET)
    public ModelAndView viewCollection() {
        return new ModelAndView("UserCollection");
    }

    @RequestMapping(value = "/getCollection")
    @ResponseBody
    public List<GoodsDO> getCollection(String userID) {
        return this.goodsService.getGoodsByUserID(userID, ActionPage.attention);
    }

}
