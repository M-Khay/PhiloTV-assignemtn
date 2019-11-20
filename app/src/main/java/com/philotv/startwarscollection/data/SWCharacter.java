package com.philotv.startwarscollection.data;
/**
 *
 */
public class SWCharacter {
    public final String id;
    public final String content;
    public final String details;

    public SWCharacter(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}