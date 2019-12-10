package com.philotv.startwarscollection.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class SWCharacterList {


    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("next")
    @Expose
    public Object next;
    @SerializedName("previous")
    @Expose
    public Object previous;
    @SerializedName("results")
    @Expose
    public List<SWCharacter> results = null;

}

