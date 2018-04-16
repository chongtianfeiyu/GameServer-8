package com.game.stzb;

import com.game.stzb.Model.HeroEntity;
import com.game.stzb.Model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by hnvfh on 2017/5/25.
 */
public interface GameDao_STZB {


    /**
     * 插入一条记录
     *
     * @param tablename
     * @param mEntity
     * @return
     * @throws Exception
     */
    @Insert(" insert into  ${tablename} (`id`, `name`, `contory`, `siege`, `speed`, `skillId`, `cost`, `type`, `quality`, `icon`, `url`) values" +
            " (#{bean.id},#{bean.name},#{bean.contory},#{bean.siege},#{bean.speed},#{bean.skillId},#{bean.cost},#{bean.type},#{bean.quality},#{bean.icon},#{bean.url})")
    @Results(@Result(column = "id", property = "pushID"))
    public int addHero(@Param("tablename") String tablename, @Param("bean") HeroEntity mEntity) throws Exception;
    /**
     * 查询记录byID
     *
     * @param tablename
     * @param mId
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} where id=#{id}")
    public HeroEntity getHeroByID(@Param("tablename") String tablename, @Param("id") int mId) throws Exception;
    /**
     * 查询数据
     *
     * @param tablename
     * @param mIndex
     * @param mLimit
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} limit #{index},#{limit}")
    public List<HeroEntity> getHeroList(@Param("tablename") String tablename, @Param("index") int mIndex, @Param("limit") int mLimit) throws Exception;


    //    @Select("select @@identity")
    @Select("select LAST_INSERT_ID()")
    public int getLastID() throws Exception;
    /**
     * 查询用户byUUID
     *
     * @param tablename
     * @param mId
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} where id=#{id}")
    public UserInfo getUserByID(@Param("tablename") String tablename, @Param("id") int mId) throws Exception;
    /**
     * 查询用户byUUID
     *
     * @param tablename
     * @param uuid
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} where uuid=#{uuid}")
    public UserInfo getUserByUUID(@Param("tablename") String tablename, @Param("uuid") String uuid) throws Exception;

    /**
     * 插入一条记录
     *
     * @param tablename
     * @param mEntity
     * @return
     * @throws Exception
     */
    @Insert(" insert into  ${tablename} ( `uuid`, `name`, `pwd`, `head`, `nickname`, `phone`) values" +
            " (#{bean.uuid},#{bean.name},#{bean.pwd},#{bean.head},#{bean.nickname},#{bean.phone})")
    public int addUser(@Param("tablename") String tablename, @Param("bean") UserInfo mEntity) throws Exception;

    /**
     * 刷新时间
     * @param tablename
     * @param id
     */
    @Update("update ${tablename} set lastlogin = now() where id =#{id}")
    public void updateLastLoginTime(@Param("tablename") String tablename, @Param("id") int id);

}
