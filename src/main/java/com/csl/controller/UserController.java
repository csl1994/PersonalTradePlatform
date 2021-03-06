package com.csl.controller;

import com.csl.domain.FinalValue;
import com.csl.domain.UserDO;
import com.csl.domain.ValidateCodeMap;
import com.csl.serviceImpl.MailServiceImpl;
import com.csl.serviceImpl.UserServiceImpl;
import com.csl.utility.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by csl on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MailServiceImpl mailService;
    private String validateCode = "";
    private ValidateCode validateCoder = ValidateCode.getInstance();
    private int validateIndex = 0;
    private ArrayList<ValidateCodeMap> validateMapList = new ArrayList<ValidateCodeMap>();

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    @ResponseBody
    public UserDO getUserByEmail(String email) {
        return this.userService.getUserByEmail(email);
    }

    @RequestMapping(value = "/getUserByID", method = RequestMethod.GET)
    @ResponseBody
    public UserDO getUserByID(String ID) {
        return this.userService.getUserByID(ID);
    }

    @RequestMapping(value = "/validateUser", method = RequestMethod.GET)
    @ResponseBody
    public boolean validateUser(String email, String password) {
        return this.userService.isRightUser(email, password);
    }

    @RequestMapping(value = "/getValidateCode", method = RequestMethod.GET)
    @ResponseBody
    public String getValidateCode() {
        String result = "";
        if (this.validateMapList != null && this.validateMapList.size() == 0) {
            this.validateMapList = this.validateCoder.getValidateMap();
        }
        int index = new Random().nextInt(this.validateMapList.size());
        while (index == this.validateIndex) {
            index = new Random().nextInt(this.validateMapList.size());
        }
        this.validateIndex = index;
        result = FinalValue.filePath + this.validateMapList.get(index).getName() + ".PNG";
        return result;
    }

    @RequestMapping(value = "/checkValidateCode", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkValidateCode(String validateCode, int kind) {
        boolean result = true;
        switch (kind) {
            case 1:
                result = validateCode.equals(this.validateMapList.get(validateIndex).getResult());
                ;
                break;
            case 2:
                result = this.validateCode != "" && this.validateCode.equals(validateCode);
                break;
            default:
                break;
        }
        return result;
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkEmail(String userEmail) {
        return this.userService.checkEmail(userEmail);
    }

    @RequestMapping(value = "/checkName", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkName(String userName) {
        return this.userService.checkName(userName);
    }

    @RequestMapping(value = "/checkTelephone", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkTelephone(String telephone) {
        return this.userService.checkTelephone(telephone);
    }

    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
    @ResponseBody
    public UserDO registerAccount(UserDO userDO) {
        userDO.setID(UUID.randomUUID().toString());
        try {
            boolean result = this.userService.registerAccount(userDO);
            if (result) {
                return userDO;
            }
        } catch (Exception exception) {

        }
        return null;
    }

    @RequestMapping(value = "/sendValidateCode", method = RequestMethod.GET)
    @ResponseBody
    public boolean sendValidateCode(String email) {
        boolean result = true;
        try {
            this.validateCode = this.mailService.sendValidateCode(email);
        } catch (Exception exception) {
            result = false;
        }
        return result;
    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    @ResponseBody
    public UserDO updateAccount(String email, String password) {
        try {
            boolean result = this.userService.resetPassword(email, password);
            if (result) {
                return this.userService.getUserByEmail(email);
            }
        } catch (Exception exception) {

        }
        return null;
    }

    @RequestMapping(value = "/modifyMessage", method = RequestMethod.POST)
    @ResponseBody
    public boolean modifyMessage(String ID, String telephone) {
        return this.userService.changeMessage(ID, telephone);
    }

    @RequestMapping(value = "/getOwner", method = RequestMethod.GET)
    @ResponseBody
    public UserDO getOwner(String goodsID) {
        return this.userService.getOwner(goodsID);
    }

    @RequestMapping(value = "/getFiveCity", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFiveCity() {
        return this.userService.getFiveCity();
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public boolean addFriend(String oneID, String twoID, String name) {
        return this.userService.addFriend(oneID, twoID, name);
    }

    @RequestMapping(value = "/getAllFriends", method = RequestMethod.POST)
    @ResponseBody
    public List<UserDO> getAllFriends(String oneID) {
        return this.userService.getAllFriends(oneID);
    }

    @RequestMapping(value = "/checkFriend", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkFriend(String oneID, String twoID) {
        return this.userService.checkFriend(oneID, twoID);
    }

    @RequestMapping(value = "/removeFriend", method = RequestMethod.POST)
    @ResponseBody
    public boolean removeFriend(String oneID, String twoID) {
        return this.userService.removeFriend(oneID, twoID);
    }
}