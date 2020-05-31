package com.example.tftsupport;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ParentModel {
    private List<ChildModel> children;
    private String name;

    public ParentModel(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public List<ChildModel> getListChampion() {
        return children;
    }

    public void setListChampion(List<ChildModel> listChampion) {
        this.children = listChampion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}