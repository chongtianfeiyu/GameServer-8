package com.game.stzb.Test;

import com.alibaba.fastjson.JSON;
import com.game.stzb.GameSTZBDao;
import com.game.stzb.Model.UserInfo;
import com.game.stzb.Utils;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {
//        GameServer.initHeroData();
//        try {
//            GameServer.updateHeroAllInfoColumn();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
test1();
    }

    /**
     * 获得到的武将数量
     */
    public static void test1(){
        //        System.out.println(GameSTZBDao.updateUserMoney("aaaaaaaaaaaaaaa", 200));

//        System.out.println(GameSTZBDao.updateHeroCountColumn(10047, Utils.getDefaultHeroCount()));
//        ;
        UserInfo userInfo = GameSTZBDao.getUserInfo(10047);
        System.out.println(Utils.byte2int(userInfo.getHerocount()[193]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[274]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[360]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[43]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[171]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[183]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[54]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[244]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[254]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[87]));
//        System.out.println(Utils.byte2int(userInfo.getHerocount()[121]));
//        try {
//            System.out.println(new String(userInfo.getHerocount(), "gbk"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(JSON.toJSONString(userInfo));
    }

}
