package com.game.stzb.Model;

public class ActionLog {
    private int id;
    private int userId;
    private String userName;
    private long actionTime;
    private String action;
    private String data;
    private String token;
    private String bak;

    public int getId() {
        return id;
    }

    public ActionLog setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public ActionLog setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ActionLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public long getActionTime() {
        return actionTime;
    }

    public ActionLog setActionTime(long actionTime) {
        this.actionTime = actionTime;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ActionLog setAction(String action) {
        this.action = action;
        return this;
    }

    public String getData() {
        return data;
    }

    public ActionLog setData(String data) {
        this.data = data;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ActionLog setToken(String token) {
        this.token = token;
        return this;
    }

    public String getBak() {
        return bak;
    }

    public ActionLog setBak(String bak) {
        this.bak = bak;
        return this;
    }
}
