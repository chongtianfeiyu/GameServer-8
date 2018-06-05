package com.game.stzb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.stzb.Model.BaseRequest;
import com.game.stzb.Model.HeroDetailEntity;
import com.game.stzb.Model.HeroEntity;
import com.game.stzb.Model.UserInfo;
import com.itgowo.SimpleServerCore.Http.HttpServerHandler;
import com.itgowo.http.ServerJsonEntity;
import io.netty.handler.codec.http.HttpMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;


public class GameServer extends GameSTZBDao {
    public static final String ROOTPATH = "/GameSTZB";


    public static List<HeroEntity> sHeroAll = new Vector<>();
    public static List<HeroEntity> sHero5 = new Vector<>();
    public static List<HeroEntity> sHero3 = new Vector<>();
    public static List<HeroEntity> sHero4 = new Vector<>();
    public static List<HeroEntity> sHero2 = new Vector<>();
    public static List<HeroEntity> sHero1 = new Vector<>();
    public static Map<Integer, HeroEntity> heroEntityMap = new HashMap<>();
    private static Map<String, HeroEntity> HeroTreitelCache = new HashMap<>();

    static {
        initData();
//        initHeroData();
    }

    private static void initData() {
        sHeroAll.addAll(getHeroList());
        for (int mI = 0; mI < sHeroAll.size(); mI++) {
            HeroEntity mHeroEntity = sHeroAll.get(mI);
            heroEntityMap.put(mHeroEntity.getId(), mHeroEntity);
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

    public static void doGame_STZB(HttpServerHandler handler, ServerJsonEntity serverJsonEntity) throws UnsupportedEncodingException {
        if (handler.getHttpRequest().method() == HttpMethod.GET) {
            int num = 5;
            List<String> nums = handler.getParameters().get("randomNum");
            if (nums != null && nums.size() != 0) {
                try {
                    num = Integer.parseInt(nums.get(0));
                } catch (Exception mE) {
                    mE.printStackTrace();
                }
            }
            BaseRequest mRequest = new BaseRequest().setToken(BaseRequest.DEFAULT_USER_UUID).setData(JSON.toJSONString(new BaseRequest.DataEntity().setRandomNum(num)));
            List<HeroEntity> mHeroEntities = getRandomHero(handler, mRequest, null);
            handler.sendData(htmlCreator.getRandomHeroHtml(mHeroEntities), false);

        } else {
            BaseRequest request = JSON.parseObject(handler.getBody(Charset.forName("utf-8")), BaseRequest.class);
            switch (request.getAction()) {
                case BaseRequest.GET_RANDOM_HERO:
                    getRandomHero(handler, request, serverJsonEntity);
                    break;
                case BaseRequest.GET_HERO_LIST:
                    handler.sendData(serverJsonEntity.setData(getHeroList()), true);
                    break;
                case BaseRequest.GET_HERO_DETAIL_LIST:
                    handler.sendData(serverJsonEntity.setData(getHeroDetailList()), true);
                    break;
                case BaseRequest.GET_HERO_DETAIL:
                    getHeroDetail(handler, request, serverJsonEntity);
                    break;
                case BaseRequest.REG_USER:
                    registerUser(handler, request, serverJsonEntity);
                    break;
                case BaseRequest.GET_USER_GAME_MONEY:
                    getUserMoney(handler, request, serverJsonEntity);
                    break;
                case BaseRequest.GET_HERO_GUESS:
                    getHeroGuess(handler, request, serverJsonEntity);
                    break;
                case BaseRequest.POST_HERO_GUESS:
                    postHeroGuess(handler, request, serverJsonEntity);
                    break;
                default:
                    handler.sendData(new ServerJsonEntity().setCode(ServerJsonEntity.Fail).setMsg("Error 404,The action to be lost，请求动作没有找到，请检查参数是否正确！  枫林开小差了！"), true);
            }
        }
    }

    private static void postHeroGuess(HttpServerHandler handler, BaseRequest request, ServerJsonEntity serverJsonEntity) throws UnsupportedEncodingException {
        HeroEntity heroEntity = HeroTreitelCache.remove(request.getToken());
        if (heroEntity != null && !heroEntity.checkTimeOut()) {
            HeroEntity entity = request.getData(HeroEntity.class);
            if (entity != null && entity.getName().equalsIgnoreCase(heroEntity.getName()) && entity.getContory().equalsIgnoreCase(heroEntity.getContory())) {
                Map<String, Long> data = new HashMap<>();
                data.put("game_money", GameSTZBDao.updateUserMoney(request.getToken(), (6 - heroEntity.getQuality()) * 200));
                handler.sendData(serverJsonEntity.setData(data), true);
            } else {
                handler.sendData(serverJsonEntity.setCode(ServerJsonEntity.Fail).setMsg("答案错误,此人来自" + heroEntity.getContory() + " 名叫 " + heroEntity.getName()), true);
            }
        } else {
            handler.sendData(serverJsonEntity.setCode(ServerJsonEntity.Fail).setMsg(heroEntity == null ? "答案已失效" : "请在15秒内提交答案"), true);
        }
    }

    private static void getHeroGuess(HttpServerHandler handler, BaseRequest mRequest, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        if (BaseRequest.DEFAULT_USER_UUID.equalsIgnoreCase(mRequest.getToken())) {
            handler.sendData(mServerJsonEntity.setCode(ServerJsonEntity.Fail).setMsg("请先登录再使用此功能"), true);
            return;
        }
        Random random = new Random(System.currentTimeMillis());
        Set<HeroEntity> heroEntities = new TreeSet<>();
        HeroEntity entity = null;
        while (heroEntities.size() < 4) {
            entity = sHeroAll.get(random.nextInt(sHeroAll.size()));
            if (entity.getNormal() < 1) {
                heroEntities.add(entity.copySimple());
            }
        }
        HeroTreitelCache.put(mRequest.getToken(), entity.initTimeTreitel());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("option", heroEntities);
        handler.sendData(mServerJsonEntity.setData(jsonObject), true);
    }

    private static void getHeroDetail(HttpServerHandler handler, BaseRequest mRequest, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        HeroDetailEntity entity = getHeroInfo(mRequest.getData(BaseRequest.DataEntity.class).getId());
        if (entity == null) {
            handler.sendData(mServerJsonEntity.setCode(ServerJsonEntity.Code404).setMsg("未找到该武将"), true);
        } else {
            handler.sendData(mServerJsonEntity.setData(entity), true);
        }
    }

    private static void getUserMoney(HttpServerHandler handler, BaseRequest request, ServerJsonEntity serverJsonEntity) throws UnsupportedEncodingException {
        Map<String, Long> data = new HashMap<>();
        data.put("game_money", GameSTZBDao.getUserMoney(request.getToken()));
        handler.sendData(serverJsonEntity.setData(data), true);
    }

    private static List<HeroEntity> getRandomHero(HttpServerHandler handler, BaseRequest request, ServerJsonEntity serverJsonEntity) throws UnsupportedEncodingException {
        int mNum = request.getData(BaseRequest.DataEntity.class).getRandomNum();
        if (mNum < 1 || mNum > 5) {
            handler.sendData(serverJsonEntity.setMsg("参数错误，别想通过改参数改数据").setCode(ServerJsonEntity.Fail), true);
            return null;
        }
        int payMoney = mNum * 200 - (mNum == 5 ? 50 : 0);
        UserInfo mUserInfo = getUserInfo(request.getToken());
        if (!request.isDefaultGuest() && mUserInfo.getGame_money() < payMoney) {
            handler.sendData(serverJsonEntity.setMsg("余额不足，请参加活动获取").setCode(ServerJsonEntity.Fail), true);
            return null;
        }
        List<HeroEntity> mHeroEntities = new ArrayList<>();
        for (int mI = 0; mI < mNum; mI++) {
            switch (mUserInfo.getRandomHeroType()) {
                case UserInfo.HERO1:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero1));
                    break;
                case UserInfo.HERO2:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero2));
                    break;
                case UserInfo.HERO3:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero3));
                    break;
                case UserInfo.HERO4:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero4));
                    break;
                case UserInfo.HERO5:
                    mHeroEntities.add(mUserInfo.getRandomHero(sHero5));
                    break;
            }
        }
        if (serverJsonEntity == null) {
            return mHeroEntities;
        } else {
            long money = request.isDefaultGuest() ? 0 : GameSTZBDao.updateUserMoney(request.getToken(), -payMoney);
            Map<String, Object> data = new HashMap<>();
            data.put("game_money", money);
            data.put("herolist", mHeroEntities);
            //todo 兼容方案，以后删除
            if (request.getAppVersion() <= 204) {
                handler.sendData(serverJsonEntity.setData(mHeroEntities), true);
            } else {
                handler.sendData(serverJsonEntity.setData(data), true);
            }
            return null;
        }
    }

    private static void registerUser(HttpServerHandler handler, BaseRequest mRequest, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        UserInfo mUserInfo = JSON.parseObject(mRequest.getData(), UserInfo.class);
        mUserInfo = registerOrLoginUser(mUserInfo);
        handler.sendData(mServerJsonEntity.setData(mUserInfo), true);
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

    public static void initHeroData() {
        try {
            InputStream mInputStream = Resources.getResourceAsStream("config/herolist");
            byte[] mBytes = new byte[mInputStream.available()];
            mInputStream.read(mBytes);
            String temp = new String(mBytes, "utf-8");
            List<HeroEntity> mHeroEntities = JSON.parseArray(temp, HeroEntity.class);
            InputStream mInputStream2 = Resources.getResourceAsStream("config/herolist2");
            byte[] mBytes2 = new byte[mInputStream2.available()];
            mInputStream2.read(mBytes2);
            String temp2 = new String(mBytes2, "utf-8");
            List<HeroDetailEntity> mHeroEntities2 = JSON.parseArray(temp2, HeroDetailEntity.class);
            for (int i = mHeroEntities2.size() - 1; i >= 0; i--) {
                for (int size = mHeroEntities.size() - 1; size >= 0; size--) {
                    if (mHeroEntities.get(size).getId() == mHeroEntities2.get(i).getId()) {
                        mHeroEntities.remove(size);
                        mHeroEntities2.remove(i);
                        break;
                    }
                }
            }
            for (int i = 0; i < mHeroEntities2.size(); i++) {
                System.out.println(String.format("https://stzb.res.netease.com/pc/qt/20170323200251/data/role/card_%s.jpg", mHeroEntities2.get(i).getId()));
            }
            for (int i = 0; i < mHeroEntities.size(); i++) {
                System.out.println(mHeroEntities.get(i).getId() + mHeroEntities.get(i).getName());
            }
        } catch (IOException mE) {
            mE.printStackTrace();
        }
    }

    private static String NetHeroAllInfoURL = "https://app.gamer.163.com/game-db/g10/hero";


    public static void updateHeroAllInfoColumn() throws IOException {
        List<HeroEntity> list = GameServer.getHeroList();
        list.sort((o1, o2) -> o1.getId() - o2.getId());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getId() + list.get(i).getName());
        OkHttpClient client = new OkHttpClient();
        // Create request for remote resource.
        Request request = new Request.Builder().url(NetHeroAllInfoURL).build();
        Response response = client.newCall(request).execute();
        String bodyjson = response.body().string();
        JSONObject jsonObject = JSON.parseObject(bodyjson);
        JSONObject jsonObject1 = jsonObject.getJSONObject("hero");
        List<HeroDetailEntity> list1 = new ArrayList<>();
        for (String s : jsonObject1.keySet()) {
            HeroDetailEntity heroBean = jsonObject1.getJSONObject(s).toJavaObject(HeroDetailEntity.class);
            list1.add(heroBean);
//            GameSTZBDao.addHero(heroBean);
        }
        String ss = JSON.toJSONString(list1);
        List<HeroDetailEntity> heroBeans = JSON.parseArray(ss, HeroDetailEntity.class);
        System.out.println(11);
    }

}
