package com.game.stzb.Model;

public class HeroEntity implements Comparable<HeroEntity>{
    /**
     * contory : 群
     * name : 吕布
     * siege : 9
     * distance : 77
     * methodDetail : {"id":200012,"name":"辕门射戟","icon":"http://res.stzb.netease.com/gw/15v1/data/jineng/tactics_02.png"}
     * cost : 3.5
     * type : 弓
     * quality : 5
     * id : 100479
     * icon : http://res.stzb.netease.com/gw/15v1/data/small/card_100479.jpg
     * src : https://mgame-f.netease.com/forum/201509/30/172410ttnvuwqeu8ae4v4h.jpg
     * url : /thread-967815-1-1.html
     */
    private Long timeTreitel;
    private String contory;
    private String name;
    private Integer distance;
    private Double cost;
    private String type;
    private Integer quality;
    private Integer id;
    private String icon;
    private Integer normal;
    public String getContory() {
        return contory;
    }

    public Integer getNormal() {
        return normal;
    }

    public boolean checkTimeOut(){
        Long l=System.currentTimeMillis()-timeTreitel;
        System.out.println(l);
        return l>15000;
    }
    public HeroEntity initTimeTreitel( ) {
        this.timeTreitel = System.currentTimeMillis();
        return this;
    }

    public HeroEntity setNormal(Integer normal) {
        this.normal = normal;
        return this;
    }

    public HeroEntity setContory(String contory) {
        this.contory = contory;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public HeroEntity setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public HeroEntity setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    public String getType() {
        return type;
    }

    public HeroEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getQuality() {
        return quality;
    }

    public HeroEntity setQuality(Integer quality) {
        this.quality = quality;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public HeroEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public HeroEntity setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public HeroEntity copySimple() {
        return new HeroEntity().setName(name).setContory(contory);
    }

    @Override
    public String toString() {
        return "HeroEntity{" +
                "contory='" + contory + '\'' +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", quality=" + quality +
                ", id=" + id +
                ", icon='" + icon + '\'' +
                '}';
    }

    @Override
    public int compareTo(HeroEntity o) {
        return getName().compareTo(o.name);
    }
}
