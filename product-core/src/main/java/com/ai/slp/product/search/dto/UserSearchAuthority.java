package com.ai.slp.product.search.dto;

/**
 * Created by xin on 16-5-25.
 */
public class UserSearchAuthority {
    private UserType usertype;
    private String userCode;

    public UserSearchAuthority(UserType usertype, String userCode) {
        this.usertype = usertype;
        this.userCode = userCode;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public String getUserCode() {
        return userCode;
    }

    public enum UserType {
        PERSONAL("10"), ENTERPRISE("11"), AGENCY("12");

        private String value;

        UserType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
