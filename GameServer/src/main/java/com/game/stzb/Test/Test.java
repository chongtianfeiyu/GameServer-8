package com.game.stzb.Test;

import com.game.stzb.DBManager;
import com.game.stzb.GameSTZBDao;
import com.game.stzb.Model.UserInfo;

public class Test {
    public static void main(String[] args) {
//        GameServer.initHeroData();
//        try {
//            GameServer.updateHeroAllInfoColumn();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(GameSTZBDao.updateUserMoney("aaaaaaaaaaaaaaa", 200));
    }


}
