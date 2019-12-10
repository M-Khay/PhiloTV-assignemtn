package com.philotv.startwarscollection.data;

public enum SWGender {
    MALE("male"),
    FEMALE("female"),
    NA("n/a");

    private final String genderValue;


    SWGender(String genderValue) {
        this.genderValue = genderValue;
    }

    public SWGender getSWGender() {
        return this;
    }
}
