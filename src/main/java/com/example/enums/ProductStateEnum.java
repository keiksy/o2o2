package com.example.enums;

public enum ProductStateEnum {
    OFFLINE(-1, "ILLEGAL PRODUCT"),DOWN(0, "UNAVAILABLE"), INNER_ERROR(-1001, "OPERATION ERROR"),
    EMPTY(-1002, "NULL PRODUCT"), SUCCESS(1, "SUCCESS");

    private int state;
    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductStateEnum stateOf(int index) {
        for (ProductStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
