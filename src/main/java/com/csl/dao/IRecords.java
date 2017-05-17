package com.csl.dao;

import com.csl.domain.RecordsDO;

/**
 * Created by csl on 2017/5/16.
 */
public interface IRecords extends IBaseDao<RecordsDO> {
    int getSuccess(final String userID);

    int getFail(final String userID);

    int updateSuccess(final String userID, final int count);

    int updateFail(final String userID, final int count);
}
