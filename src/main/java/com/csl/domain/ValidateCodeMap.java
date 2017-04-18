package com.csl.domain;

import com.csl.utility.ValidateCode;

/**
 * Created by csl on 2017/4/11.
 */
public class ValidateCodeMap {
    private String name;

    private String result;

    public ValidateCodeMap() {}

    public ValidateCodeMap(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
