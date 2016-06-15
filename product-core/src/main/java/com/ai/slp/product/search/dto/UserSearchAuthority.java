package com.ai.slp.product.search.dto;

/**
 * Created by xin on 16-5-25.
 */
public class UserSearchAuthority {
    private UserType usertype;
    private String userId;

    public UserSearchAuthority(UserType usertype, String userId) {
        this.usertype = usertype;
        this.userId = userId;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public String getUserId() {
        return userId;
    }

    public enum UserType {
        SUPPLY("13"),PERSONAL("10"), ENTERPRISE("11"), AGENCY("12");

        private String value;

        UserType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
