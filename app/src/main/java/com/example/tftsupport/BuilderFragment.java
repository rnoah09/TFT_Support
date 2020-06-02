package com.example.tftsupport;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuilderFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private GridView simpleGrid;
    private ImageView imageView;
    private List<Integer> logos;
    private List<Champion> champList;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_builder, container, false);

        logos = new ArrayList<>();
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.champions);
        String sxml = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        Champion[] champions =  gson.fromJson(sxml, Champion[].class);
        champList = Arrays.asList(champions);

        imageView = rootView.findViewById(R.id.imageViewtemSelection);
        viewFlipper = rootView.findViewById(R.id.viewflipper_builder);
        listView = rootView.findViewById(R.id.listview_builder);

        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);

        for(Champion champion: champList){
            String championPlaceholder = champion.getName().toLowerCase().replaceAll("\\p{Punct}","").replaceAll(" ","");
            int resourceImage = getResources().getIdentifier(championPlaceholder, "drawable", getContext().getPackageName());
            logos.add(resourceImage);
        }

        simpleGrid = rootView.findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getContext(), logos);
        simpleGrid.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageDrawable(getResources().getDrawable(logos.get(position)));
                for(Champion champion: champList){
                    String championPlaceholder = champion.getName().toLowerCase().replaceAll("\\p{Punct}","").replaceAll(" ","");
                    int resourceImage = getResources().getIdentifier(championPlaceholder, "drawable", getContext().getPackageName());

                    if(logos.get(position) == resourceImage){
                        ItemAdapter itemAdapter = new ItemAdapter(getContext(), -1, champion.getItems());
                        listView.setAdapter(itemAdapter);
                    }
                }
                viewFlipper.showNext();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
                Log.e("onclick", "onClick:");
            }
        });


        return rootView;
    }

    private class ItemAdapter extends ArrayAdapter<String> {
        private List<String> recommended;
        private List<Item> itemsList;
        private List<Component> componentsList;

        public ItemAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            this.recommended = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            if(convertView == null) {
                convertView = inflater.inflate(R.layout.item_recommended, parent, false);
            }
            itemsList = new ArrayList<>();

            InputStream XmlFileInputStream = getResources().openRawResource(R.raw.items);
            String sxml1 = readTextFile(XmlFileInputStream);

            Gson gson = new Gson();
            Item[] items =  gson.fromJson(sxml1, Item[].class);
            itemsList = Arrays.asList(items);

            InputStream XmlFileInputStream2 = getResources().openRawResource(R.raw.components);
            String sxml2 = readTextFile(XmlFileInputStream2);

            Gson gson2 = new Gson();
            Component[] components =  gson2.fromJson(sxml2, Component[].class);
            componentsList = Arrays.asList(components);

            TextView textViewItem = convertView.findViewById(R.id.textview_itemname);
            ImageView imageViewItem = convertView.findViewById(R.id.imageview_fullitem);
            ImageView imageViewComponent1 = convertView.findViewById(R.id.imageview_component1);
            ImageView imageViewComponent2 = convertView.findViewById(R.id.imageview_component2);

            String item = recommended.get(position);
            textViewItem.setText(item);

            for(Item i: itemsList){
                if (i.getName().equals(item)){
                    int resourceImage = getResources().getIdentifier(i.getId(), "drawable", getContext().getPackageName());
                    imageViewItem.setImageDrawable(getResources().getDrawable(resourceImage));

                    List<String> components1 = i.getComponents();
                    Log.e("itemlist", "getView: " + i.getComponents() );
                    for(Component component : components){
                        if(component.getName().equals(components1.get(0))){
                            int resourceImage1 = getResources().getIdentifier(component.getId(), "drawable", getContext().getPackageName());
                            imageViewComponent1.setImageDrawable(getResources().getDrawable(resourceImage1));
                        }
                        if(component.getName().equals(components1.get(1))) {
                            int resourceImage2 = getResources().getIdentifier(component.getId(), "drawable", getContext().getPackageName());
                            imageViewComponent2.setImageDrawable(getResources().getDrawable(resourceImage2));
                        }
                    }
                }
            }

            return convertView;
        }
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
