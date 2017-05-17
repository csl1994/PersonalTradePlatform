package com.csl.serviceImpl;

import com.csl.domain.UserDO;
import com.csl.utility.ValidateCode;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by csl on 2017/3/12.
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void validateUser() {
        boolean result = userService.isRightUser("18840824441@163.com", "123456");
        boolean result2 = this.userService.isRightUser("123", "123");
        assertTrue(result);
    }

    @Test
    public void registerUser() {
        UserDO userDO = new UserDO();
        userDO.setID(UUID.randomUUID().toString());
        userDO.setEmail("18840824444@163.com");
        userDO.setPassword("650802");
        userDO.setRegion("dalian");
        userDO.setName("user3");
        userDO.setCredit(50);
        userDO.setTelephone("18840824444");
        boolean result1 = userService.registerCount(userDO);
        assertTrue(result1);
    }

    @Test
    public void changePassword() {
        ValidateCode validateCoder = ValidateCode.getInstance();
    }

    @Test
    public void getUser() {
        UserDO user1 = userService.getUserByEmail("18840824441@163.com");
        UserDO user2 = userService.getUserByID(user1.getID());
        assertEquals(user1, user2);
    }

    @Test
    public void test() {
        double d = 1.2312;
        String string = d + "";
        int j = 0;
        String str = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1);
        int intgeo = Integer.parseInt(str);
        int a = Integer.parseInt(new java.text.DecimalFormat("0").format(d));
    }
}
