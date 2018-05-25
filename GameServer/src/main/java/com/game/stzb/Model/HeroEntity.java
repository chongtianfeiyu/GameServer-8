package com.game.stzb.Model;

public class HeroEntity {
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

    private String contory;
    private String name;
    private int distance;
    private double cost;
    private String type;
    private int quality;
    private int id;
    private String icon;

    public String getContory() {
        return contory;
    }

    public void setContory(String contory) {
        this.contory = contory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
