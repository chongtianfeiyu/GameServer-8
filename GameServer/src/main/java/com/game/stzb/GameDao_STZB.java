package com.game.stzb;

import com.game.stzb.Model.*;
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
    @Insert("insert into ${tablename} \n" +
            "( `id`, `name`, `contory`, `quality`, `cost`, \n" +
            "`type`, `distance`, `attack`, `attGrow`, `def`,\n" +
            "`defGrow`, `ruse`, `ruseGrow`, `siege`, `siegeGrow`,\n" +
            "`speed`, `speedGrow`, `sex`, `icon`,\n" +
            "`desc`, `groudArr`, `groupName`, `methodId`, `methodDesc`,\n" +
            "`methodName`, `methodId1`, `methodDesc1`, `methodName1`, `methodId2`,\n" +
            "`methodDesc2`, `methodName2`, `uniqueName`, `groups`) \n" +
            "values (\n" +
            "#{bean.id},#{bean.name},#{bean.contory},#{bean.quality},#{bean.cost},\n" +
            "#{bean.type},#{bean.distance},#{bean.attack},#{bean.attGrow},#{bean.def},\n" +
            "#{bean.defGrow},#{bean.ruse},#{bean.ruseGrow},#{bean.siege},#{bean.siegeGrow},\n" +
            "#{bean.speed},#{bean.speedGrow},#{bean.sex},#{bean.icon},\n" +
            "#{bean.desc},#{bean.groudArr},#{bean.groupName},#{bean.methodId},#{bean.methodDesc},\n" +
            "#{bean.methodName},#{bean.methodId1},#{bean.methodDesc1},#{bean.methodName1},#{bean.methodId2},\n" +
            "#{bean.methodDesc2},#{bean.methodName2},#{bean.uniqueName},#{bean.groups});")
    @Results(@Result(column = "id"))
    public int addHero(@Param("tablename") String tablename, @Param("bean") HeroDetailEntity mEntity) throws Exception;

    /**
     * 查询记录byID
     *
     * @param tablename
     * @param mId
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} where id=#{id}")
    public HeroDetailEntity getHeroByID(@Param("tablename") String tablename, @Param("id") int mId) throws Exception;

    /**
     * 获取武将列表，简单数据
     *
     * @param tablename
     * @param mIndex
     * @param mLimit
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} limit #{index},#{limit}")
    public List<HeroEntity> getHeroList(@Param("tablename") String tablename, @Param("index") int mIndex, @Param("limit") int mLimit) throws Exception;

    /**
     * 获取武将列表，简单数据包含属性
     *
     * @param tablename
     * @param mIndex
     * @param mLimit
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} limit #{index},#{limit}")
    public List<HeroEntityWithAttr> getHeroListWithAttr(@Param("tablename") String tablename, @Param("index") int mIndex, @Param("limit") int mLimit) throws Exception;

    /**
     * 获取武将列表，详细
     *
     * @param tablename
     * @param mIndex
     * @param mLimit
     * @return
     * @throws Exception
     */
    @Select("select * from ${tablename} limit #{index},#{limit}")
    public List<HeroDetailEntity> getHeroDetailList(@Param("tablename") String tablename, @Param("index") int mIndex, @Param("limit") int mLimit) throws Exception;


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
    @Insert(" insert into  ${tablename} ( `uuid`, `name`, `pwd`, `head`, `nickname`, `phone`, `logintype`, `logininfo`) values" +
            " (#{bean.uuid},#{bean.name},#{bean.pwd},#{bean.head},#{bean.nickname},#{bean.phone},#{bean.logintype},#{bean.logininfo})")
    public int addUser(@Param("tablename") String tablename, @Param("bean") UserInfo mEntity) throws Exception;

    /**
     * 刷新时间
     *
     * @param tablename
     * @param id
     */
    @Update("update ${tablename} set lastlogin = now() where id =#{id}")
    public void updateLastLoginTime(@Param("tablename") String tablename, @Param("id") int id);

    /**
     * 更新武将完整数据字段
     *
     * @param tablename
     * @param id
     */
    @Update("update ${tablename} set herocount = #{herocount} where id =#{id}")
    public int updateHeroCountColumn(@Param("tablename") String tablename, @Param("id") int id, @Param("herocount") byte[] herocount);

    /**
     * 修改金额，增加减少多少
     *
     * @param tablename
     * @param userToken
     */
    @Update("update ${tablename} set game_money=game_money + #{money} where uuid =#{userToken}")
    public int updateUserGameMoney(@Param("tablename") String tablename, @Param("userToken") String userToken, @Param("money") long money);

    /**
     * 获取金钱数
     *
     * @param tablename
     * @param userToken
     */
    @Select("select game_money from ${tablename} where uuid=#{userToken}")
    public Long getUserGameMoney(@Param("tablename") String tablename, @Param("userToken") String userToken);
    /**
     * 插入一条操作记录
     *
     * @param tablename
     * @param mEntity
     * @return
     * @throws Exception
     */
    @Insert(" insert into  ${tablename} ( `userId`, `userName`, `data`, `token`, `action`, `bak`) values" +
            " (#{bean.userId},#{bean.userName},#{bean.data},#{bean.token},#{bean.action},#{bean.bak})")
    public int addActionLog(@Param("tablename") String tablename, @Param("bean") ActionLog mEntity) throws Exception;

}
