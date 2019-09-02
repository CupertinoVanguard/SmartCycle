package com.example.smartcyclev1;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class ProjectClassV2 extends AppCompatActivity {
    CustomItem2 adapter;
    String[] notApplicableList = {"Muscle", "Bird", "Flesh", "Human", "Dog", "Fun", "Selfie", "Room", "Bumper", "Pattern", "Cat", "Monochrome", "Eyelash"};
    String[] customResponses = {"Not Applicable", "Yes", "No", "More information necessary"};
    String[] sureFireRecyclables = {"Paper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str = getIntent().getStringExtra("materialClicked");
        setContentView(R.layout.activity_project_class_v2);
        RecyclerView recycle = (RecyclerView)findViewById(R.id.horizontalor);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycle.setLayoutManager(horizontalLayoutManager);
        List<DiffMaterialFactory> stuff = new ArrayList<DiffMaterialFactory>();
        List<String> listerName = new ArrayList<String>();
        TextView t1 = findViewById(R.id.textView8);
        TextView t2 = findViewById(R.id.textView7);
        TextView t3 = findViewById(R.id.textView9);
        ImageView imgViewer = (ImageView)findViewById(R.id.imageView2);
        imgViewer.setImageResource(R.drawable.logoimage);
        List<Float> listerConfidence = new ArrayList<Float>();
        List<ImageRecogObject> listerImageRecogObs = ImageRecogDatabase.getInstance().imgDAO().getAll();
        for (int j = 0; j < listerImageRecogObs.size(); j++){
            listerName.add(listerImageRecogObs.get(j).imageType);
            listerConfidence.add(listerImageRecogObs.get(j).confidenceLevel);
        }
        String namer = listerName.remove(0);
        t1.setText(namer);
        t2.setText(""+ listerConfidence.remove(0));
        if (isWithinList(namer, notApplicableList)){
            t3.setText("Recyclable: Not Applicable");
        }else{

        }
        for (int i = 0; i < listerName.size(); i++){
            Log.d("I am adding the stuff", "to stuff");
            String a = listerName.get(i);
            if (isWithinList(a, notApplicableList)){
                stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
            }else if (isWithinList(a, sureFireRecyclables)){
                stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
            }else if (str.equals("Metals")){
                if (RecyclingDatabase.getInstance().metalDAO().findByName(a) != null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }else if (str.equals("Cardboard/Paper")){
                if (RecyclingDatabase.getInstance().metalDAO().findByName(a) == null && RecyclingDatabase.getInstance().electronicsDAO().findByName(a) == null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }else if (str.equals("Electronics")){
                if (RecyclingDatabase.getInstance().electronicsDAO().findByName(a) != null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }else if (str.equals("Glass")){
                if (RecyclingDatabase.getInstance().glassDAO().findByName(a) != null && RecyclingDatabase.getInstance().metalDAO().findByName(a) == null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }else if (str.equals("Plastic")){
                if (RecyclingDatabase.getInstance().plasticDAO().findByName(a) != null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }else if (str.equals("Batteries")){
                if (RecyclingDatabase.getInstance().metalDAO().findByName(a) == null && RecyclingDatabase.getInstance().electronicsDAO().findByName(a) == null){
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
                }else{
                    stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.plastic));
                }
            }
        }
        adapter = new CustomItem2(stuff, this);
        recycle.setAdapter(adapter);
    }

    private boolean isWithinList(String name, String[] list){
        for (int i = 0; i < list.length; i++){
            if (list[i].equals(name)){
                return true;
            }
        }return false;
    }
}
