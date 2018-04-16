package com.game.stzb;

import com.alibaba.fastjson.JSON;
import com.game.stzb.Model.BaseRequest;
import com.game.stzb.Model.HeroEntity;
import com.game.stzb.Model.UserInfo;
import com.itgowo.http.HttpServerManager;
import com.itgowo.http.ServerJsonEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static com.game.stzb.Model.UserInfo.*;

public class GameServer extends GameSTZBDao {
    public static final String ROOTPATH = "/GameSTZB";
    public static final String GETRANDOMHERO = "getRandomHero";
    public static final String GETHEROLIST = "getHeroList";
    public static final String REGUSER = "regUser";

    public static List<HeroEntity> sHeroAll = new Vector<>();
    public static List<HeroEntity> sHero5 = new Vector<>();
    public static List<HeroEntity> sHero3 = new Vector<>();
    public static List<HeroEntity> sHero4 = new Vector<>();
    public static List<HeroEntity> sHero2 = new Vector<>();
    public static List<HeroEntity> sHero1 = new Vector<>();

    static {
        initData();
    }

    private static void initData() {
        sHeroAll.addAll(getHeroList());
        for (int mI = 0; mI < sHeroAll.size(); mI++) {
            HeroEntity mHeroEntity = sHeroAll.get(mI);
            switch (mHeroEntity.getQuality()) {
                case 5:
                    sHero5.add(mHeroEntity);
                    break;
                case 4:
                    sHero4.add(mHeroEntity);
                    break;
                case 3:
                    sHero3.add(mHeroEntity);
                    break;
                case 2:
                    sHero2.add(mHeroEntity);
                    break;
                case 1:
                    sHero1.add(mHeroEntity);
                    break;
            }
        }
    }

    public static void doGame_STZB(ChannelHandlerContext ctx, HttpRequest mHttpRequest, HttpContent mHttpContent, String mUri, Map<String, List<String>> mUriQuery, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        if (mHttpRequest.method() == HttpMethod.GET) {
            int num = 5;
            List<String> nums = mUriQuery.get("randomNum");
            if (nums != null && nums.size() != 0) {
                try {
                    num = Integer.parseInt(nums.get(0));
                } catch (Exception mE) {
                    mE.printStackTrace();
                }

            }
            BaseRequest mRequest = new BaseRequest().setToken("aaaaaaaaaaaaaaaa").setData(JSON.toJSONString(new BaseRequest.getRandomHeroEntity().setRandomNum(num)));
            HttpServerManager.sendResponse(ctx, mServerJsonEntity.setData(getRandomHero(ctx, mHttpRequest, mRequest, mUri, mUriQuery, mServerJsonEntity)));

        } else {
            BaseRequest mRequest = JSON.parseObject(mHttpContent.content().toString(CharsetUtil.UTF_8), BaseRequest.class);
            switch (mRequest.getAction()) {
                case GETRANDOMHERO:
                    HttpServerManager.sendResponse(ctx, mServerJsonEntity.setData(getRandomHero(ctx, mHttpRequest, mRequest, mUri, mUriQuery, mServerJsonEntity)));
                    break;
                case GETHEROLIST:
                    HttpServerManager.sendResponse(ctx, mServerJsonEntity.setData(getHeroList()));
                    break;
                case REGUSER:
                    registerUser(ctx, mHttpRequest, mRequest, mUri, mUriQuery, mServerJsonEntity);
                    break;
            }
        }
    }

    private static List<HeroEntity> getRandomHero(ChannelHandlerContext ctx, HttpRequest mHttpRequest, BaseRequest mRequest, String mUri, Map<String, List<String>> mUriQuery, ServerJsonEntity mServerJsonEntity) {
        int mNum = mRequest.getData(BaseRequest.getRandomHeroEntity.class).getRandomNum();
        UserInfo mUserInfo = getUserInfo(mRequest.getToken());
        List<HeroEntity> mHeroEntities = new ArrayList<>();
        for (int mI = 0; mI < mNum; mI++) {
            switch (mUserInfo.getRandomHeroType()) {
                case HERO1:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero1));
                    break;
                case HERO2:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero2));
                    break;
                case HERO3:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero3));
                    break;
                case HERO4:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero4));
                    break;
                case HERO5:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero5));
                    break;
            }
        }
        return mHeroEntities;
    }

    private static void registerUser(ChannelHandlerContext ctx, HttpRequest mHttpRequest, BaseRequest mRequest, String mUri, Map<String, List<String>> mUriQuery, ServerJsonEntity mServerJsonEntity) {
        UserInfo mUserInfo = JSON.parseObject(mRequest.getData(), UserInfo.class);
        registerUser(mUserInfo);
    }


    /**
     * 0-45  5星
     * 46-134  4星
     * 135-219 3星
     * <p>
     */
    private void goodluck() {
//
//        final HeroEntity entity;
//        int temp = random.nextInt(30);
//        if (temp < seed) {
//            entity = heroEntities.get(random.nextInt(221));
//        } else if (temp < seed * 2) {
//            entity = heroEntities.get(48 + random.nextInt(173));
//        } else {
//            entity = heroEntities.get(137 + random.nextInt(84));
//        }
    }
}
