package com.game.stzb.Model;

import com.alibaba.fastjson.JSON;

public class BaseRequest {
    public static final String GET_HEROLIST = "get_heroList";
    public static final String GET_CUSTOMHEROLIST = "get_customHeroList";
    public static final String GET_GOODLUCKNUMBER = "get_GOODLUCK";
    private String flag = "GameSTZB";
    private String action;
    private String token;
    private Integer pageIndex;
    private Integer pageSize;
    private String data;

    public String getFlag() {
        return flag;
    }

    public BaseRequest setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String getAction() {
        return action;
    }

    public BaseRequest setAction(String action) {
        this.action = action;
        return this;
    }

    public String getToken() {
        return token;
    }

    public BaseRequest setToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public BaseRequest setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getData() {
        return data;
    }

    public <T> T getData(Class<T> mClass) {
        return JSON.parseObject(data, mClass);
    }

    public BaseRequest setData(String data) {
        this.data = data;
        return this;
    }

    public String toJson() {
        if (token == null) {
            initToken();
        }
        return JSON.toJSONString(this);
    }

    public void initToken() {

    }

    public static class getRandomHeroEntity {
        private int randomNum;

        public int getRandomNum() {
            return randomNum;
        }

        public getRandomHeroEntity setRandomNum(int mRandomNum) {
            randomNum = mRandomNum;
            return this;
        }
    }
}
