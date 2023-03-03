package com.generic.khatabook.entity;

public enum AppEntity {

    KHATABOOK("Khatabook"), CUSTOMER("Customer");
    private String myName;

    AppEntity(final String name) {
        myName = name;
    }

    @Override
    public String toString() {
        return "Entity Name='" + myName + '\'';
    }
}
