package com.game.stzb.Model;

import java.util.List;

public class HeroEntityWithAttr {
    /**
     * attGrow : 2.21
     * attack : 91
     * contory : 魏
     * cost : 3.5
     * def : 94
     * defGrow : 2.27
     * desc : 【志】汉末三国时期曹魏名将。曾从属丁原、董卓、吕布。吕布下邳败亡后，归顺曹操。此后随曹操四处征讨，战功累累。张辽为历代推崇，并成为古今六十四名将之一。曹丕登基后，仍令张辽守御孙权。黄初二年（221年），张辽屯驻雍丘，染病。张辽大病期间，孙权依旧对其非常忌惮。黄初三年（222年），张辽不负众望，抱病击破吴将吕范。同年在江都病逝，谥刚侯，享年五十四岁。
     【演】在《三国演义》中，他先为吕布部将，多番令曹军陷于苦战，其武勇连关羽亦甚称道。后来吕布战败遭擒，曹操下令绞杀吕布后，刘备、关羽为张辽求情，曹操便待张辽以上宾之礼。张辽感其恩情，于是投降，自此成为曹操将领。后来，张辽随曹丕以大船征伐江东，军队被徐盛所击败。曹丕登岸逃亡时，吴将丁奉从岸边杀至，张辽为保护曹丕上前迎敌，却被丁奉以箭射其腰，回营后不治身亡，曹丕厚葬之。
     * distance : 3
     * groudArr : 12,
     * groupName : null
     * groups : [{"attr":"攻击","members":"张辽(魏)、张郃(魏)、徐晃(魏)、于禁(魏)、乐进(魏)","name":"五子良将"}]
     * icon : https://stzb.res.netease.com/pc/qt/20170323200251/data/small/card_100027.jpg
     * id : 100027
     * methodDesc : 战斗开始后前3回合，使我军群体速度属性提高20.5（受谋略属性影响），并使其每回合有35%的机率可以进行两次普通攻击
     * methodDesc1 : 战斗开始后前3回合，使我军群体受到攻击和策略攻击时的伤害降低15%（受谋略属性影响）
     * methodDesc2 : null
     * methodDetail : {"desc":"战斗开始后前3回合，使我军群体速度属性提高20.5（受谋略属性影响），并使其每回合有35%的机率可以进行两次普通攻击","dismantling":"","distance":3,"icon":"https://stzb.res.netease.com/pc/qt/20170323200251/data/jineng/tactics_01.png","id":200027,"isAppear":"是","name":"其疾如风","notice":"无","probability":"100%","skillCount":1,"soldierType":"骑","studyDesc":"无","studyDesc2":"无","studyStar":"","targetShow":"我军群体(有效距离内2-3个目标)","targetType":"我军群体","type":"指挥"}
     * methodDetail1 : {"desc":"战斗开始后前3回合，使我军群体受到攻击和策略攻击时的伤害降低15%（受谋略属性影响）","dismantling":"【汉·灵帝·弓】【魏·张辽·骑】","distance":2,"icon":"https://stzb.res.netease.com/pc/qt/20170323200251/data/jineng/tactics_01.png","id":200194,"isAppear":"是","name":"避其锋芒","notice":"技能研究成功后，可配置1个武将","probability":"100%","skillCount":1,"soldierType":"弓步骑","studyDesc":"该技能可通过拆解武将【汉·灵帝·弓】【魏·张辽·骑】获得\r\n\r\n4星群武将可促使技能研究进度+5%\r\n【汉·灵帝·弓】【魏·张辽·骑】可促使技能研究进度+30%","studyDesc2":"该技能可通过拆解武将获得\r\n\r\n4星群武将可促使技能研究进度+5%\r\n可促使技能研究进度+30%","studyStar":5,"targetShow":"我军群体(有效距离内2个目标)","targetType":"我军群体","type":"指挥"}
     * methodDetail2 : null
     * methodId : 200027
     * methodId1 : 200194
     * methodId2 :
     * methodName : 其疾如风
     * methodName1 : 避其锋芒
     * methodName2 :
     * name : 张辽
     * quality : 5
     * ruse : 83
     * ruseGrow : 1.05
     * sex : 男
     * siege : 3
     * siegeGrow : 0.29
     * speed : 105
     * speedGrow : 2.72
     * type : 骑
     * uniqueName : 张辽-魏-骑
     */
    /**
     * 攻击成长
     */
    private double attGrow;
    /**
     * 攻击初始值
     */
    private int attack;
    /**
     * 国家
     */
    private String contory;
    /**
     * 代价，占位大小
     */
    private double cost;
    /**
     * 防御初始值
     */
    private int def;
    /**
     * 防御增长值
     */
    private double defGrow;
    /**
     * 攻击距离
     */
    private int distance;

    /**
     * 小头像
     */
    private String icon;
    /**
     * 武将id
     */
    private int id;

    /**
     * 天赋技能id
     */
    private int methodId;
    /**
     * 拆解技能id
     */
    private int methodId1;
    private int methodId2;

    /**
     * 武将名
     */
    private String name;
    /**
     * 稀有度
     */
    private int quality;
    /**
     * 谋略初始值
     */
    private int ruse;
    /**
     * 谋略成长值
     */
    private double ruseGrow;
    /**
     * 性别
     */
    private String sex;
    /**
     * 攻城初始值
     */
    private int siege;
    /**
     * 攻城成长值
     */
    private double siegeGrow;
    /**
     * 速度初始值
     */
    private int speed;
    /**
     * 速度成长值
     */
    private double speedGrow;
    /**
     * 兵种
     */
    private String type;
    /**
     * 唯一标示名称
     */
    private String uniqueName;

    /**
     * 数据库顺序
     */
    private Integer keyid;
    private Integer userCount;

    public Integer getUserCount() {
        return userCount;
    }

    public HeroEntityWithAttr setUserCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public HeroEntityWithAttr setKeyid(Integer keyid) {
        this.keyid = keyid;
        return this;
    }

    public double getAttGrow() {
        return attGrow;
    }

    public void setAttGrow(double attGrow) {
        this.attGrow = attGrow;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getContory() {
        return contory;
    }

    public void setContory(String contory) {
        this.contory = contory;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public double getDefGrow() {
        return defGrow;
    }

    public void setDefGrow(double defGrow) {
        this.defGrow = defGrow;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public int getMethodId1() {
        return methodId1;
    }

    public void setMethodId1(int methodId1) {
        this.methodId1 = methodId1;
    }

    public int getMethodId2() {
        return methodId2;
    }

    public void setMethodId2(int methodId2) {
        this.methodId2 = methodId2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getRuse() {
        return ruse;
    }

    public void setRuse(int ruse) {
        this.ruse = ruse;
    }

    public double getRuseGrow() {
        return ruseGrow;
    }

    public void setRuseGrow(double ruseGrow) {
        this.ruseGrow = ruseGrow;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public double getSiegeGrow() {
        return siegeGrow;
    }

    public void setSiegeGrow(double siegeGrow) {
        this.siegeGrow = siegeGrow;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getSpeedGrow() {
        return speedGrow;
    }

    public void setSpeedGrow(double speedGrow) {
        this.speedGrow = speedGrow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }
}
