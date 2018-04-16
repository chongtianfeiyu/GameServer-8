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
    private String name;
    private String uuid;
    private String pwd;
    private String head;
    private String nickname;
    private String phone;
    private String imei;
    private int id;
    private Date lastlogin;
    private int seed1;
    private int seed2;
    private int seed3;
    private int seed4;
    private int seed5;

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

    public int getSeed3() {
        return seed3;
    }

    public int getSeed1() {
        return seed1;
    }

    public int getSeed2() {
        return seed2;
    }

    public UserInfo setSeed1(int mSeed1) {
        seed1 = mSeed1;
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

    public int getSeed4() {
        return seed4;
    }

    public UserInfo setSeed4(int mSeed4) {
        seed4 = mSeed4;
        return this;
    }

    public int getSeed5() {
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
