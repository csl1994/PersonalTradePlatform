package com.csl.serviceImpl;

import com.csl.daoImpl.RecordsDaoImpl;
import com.csl.daoImpl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by csl on 2017/5/16.
 */
@Service
public class RecordsServiceImpl {
    @Autowired
    private RecordsDaoImpl recordsDao;
    @Autowired
    private UserDaoImpl userDao;

    private int getSuccess(String userID) {
        return this.recordsDao.getSuccess(userID);
    }

    private int getFail(String userID) {
        return this.recordsDao.getFail(userID);
    }

    private boolean updateSuccess(String userID, int count) {
        return this.recordsDao.updateSuccess(userID, count) > 0;
    }

    private boolean updateFail(String userID, int count) {
        return this.recordsDao.updateFail(userID, count) > 0;
    }

    public boolean updateUserCredit(String userID, int currentOrderGrade) {
        int success = this.recordsDao.getSuccess(userID);
        int fail = this.recordsDao.getFail(userID);
        int result = 0;
        double currentCredit = 0;
        double sd = 0;
        double fd = 0;
        double ud = 0;
        double parameterS = 0;
        double parameterF = 0;
        double parameterU = 0;
        if (currentOrderGrade >= 80) {
            success += 1;
            sd = success / (success + fail + 1.0);
            fd = fail / (success + fail + 1.0);
            ud = 1.0 - sd - fd;
            parameterS = new Random().nextDouble() * 0.2 + 0.75;
            parameterF = new Random().nextDouble() * 0.2 + parameterS;
            parameterF = parameterF > 1 ? 1 : parameterF;
            parameterU = success / (success + fail);
            currentCredit = ((parameterS * sd - parameterF * fd + parameterU * ud) * 100.0 );
        } else if (currentOrderGrade >= 60) {
            fail += 1;
            sd = success / (success + fail + 1.0);
            fd = fail / (success + fail + 1.0);
            ud = 1.0 - sd - fd;
            parameterS = new Random().nextDouble() * 0.2 + 0.655;
            parameterF = new Random().nextDouble() * 0.2 + parameterS;
            parameterF = parameterF > 1 ? 1 : parameterF;
            parameterU = success / (success + fail);
            currentCredit = ((parameterS * sd - parameterF * fd + parameterU * ud) * 100.0 );
        } else {
            fail += 1;
            sd = success / (success + fail + 1.0);
            fd = fail / (success + fail + 1.0);
            ud = 1.0 - sd - fd;
            parameterS = new Random().nextDouble() * 0.2 + 0.655;
            parameterF = new Random().nextDouble() * 0.2 + parameterS;
            parameterF = parameterF > 1 ? 1 : parameterF;
            parameterU = 0;
            currentCredit = ((parameterS * sd - parameterF * fd + parameterU * ud) * 100.0 );
        }
        result = Integer.parseInt(new java.text.DecimalFormat("0").format(currentCredit));
        if (result > 100) {
            result = 100;
        } else if (result < 0) {
            result = 0;
        }
        this.recordsDao.updateSuccess(userID, success);
        this.recordsDao.updateFail(userID, fail);
        this.userDao.updateCredit(userID, result);
        return true;
    }
}
