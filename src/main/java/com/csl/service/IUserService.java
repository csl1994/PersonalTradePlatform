package com.csl.service;

import com.csl.domain.UserDO;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by csl on 2017/3/8.
 */
public interface IUserService {
    Boolean isRightUser(String email, String password);

    UserDO getUserByID(String ID);

    Boolean setUserInformation(UserDO userDO);

    Boolean registerCount(UserDO userDO);

    Boolean resetPassword(String email,String password);

    Boolean checkEmail(String email);

    Boolean checkName(String name);

    Boolean checkTelephone(String telephone);
}