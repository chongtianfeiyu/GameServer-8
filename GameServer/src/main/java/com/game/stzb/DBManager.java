package com.game.stzb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class DBManager {
    public static final String STZB_DATABASENAME = "Game";
    public static final String UPDETEVERSION_DATABASENAME = "server";
    public static final String STZB_DATATABLE_HERO = "stzb_hero2";
    public static final String STZB_DATATABLE_SKILL = "stzb_skill";
    public static final String STZB_DATATABLE_USER = "stzb_user";
    private static SqlSessionFactory mSqlSessionFactory;
    private static SqlSessionFactoryBuilder mSqlSessionFactoryBuilder;
    private static InputStream configFileInputStream;

    public static SqlSessionFactory getSqlSessionFactory(Class mClass) {
        if (mSqlSessionFactoryBuilder == null) {
            mSqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        }
        try {
            configFileInputStream = Resources.getResourceAsStream("config/mybatis3-config.xml");
        } catch (IOException mE) {
            Logger.getLogger("BaseConfig").severe("mybatis初始化错误：加载配置文件失败" + mE.getLocalizedMessage());
            mE.printStackTrace();
            return null;
        }
        mSqlSessionFactory = mSqlSessionFactoryBuilder.build(configFileInputStream, STZB_DATABASENAME);
        if (!mSqlSessionFactory.getConfiguration().hasMapper(mClass)) {
            mSqlSessionFactory.getConfiguration().addMapper(mClass);
        }
        return mSqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactory4Version(Class mClass) {
        if (mSqlSessionFactoryBuilder == null) {
            mSqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        }
        try {
            configFileInputStream = Resources.getResourceAsStream("config/mybatis3-config.xml");
        } catch (IOException mE) {
            Logger.getLogger("BaseConfig").severe("mybatis初始化错误：加载配置文件失败" + mE.getLocalizedMessage());
            mE.printStackTrace();
            return null;
        }
        mSqlSessionFactory = mSqlSessionFactoryBuilder.build(configFileInputStream, UPDETEVERSION_DATABASENAME);
        if (!mSqlSessionFactory.getConfiguration().hasMapper(mClass)) {
            mSqlSessionFactory.getConfiguration().addMapper(mClass);
        }
        return mSqlSessionFactory;
    }
}
