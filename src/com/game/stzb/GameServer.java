package com.game.stzb;

import com.alibaba.fastjson.JSON;
import com.game.stzb.Model.HeroEntity;
import com.itgowo.http.HttpServerManager;
import com.itgowo.http.ServerJsonEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import jdk.internal.util.xml.impl.Input;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.util.List;
import java.util.Map;

public class GameServer {
    public static void doGame_STZB(ChannelHandlerContext ctx, HttpRequest mHttpRequest, HttpContent mHttpContent, String mUri, Map<String, List<String>> mUriQuery, ServerJsonEntity mServerJsonEntity) throws UnsupportedEncodingException {
        List<HeroEntity> mHeroEntitys;
        int count = 0;
        try {
            BufferedReader mReader=new BufferedReader(new InputStreamReader(Resources.getResourceAsStream("config/herolist")));
            mHeroEntitys=JSON.parseArray(mReader.readLine(),HeroEntity.class);
            for (int mI = 0; mI < mHeroEntitys.size(); mI++) {
               if (addHero(mHeroEntitys.get(mI))==-1){
                   count++;
               }
            }
        } catch (FileNotFoundException mE) {
            mE.printStackTrace();
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        mServerJsonEntity.setData(count);
        HttpServerManager.sendResponse(ctx, mServerJsonEntity);
    }

    public static int addHero(HeroEntity mHeroEntity) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            int result = mGameDao_stzb.addHero(DBManager.STZB_DATATABLE_HERO, mHeroEntity);
            session.commit(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.rollback(true);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return -1;
    }
}
