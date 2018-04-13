package com.game.stzb.Model;

public class SkillEntity {
        /**
         * distance : 6
         * notice : 无
         * isAppear : 是
         * name : 遗志
         * probability : 100%
         * targetType : 我军单体
         * targetShow : 我军单体
         * soldierType : 步
         * skillCount : 1
         * studyDesc2 : 无
         * studyDesc : 无
         * dismantling :
         * studyStar :
         * desc : 战斗开始后第3回合，使我军前锋步兵或弓兵恢复极大量兵力，此后，受到普通攻击可以进行反击（伤害率42.5%），同时无法恢复兵力，持续直到战斗结束
         * type : 指挥
         * id : 200033
         * icon : http://res.stzb.netease.com/gw/15v1/data/jineng/tactics_01.png
         */

        private int distance;
        private String notice;
        private String isAppear;
        private String name;
        private String probability;
        private String targetType;
        private String targetShow;
        private String soldierType;
        private int skillCount;
        private String studyDesc2;
        private String studyDesc;
        private String dismantling;
        private String studyStar;
        private String desc;
        private String type;
        private int id;
        private String icon;

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getIsAppear() {
            return isAppear;
        }

        public void setIsAppear(String isAppear) {
            this.isAppear = isAppear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProbability() {
            return probability;
        }

        public void setProbability(String probability) {
            this.probability = probability;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getTargetShow() {
            return targetShow;
        }

        public void setTargetShow(String targetShow) {
            this.targetShow = targetShow;
        }

        public String getSoldierType() {
            return soldierType;
        }

        public void setSoldierType(String soldierType) {
            this.soldierType = soldierType;
        }

        public int getSkillCount() {
            return skillCount;
        }

        public void setSkillCount(int skillCount) {
            this.skillCount = skillCount;
        }

        public String getStudyDesc2() {
            return studyDesc2;
        }

        public void setStudyDesc2(String studyDesc2) {
            this.studyDesc2 = studyDesc2;
        }

        public String getStudyDesc() {
            return studyDesc;
        }

        public void setStudyDesc(String studyDesc) {
            this.studyDesc = studyDesc;
        }

        public String getDismantling() {
            return dismantling;
        }

        public void setDismantling(String dismantling) {
            this.dismantling = dismantling;
        }

        public String getStudyStar() {
            return studyStar;
        }

        public void setStudyStar(String studyStar) {
            this.studyStar = studyStar;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
}
