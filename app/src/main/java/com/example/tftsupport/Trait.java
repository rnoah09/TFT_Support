package com.example.tftsupport;

import android.os.Parcel;
import android.os.Parcelable;

public class Trait implements Parcelable, Comparable<Champion> {
    private String name;
    private String key;
    private String description;
    private String type;
    private TraitSet sets;

    public Trait() {
    }

    protected Trait(Parcel in) {
        name = in.readString();
        key = in.readString();
        description = in.readString();
        type = in.readString();
    }

    public static final Creator<Trait> CREATOR = new Creator<Trait>() {
        @Override
        public Trait createFromParcel(Parcel in) {
            return new Trait(in);
        }

        @Override
        public Trait[] newArray(int size) {
            return new Trait[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TraitSet getSets() {
        return sets;
    }

    public void setSets(TraitSet sets) {
        this.sets = sets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(key);
        dest.writeString(description);
        dest.writeString(type);
    }

    @Override
    public int compareTo(Champion o) {
        return 0;
    }
}
