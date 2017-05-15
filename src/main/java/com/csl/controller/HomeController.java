package com.csl.controller;

import com.csl.domain.GoodsDO;
import com.csl.domain.GoodsKind;
import com.csl.domain.SortBasis;
import com.csl.serviceImpl.GoodsServiceImpl;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csl on 2017/3/21.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsDO> getGoodsList(String userID, String region, String goodsKind, String sortBasis, int page) {
        List<GoodsDO> goodsDOList = new ArrayList<GoodsDO>();
        try {
            goodsDOList = this.goodsService.getGoods(userID, region == "" ? null : region
                    , goodsKind == "" ? null : GoodsKind.valueOf(goodsKind), SortBasis.valueOf(sortBasis), page);
        } catch (Exception exception) {

        }
        return goodsDOList;
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.GET)
    @ResponseBody
    public int getCount(String userID, String region, String goodsKind) {
        int count = 0;
        try {
            count = this.goodsService.getCount(userID, region == "" ? null : region, goodsKind == "" ? null : GoodsKind.valueOf(goodsKind));
        } catch (Exception exception) {

        }
        return count;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(){
        return new ModelAndView("websocket");
    }
}