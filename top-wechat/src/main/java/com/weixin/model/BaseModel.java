package com.weixin.model;


import com.weixin.util.Jackson;

import java.io.Serializable;

/**
 * Created by guishuangfeng on 2015/6/9.
 */
public class BaseModel implements Serializable {
    public String toString() {
        return Jackson.asString(this);
    }
}
