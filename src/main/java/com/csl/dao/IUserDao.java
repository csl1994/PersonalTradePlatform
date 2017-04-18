package com.csl.dao;

import com.csl.domain.UserDO;

/**
 * Created by csl on 2017/3/7.
 */
public interface IUserDao extends IBaseDao<UserDO> {
    UserDO getByEmail(final String email);

    int updatePassword(final String email, final String password);

    int checkEmail(final String email);

    int checkName(final String name);

    int checkTelephone(final String telephone);
}
