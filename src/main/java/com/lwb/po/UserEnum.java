package com.lwb.po;

/**
 * Created by lwb on 2015/9/19.
 */
public class UserEnum {
    public static enum USER_STATUS{
        NOT_START("未开始"),
        ONGOING("进行中"),
        END("已结束"),
        OTHER("其他");
        private String cnName;

        private USER_STATUS(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (USER_STATUS item : USER_STATUS.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }
}
