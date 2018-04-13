package com.game.stzb.Model;

import java.util.List;

public class GroupEntity {
        /**
         * groupDesc : {"attr":"攻击","members":"吕布(群)、吕布(汉)、貂蝉(汉)、高顺(群)、吕姬(群)、张辽(群)、陈宫(群)","name":"温侯无双"}
         * groupImgId2 : ["100003","100060"]
         * groupImgId3 : []
         * groupName : 温侯无双
         * groupImgId : ["100010","100005","100115","100055","100050","100109"]
         * groupId : 38
         */

        private GroupDescBean groupDesc;
        private String groupName;
        private int groupId;
        private List<String> groupImgId2;
        private List<?> groupImgId3;
        private List<String> groupImgId;

        public GroupDescBean getGroupDesc() {
            return groupDesc;
        }

        public void setGroupDesc(GroupDescBean groupDesc) {
            this.groupDesc = groupDesc;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public List<String> getGroupImgId2() {
            return groupImgId2;
        }

        public void setGroupImgId2(List<String> groupImgId2) {
            this.groupImgId2 = groupImgId2;
        }

        public List<?> getGroupImgId3() {
            return groupImgId3;
        }

        public void setGroupImgId3(List<?> groupImgId3) {
            this.groupImgId3 = groupImgId3;
        }

        public List<String> getGroupImgId() {
            return groupImgId;
        }

        public void setGroupImgId(List<String> groupImgId) {
            this.groupImgId = groupImgId;
        }

        public static class GroupDescBean {
            /**
             * attr : 攻击
             * members : 吕布(群)、吕布(汉)、貂蝉(汉)、高顺(群)、吕姬(群)、张辽(群)、陈宫(群)
             * name : 温侯无双
             */

            private String attr;
            private String members;
            private String name;

            public String getAttr() {
                return attr;
            }

            public void setAttr(String attr) {
                this.attr = attr;
            }

            public String getMembers() {
                return members;
            }

            public void setMembers(String members) {
                this.members = members;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
}
