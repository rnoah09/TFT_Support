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

public class ChampionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Champion> championsList;
    private List<ParentModel> traitsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_champions, container, false);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.champions);
        String sxml = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        Champion[] champions =  gson.fromJson(sxml, Champion[].class);
        championsList = Arrays.asList(champions);
        traitsList = new ArrayList<>();
        generateTraitLists(championsList);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_parent);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter(traitsList);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void generateTraitLists(List<Champion> list){
        ParentModel blademaster = new ParentModel("Blademaster");
        ParentModel blaster = new ParentModel("Blaster");
        ParentModel brawler = new ParentModel("Brawler");
        ParentModel celestial = new ParentModel("Celestial");
        ParentModel chrono = new ParentModel("Chrono");
        ParentModel cybernetic = new ParentModel("Cybernetic");
        ParentModel darkStar = new ParentModel("Dark Star");
        ParentModel demolitionist = new ParentModel("Demolitionist");
        ParentModel infiltrator = new ParentModel("Infiltrator");
        ParentModel manaReaver = new ParentModel("Mana-Reaver");
        ParentModel mechPilot = new ParentModel("Mech-Pilot");
        ParentModel mercenary = new ParentModel("Mercenary");
        ParentModel mystic = new ParentModel("Mystic");
        ParentModel protector = new ParentModel("Protector");
        ParentModel rebel = new ParentModel("Rebel");
        ParentModel sniper = new ParentModel("Sniper");
        ParentModel sorcerer = new ParentModel("Sorcerer");
        ParentModel spacePirate = new ParentModel("Space Pirate");
        ParentModel starGuardian = new ParentModel("Star Guardian");
        ParentModel starShip = new ParentModel("Starship");
        ParentModel valkyrie = new ParentModel("Valkyrie");
        ParentModel vanguard = new ParentModel("Vanguard");
        ParentModel tftvoid = new ParentModel("Void");

        traitsList.add(blademaster);
        traitsList.add(blaster);
        traitsList.add(brawler);
        traitsList.add(celestial);
        traitsList.add(chrono);
        traitsList.add(cybernetic);
        traitsList.add(darkStar);
        traitsList.add(demolitionist);
        traitsList.add(infiltrator);
        traitsList.add(manaReaver);
        traitsList.add(mechPilot);
        traitsList.add(mercenary);
        traitsList.add(mystic);
        traitsList.add(protector);
        traitsList.add(rebel);
        traitsList.add(sniper);
        traitsList.add(sorcerer);
        traitsList.add(spacePirate);
        traitsList.add(starGuardian);
        traitsList.add(starShip);
        traitsList.add(tftvoid);
        traitsList.add(valkyrie);
        traitsList.add(vanguard);

        for(int i = traitsList.size() - 1; i >= 0; i--){
           for(Champion champion : list){
               for(int j = champion.getTraits().size(); j >= 1; j--){
                   if(champion.getTraits().get(j -1).contains(traitsList.get(i).getName())){
                       String championPlaceholder = champion.getName().toLowerCase().replaceAll("\\p{Punct}","").replaceAll(" ","");
                       Log.e("champname", "generateTraitLists: " + championPlaceholder);

                       int resourceImage = getResources().getIdentifier(championPlaceholder, "drawable", getContext().getPackageName());
                       ChildModel c = new ChildModel();
                       c.setImage(resourceImage);

                       Log.e("resourcenumber", "generateTraitLists: " + resourceImage);
                       traitsList.get(i).getListChampion().add(c);
                   }
               }
           }
        }
        Log.e("listcheck", "generateTraitLists: " + spacePirate.getListChampion());
        Log.e("listcheck2", "generateTraitLists: " + traitsList.size());

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
