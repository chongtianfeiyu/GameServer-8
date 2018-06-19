package com.game.stzb;

import com.game.stzb.Model.*;
import com.sun.istack.internal.Nullable;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class GameSTZBDao {
    public static int addHero(HeroDetailEntity mHeroEntity) {
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

    public static List<HeroEntity> getHeroListWithUser(BaseRequest request, @Nullable UserInfo userInfo, @Nullable List<HeroEntity> heroEntities) {
        if (request.isDefaultGuest()) {
            return heroEntities;
        }
        if (heroEntities == null) {
            heroEntities = getHeroList();
        }
        if (userInfo == null) {
            userInfo = getUserInfo(request.getToken());
        }
        for (int i = 0; i < heroEntities.size(); i++) {
            HeroEntity entity = heroEntities.get(i);
            entity.setUserCount(Utils.getHeroCount(userInfo.getHerocount(), entity.getKeyid()));
        }
        return heroEntities;
    }

    public static List<HeroEntityWithAttr> getHeroListWithUserAndAttr(BaseRequest request, @Nullable UserInfo userInfo, @Nullable List<HeroEntityWithAttr> heroEntities) {
        if (request.isDefaultGuest()) {
            return new ArrayList<>();
        }
        if (heroEntities == null) {
            heroEntities = getHeroListWithAttr();
        }
        if (userInfo == null) {
            userInfo = getUserInfo(request.getToken());
        }
        for (int i = 0; i < heroEntities.size(); i++) {
            HeroEntityWithAttr entity = heroEntities.get(i);
            entity.setUserCount(Utils.getHeroCount(userInfo.getHerocount(), entity.getKeyid()));
        }
        return heroEntities;
    }

    public static void addActionLog(BaseRequest request) {
        if (request == null || request.isDefaultGuest()) {
            return;
        }
        ActionLog actionLog = new ActionLog().setAction(request.getAction());
        actionLog.setToken(request.getToken()).setData(request.getData()).setBak(request.toJson());
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            int result = mGameDao_stzb.addActionLog(DBManager.STZB_DATATABLE_LOG, actionLog);
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

    public static List<HeroEntityWithAttr> getHeroListWithAttr() {
        SqlSession session = null;
        List<HeroEntityWithAttr> mHeroEntities;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mHeroEntities = mGameDao_stzb.getHeroListWithAttr(DBManager.STZB_DATATABLE_HERO, 0, 1000);
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

    public static List<HeroDetailEntity> getHeroDetailList() {
        SqlSession session = null;
        List<HeroDetailEntity> mHeroEntities;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mHeroEntities = mGameDao_stzb.getHeroDetailList(DBManager.STZB_DATATABLE_HERO, 0, 1000);
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

    public static HeroDetailEntity getHeroInfo(int id) {
        SqlSession session = null;
        HeroDetailEntity heroBean;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            heroBean = mGameDao_stzb.getHeroByID(DBManager.STZB_DATATABLE_HERO, id);
            session.commit(true);
            return heroBean;
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

    public static UserInfo getUserInfo(int id) {
        SqlSession session = null;
        UserInfo mUserInfo;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mUserInfo = mGameDao_stzb.getUserByID(DBManager.STZB_DATATABLE_USER, id);
            if (mUserInfo != null) {
                mUserInfo.setPwd(null);
                mUserInfo.setLogininfo(null);
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
                mUserInfo.setLogininfo(null);
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

    public static int updateHeroCountColumn(int id, byte[] herocount) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            int result = mGameDao_stzb.updateHeroCountColumn(DBManager.STZB_DATATABLE_USER, id, herocount);
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

    public static long updateUserMoney(String userToken, long moneyChange) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            mGameDao_stzb.updateUserGameMoney(DBManager.STZB_DATATABLE_USER, userToken, moneyChange);
            Long money = mGameDao_stzb.getUserGameMoney(DBManager.STZB_DATATABLE_USER, userToken);
            session.commit(true);
            if (money == null) {
                return -1;
            }
            return money;
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

    public static long getUserMoney(String userToken) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            long money = mGameDao_stzb.getUserGameMoney(DBManager.STZB_DATATABLE_USER, userToken);
            session.commit(true);
            return money;
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

    public static UserInfo registerOrLoginUser(UserInfo mUserInfo) {
        SqlSession session = null;
        try {
            session = DBManager.getSqlSessionFactory(GameDao_STZB.class).openSession();
            GameDao_STZB mGameDao_stzb = session.getMapper(GameDao_STZB.class);
            UserInfo mUserInfo1 = mGameDao_stzb.getUserByUUID(DBManager.STZB_DATATABLE_USER, mUserInfo.getUuid());
            if (mUserInfo1 == null) {
                int result = mGameDao_stzb.addUser(DBManager.STZB_DATATABLE_USER, mUserInfo);
                int userid = mGameDao_stzb.getLastID();
                mGameDao_stzb.updateHeroCountColumn(DBManager.STZB_DATATABLE_USER, userid, Utils.getDefaultHeroCount());
                mUserInfo1 = mGameDao_stzb.getUserByID(DBManager.STZB_DATATABLE_USER, userid);
            } else {
                updateUserLoginTime(mUserInfo1.getId());
            }
            if (mUserInfo1 != null) {
                mUserInfo1.setPwd(null);
                mUserInfo1.setLogininfo(null);
            }
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
