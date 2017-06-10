package com.csl.controller;

import com.alibaba.fastjson.JSON;
import com.csl.domain.*;
import com.csl.serviceImpl.GoodsServiceImpl;
import com.csl.serviceImpl.ImageServiceImpl;
import com.csl.serviceImpl.UserServiceImpl;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private ImageServiceImpl imageService;

    private String imageUrl = "";

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
    public List<GoodsDO> getTop5(String userID) {
        return this.goodsService.getTop5(userID);
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

    @RequestMapping(value = "/viewRepository", method = RequestMethod.GET)
    public ModelAndView viewRepository() {
        return new ModelAndView("GoodsRepository");
    }

    @RequestMapping(value = "/getRepository", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsDO> getRepository(String userID) {
        return this.goodsService.getGoodsByUserID(userID, ActionPage.own);
    }

    @RequestMapping(value = "/editGoods/{goodsID}", method = RequestMethod.GET)
    public ModelAndView editGoods(@PathVariable String goodsID) {
        GoodsDO goodsDO = this.goodsService.find(goodsID);
        ModelAndView modelAndView = new ModelAndView("GoodsEdit");
        modelAndView.addObject("goods", goodsDO);
        modelAndView.addObject("pageType", "edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public boolean update(String goodsJSON) {
        GoodsDO goodsDO = JSON.parseObject(goodsJSON, GoodsDO.class);
        if (goodsDO.getImageUrl() == "") {
            goodsDO.setImageUrl(this.imageUrl);
        }
        return this.goodsService.update(goodsDO);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public boolean remove(String goodsID, int type) {
        boolean result = true;
        switch (type) {
            case 1:
                result = this.goodsService.remove(goodsID);
                break;
            case 2:
                result = this.goodsService.removeSoldGoods(goodsID);
                break;
            default:
                break;
        }
        return result;
    }

    @RequestMapping(value = "/createGoods", method = RequestMethod.GET)
    public ModelAndView createGoods() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setID(UUID.randomUUID().toString());
        ModelAndView modelAndView = new ModelAndView("GoodsEdit");
        modelAndView.addObject("goods", goodsDO);
        modelAndView.addObject("pageType", "create");
        return modelAndView;
    }

    @RequestMapping(value = "/friendGoods/{twoID}", method = RequestMethod.GET)
    public ModelAndView friendGoods(@PathVariable("twoID") String twoID) {
        ModelAndView modelAndView = new ModelAndView("FriendGoods");
        modelAndView.addObject("twoID", twoID);
        return new ModelAndView("FriendGoods");
    }

    @RequestMapping(value = "/upImage", method = RequestMethod.POST)
    @ResponseBody
    public boolean upImage(@RequestParam("file") MultipartFile imageFile) {
        try {
            this.imageUrl = this.imageService.upImage(imageFile.getBytes());
        } catch (Exception exception) {

        }
        return true;
    }

    @RequestMapping(value = "/addGoods", method = RequestMethod.GET)
    @ResponseBody
    public boolean addGoods(String goodsJSON, String userID) {
        GoodsDO goodsDO = JSON.parseObject(goodsJSON, GoodsDO.class);
        goodsDO.setImageUrl(this.imageUrl);
        goodsDO.setStatus(GoodsStatus.unsold.name());
        return this.goodsService.save(goodsDO, userID);
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    @ResponseBody
    public boolean updateStatus(String goodsID, String status) {
        return this.goodsService.updateStatus(goodsID, GoodsStatus.valueOf(status));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsDO> search(String userID, String text) {
        return null;
    }
}
