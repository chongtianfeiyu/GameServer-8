package com.game.stzb;

import com.game.stzb.Model.HeroEntity;
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
    @Results(@Result(column = "id", property = "pushID"))
    public HeroEntity getPushMsgByID(@Param("tablename") String tablename, @Param("id") int mId) throws Exception;
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
    @Results(@Result(column = "id", property = "pushID"))
    public List<HeroEntity> getPushMsgs(@Param("tablename") String tablename, @Param("index") int mIndex, @Param("limit") int mLimit) throws Exception;


    //    @Select("select @@identity")
    @Select("select LAST_INSERT_ID()")
    public int getLastID() throws Exception;
}
