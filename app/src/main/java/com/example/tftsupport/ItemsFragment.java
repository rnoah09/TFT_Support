package com.example.tftsupport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Item> itemsList;
    private List<ItemParentModel> mainComponentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_items, container, false);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.items);
        String sxml = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        Item[] champions =  gson.fromJson(sxml, Item[].class);
        itemsList = Arrays.asList(champions);
        mainComponentList = new ArrayList<>();
        generateComponentList(itemsList);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_item_parent);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ItemRecyclerAdapter(mainComponentList);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void generateComponentList(List<Item> list){
        ItemParentModel bf = new ItemParentModel("B.F. Sword", getResources().getIdentifier("bf", "drawable", getContext().getPackageName()));
        ItemParentModel bow = new ItemParentModel("Recurve Bow", getResources().getIdentifier("bow", "drawable", getContext().getPackageName()));
        ItemParentModel rod = new ItemParentModel("Needlessly Large Rod", getResources().getIdentifier("rod", "drawable", getContext().getPackageName()));
        ItemParentModel tear = new ItemParentModel("Tear of the Goddess", getResources().getIdentifier("tear", "drawable", getContext().getPackageName()));
        ItemParentModel armor = new ItemParentModel("Chain Vest", getResources().getIdentifier("armor", "drawable", getContext().getPackageName()));
        ItemParentModel cloak = new ItemParentModel("Negatron Cloak", getResources().getIdentifier("cloak", "drawable", getContext().getPackageName()));
        ItemParentModel belt = new ItemParentModel("Giant's Belt", getResources().getIdentifier("belt", "drawable", getContext().getPackageName()));
        ItemParentModel spatula = new ItemParentModel("Spatula", getResources().getIdentifier("spatula", "drawable", getContext().getPackageName()));
        ItemParentModel gloves = new ItemParentModel("Sparring Gloves", getResources().getIdentifier("gloves", "drawable", getContext().getPackageName()));


        mainComponentList.add(bf);
        mainComponentList.add(bow);
        mainComponentList.add(rod);
        mainComponentList.add(tear);
        mainComponentList.add(armor);
        mainComponentList.add(cloak);
        mainComponentList.add(belt);
        mainComponentList.add(spatula);
        mainComponentList.add(gloves);

        for(int i = mainComponentList.size() - 1; i >= 0; i--){
            for(Item item : list){
                    if(item.getComponents().get(0).equals(mainComponentList.get(i).getName())){
                        int resourceImage = getResources().getIdentifier(item.getId(), "drawable", getContext().getPackageName());
                        ItemChildModel c = new ItemChildModel();
                        c.setItem(resourceImage);
                        c.setComponent1(mainComponentList.get(i).getImage());
                        c.setName(item.getName());

                        for(ItemParentModel component: mainComponentList){
                            if (component.getName().equals(item.getComponents().get(1))){
                                c.setComponent2(component.getImage());
                            }
                        }

                        mainComponentList.get(i).getListItem().add(c);
                    }
                    else if(item.getComponents().get(1).equals(mainComponentList.get(i).getName())){
                        int resourceImage = getResources().getIdentifier(item.getId(), "drawable", getContext().getPackageName());
                        ItemChildModel c = new ItemChildModel();
                        c.setItem(resourceImage);
                        c.setComponent1(mainComponentList.get(i).getImage());
                        c.setName(item.getName());

                        for(ItemParentModel component: mainComponentList){
                            if (component.getName().equals(item.getComponents().get(0))){
                                c.setComponent2(component.getImage());
                            }
                        }

                        mainComponentList.get(i).getListItem().add(c);
                    }
            }
        }
        Log.e("bf", "generateComponentList: " + bf.getListItem() );
        Log.e("bow", "generateComponentList: " + bow.getListItem() );

    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

}
