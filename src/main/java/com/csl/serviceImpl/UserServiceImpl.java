package com.csl.serviceImpl;

import com.csl.daoImpl.UserDaoImpl;
import com.csl.domain.UserDO;
import com.csl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by csl on 2017/3/8.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDaoImpl userDao;

    public Boolean isRightUser(String email, String password) {
        UserDO userDO = this.userDao.getByEmail(email);
        boolean result = false;
        if (userDO.getPassword() != null) {
            result = userDO.getPassword().equals(password);
        }
        return result;
    }

    public UserDO getUserByID(String ID) {
        return this.userDao.find(ID);
    }

    public UserDO getUserByEmail(String email) {
        return this.userDao.getByEmail(email);
    }

    public Boolean setUserInformation(UserDO userDO) {
        return this.userDao.update(userDO) > 0;
    }

    public Boolean registerCount(UserDO userDO) {
        return this.userDao.save(userDO) > 0;
    }

    public Boolean resetPassword(String email, String password) {
        return this.userDao.updatePassword(email, password) > 0;
    }

    public Boolean checkEmail(String email) {
        return this.userDao.checkEmail(email) <= 0;
    }

    public Boolean checkName(String name) {
        return this.userDao.checkName(name) <= 0;
    }

    public Boolean checkTelephone(String telephone) {
        return this.userDao.checkTelephone(telephone) <= 0;
    }
}
