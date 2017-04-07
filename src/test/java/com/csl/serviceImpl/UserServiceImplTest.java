package com.csl.serviceImpl;

import com.csl.domain.UserDO;
import com.csl.utility.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by csl on 2017/3/12.
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceImplTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void validateUser(){
        boolean result = userService.isRightUser("18840824441@163.com","123456");
        boolean result2 = this.userService.isRightUser("123", "123");
        assertTrue(result);
    }

    @Test
    public void registerUser(){
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
    public void changePassword(){
        ValidateCode validateCoder = ValidateCode.getInstance();
        String name = validateCoder.generateImages();
        //boolean result = userService.resetPassword("18840824441@163.com","123456");
        //assertTrue(result);
    }

    @Test
    public void getUser(){
        UserDO user1 = userService.getUserByEmail("18840824441@163.com");
        UserDO user2 = userService.getUserByID(user1.getID());
        assertEquals(user1,user2);
    }
}
