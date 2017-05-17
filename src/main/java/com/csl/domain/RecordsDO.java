package com.csl.domain;

/**
 * Created by csl on 2017/5/17.
 */
public class RecordsDO {
    private String userID;

    private int success;

    private int fail;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }
}
