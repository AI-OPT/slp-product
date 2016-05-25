package com.ai.slp.product.search.dto;

/**
 * Created by xin on 16-5-25.
 */
public enum SortType {
    DESC("desc"), ASC("desc");

    private String value;


    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
