package com.example.smartcyclev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    SharedPreferences sharedPref;
    //SharedPreferences cantBeRecycled;
    String[] electronicsR = {"Computer", "Musical instrument", "Mobile phone", "Television", "Watch", "Clock"};
    String[] plasticsR = {"Tableware", "Cup", "Flowerpot", "Saucer", "Bottle"};
    String[] metalR = {"Tableware", "Nail", "Cutlery", "Musical instrument", "Vehicle", "Clock", "Watch", "Cans", "Cups"};
    String[] paper = {"all"};
    String[] batteries = {"all"};
    String[] woodRs = {"Musical instrument", "Chair", "Toy", "Tableware"};
    String[] glassRs = {"Cup", "Porcelain"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context contex = getBaseContext();
        //SharedPreferences sharedPreferences = contex.getSharedPreferences("DataStuff", Context.MODE_PRIVATE);
        sharedPref = getBaseContext().getSharedPreferences("SharedFile", Context.MODE_PRIVATE);
        Button btn2 = (Button)findViewById(R.id.invisibleButton);
        TextView txt = (TextView)findViewById(R.id.textActual);
        if (sharedPref.contains("status")){
            txt.setText("You already viewed all the stuff lol");
            btn2.setVisibility(View.VISIBLE);
        }else{
            txt.setText("HEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEE");
        }
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        addElectronics(); addBatteries(); addGlass(); addPlastics(); addWoods(); addMetals();
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getBaseContext().getSharedPreferences("SharedFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("status", "visited");
                editor.apply();
                Intent i = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(i);
            }
        });
    }
    private List<RecyclingOptions> produceList (String[] names){
        List<RecyclingOptions> lister = new ArrayList<RecyclingOptions>();
        for (int i = 0; i < names.length; i++){
            RecyclingOptions a = new RecyclingOptions();
            a.type = names[i];
            lister.add(a);
        }return lister;
    }
    public void addElectronics(){
        for (int i = 0; i < produceList(electronicsR).size(); i++){
            RecyclingDatabase.getInstance().electronicsDAO().insertAll(produceList(electronicsR).get(i));
        }
    }
    public void addGlass(){
        for (int i = 0; i < produceList(glassRs).size(); i++){
            RecyclingDatabase.getInstance().glassDAO().insertAll(produceList(glassRs).get(i));
        }
    }
    public void addBatteries(){
        for (int i = 0; i < produceList(batteries).size(); i++){
            RecyclingDatabase.getInstance().batteriesDAO().insertAll(produceList(batteries).get(i));
        }
    }
    public void addPlastics(){
        for (int i = 0; i < produceList(plasticsR).size(); i++){
            RecyclingDatabase.getInstance().plasticDAO().insertAll(produceList(plasticsR).get(i));
        }
    }
    public void addMetals(){
        for (int i = 0; i < produceList(metalR).size(); i++){
            RecyclingDatabase.getInstance().metalDAO().insertAll(produceList(metalR).get(i));
        }
    }
    public void addWoods(){
        for (int i = 0; i < produceList(woodRs).size(); i++){
            RecyclingDatabase.getInstance().woodDAO().insertAll(produceList(woodRs).get(i));
        }
    }
}
