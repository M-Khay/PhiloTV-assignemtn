package com.philotv.startwarscollection.data;
/**
 *
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SWCharacter implements Parcelable {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("mass")
    @Expose
    public String mass;
    @SerializedName("birth_year")
    @Expose
    public String birthYear;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("homeworld")
    @Expose
    public String homeworld;

    public String getGender() {
        gender.replace("/","");
        return gender;
    }

    protected SWCharacter(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        gender = in.readString();
        birthYear = in.readString();
        homeworld = in.readString();

    }

    public static final Creator<SWCharacter> CREATOR = new Creator<SWCharacter>() {
        @Override
        public SWCharacter createFromParcel(Parcel in) {
            return new SWCharacter(in);
        }

        @Override
        public SWCharacter[] newArray(int size) {
            return new SWCharacter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(gender);
        dest.writeString(birthYear);
        dest.writeString(homeworld);
    }
}