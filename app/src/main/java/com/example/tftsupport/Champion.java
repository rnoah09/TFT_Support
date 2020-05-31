package com.example.tftsupport;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Champion implements Parcelable, Comparable<Champion> {
    private String name;
    private String championID;
    private int cost;
    private List<String> traits;

    public Champion() {

    }

    protected Champion(Parcel in) {
        name = in.readString();
        championID = in.readString();
        cost = in.readInt();
        traits = in.createStringArrayList();
    }

    public static final Creator<Champion> CREATOR = new Creator<Champion>() {
        @Override
        public Champion createFromParcel(Parcel in) {
            return new Champion(in);
        }

        @Override
        public Champion[] newArray(int size) {
            return new Champion[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChampionID() {
        return championID;
    }

    public void setChampionID(String championID) {
        this.championID = championID;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(championID);
        dest.writeInt(cost);
        dest.writeStringList(traits);
    }

    @Override
    public int compareTo(Champion o) {
        return 0;
    }
}
