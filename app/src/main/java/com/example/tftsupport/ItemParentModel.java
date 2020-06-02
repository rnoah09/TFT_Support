package com.example.tftsupport;

import java.util.ArrayList;
import java.util.List;

public class ItemParentModel {
    private List<ItemChildModel> children;
    private String name;
    private int image;

    public ItemParentModel(String name, int image) {
        this.name = name;
        this.image = image;
        children = new ArrayList<>();
    }

    public List<ItemChildModel> getListItem() {
        return children;
    }

    public void setListItem(List<ItemChildModel> listItem) {
        this.children = listItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
