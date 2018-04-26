package com.game.stzb.Model;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserInfo {
    private static Random sRandom = new Random(System.currentTimeMillis());
    public static final int HERO5 = 5;
    public static final int HERO4 = 4;
    public static final int HERO3 = 3;
    public static final int HERO2 = 2;
    public static final int HERO1 = 1;
    public static final int LOGIN_TYPE_WEIXIN = 1;
    public static final int LOGIN_TYPE_QQ = 2;
    public static final int LOGIN_TYPE_AUTO = 0;
    private String name;
    private String uuid;
    private String pwd;
    private String head;
    private String nickname;
    private String phone;
    private String imei;
    private int id;
    private Date lastlogin;
    private Integer seed1;
    private Integer seed2;
    private Integer seed3;
    private Integer seed4;
    private Integer seed5;
    private int logintype;
    private String logininfo;

    public synchronized int getRandomHeroType() {
        int temp = sRandom.nextInt(getSeedCount());
        if (temp < getSeed5()) {
            return HERO5;
        }
        if (temp < getSeed4() + getSeed5()) {
            return HERO4;
        }
        if (temp < getSeed3() + getSeed4() + getSeed5()) {
            return HERO3;
        }
        if (temp < getSeed2() + getSeed3() + getSeed4() + getSeed5()) {
            return HERO2;
        }
        if (temp < getSeed1() + getSeed2() + getSeed3() + getSeed4() + getSeed5()) {
            return HERO1;
        }
        return HERO5;
    }

    public HeroEntity getRandomHero(List<HeroEntity> mHeroEntities) {
        return mHeroEntities.get(sRandom.nextInt(mHeroEntities.size()));
    }

    public int getSeedCount() {
        return seed1 + seed2 + seed3 + seed4 + seed5;
    }

    public Integer getSeed3() {
        return seed3;
    }

    public Integer getSeed1() {
        return seed1;
    }

    public Integer getSeed2() {
        return seed2;
    }

    public UserInfo setSeed1(int mSeed1) {
        seed1 = mSeed1;
        return this;
    }

    public int getLogintype() {
        return logintype;
    }

    public UserInfo setLogintype(int mLogintype) {
        logintype = mLogintype;
        return this;
    }

    public String getLogininfo() {
        return logininfo;
    }

    public UserInfo setLogininfo(String mLogininfo) {
        logininfo = mLogininfo;
        return this;
    }

    public UserInfo setSeed2(int mSeed2) {
        seed2 = mSeed2;
        return this;
    }

    public UserInfo setSeed3(int mSeed3) {
        seed3 = mSeed3;
        return this;
    }

    public Integer getSeed4() {
        return seed4;
    }

    public UserInfo setSeed4(int mSeed4) {
        seed4 = mSeed4;
        return this;
    }

    public Integer getSeed5() {
        return seed5;
    }

    public UserInfo setSeed5(int mSeed5) {
        seed5 = mSeed5;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String mPhone) {
        phone = mPhone;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public UserInfo setImei(String mImei) {
        imei = mImei;
        return this;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public UserInfo setLastlogin(Date mLastlogin) {
        lastlogin = mLastlogin;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfo setName(String mName) {
        name = mName;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UserInfo setUuid(String mUuid) {
        uuid = mUuid;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public UserInfo setPwd(String mPwd) {
        pwd = mPwd;
        return this;
    }

    public String getHead() {
        return head;
    }

    public UserInfo setHead(String mHead) {
        head = mHead;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInfo setNickname(String mNickname) {
        nickname = mNickname;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserInfo setId(int mId) {
        id = mId;
        return this;
    }
}
