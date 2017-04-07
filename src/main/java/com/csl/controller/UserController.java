package com.csl.controller;

import com.csl.domain.UserDO;
import com.csl.serviceImpl.UserServiceImpl;
import com.csl.utility.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.awt.image.BufferedImage;

/**
 * Created by csl on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    private ValidateCode validateCoder = ValidateCode.getInstance();
    private String validateResult;

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
        String name = validateCoder.generateImages();
        this.validateCoder.getValidateCode();
        return name;
    }
}
