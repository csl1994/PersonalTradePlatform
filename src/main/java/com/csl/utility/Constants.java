package com.csl.utility;

/**
 * Created by csl on 2017/5/12.
 */
public enum Constants {
    SESSION_USERNAME("USERNAME");
    private String username;
    Constants(String username) {
        this.username=username;
    }
    public String value(){
        return this.username;
    }
}
