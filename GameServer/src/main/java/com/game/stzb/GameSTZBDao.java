package com.game.stzb;

import com.game.stzb.Model.HeroEntity;
import com.game.stzb.Model.UserInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class GameSTZBDao {
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

    public static List<HeroEntity> getHeroList() {
        SqlSession session = null;
        List<HeroEntity> mHeroEntities;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mHeroEntities = mGameDao_stzb.getHeroList(DBManager.STZB_DATATABLE_HERO, 0, 1000);
            session.commit(true);
            return mHeroEntities;
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
        return new ArrayList<>();
    }

    public static UserInfo getUserInfo(int id) {
        SqlSession session = null;
        UserInfo mUserInfo;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mUserInfo = mGameDao_stzb.getUserByID(DBManager.STZB_DATATABLE_USER, id);
            if (mUserInfo != null) {
                mUserInfo.setPwd(null);
            }
            session.commit(true);
            return mUserInfo;
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
        return null;
    }

    public static UserInfo getUserInfo(String uuid) {
        SqlSession session = null;
        UserInfo mUserInfo;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mUserInfo = mGameDao_stzb.getUserByUUID(DBManager.STZB_DATATABLE_USER, uuid);
            if (mUserInfo != null) {
                mUserInfo.setPwd(null);
            }
            session.commit(true);
            return mUserInfo;
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
        return null;
    }

    public static void updateUserLoginTime(int id) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mGameDao_stzb.updateLastLoginTime(DBManager.STZB_DATATABLE_USER, id);
            session.commit(true);
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
    }

    public static UserInfo registerOrLoginUser(UserInfo mUserInfo) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            UserInfo mUserInfo1 = mGameDao_stzb.getUserByUUID(DBManager.STZB_DATATABLE_USER, mUserInfo.getUuid());
            if (mUserInfo1 == null) {
                int result = mGameDao_stzb.addUser(DBManager.STZB_DATATABLE_USER, mUserInfo);
                int userid = mGameDao_stzb.getLastID();
                mUserInfo1 = mGameDao_stzb.getUserByID(DBManager.STZB_DATATABLE_USER, userid);
            }
            mUserInfo1.setPwd(null);
            session.commit(true);
            return mUserInfo1;
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
        return null;
    }

}