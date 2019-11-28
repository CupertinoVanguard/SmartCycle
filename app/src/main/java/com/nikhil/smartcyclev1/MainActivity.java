package com.nikhil.smartcyclev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppIntro {
    //Button btn;
    //TextView txt;
    SharedPreferences sharedPref;
    //SharedPreferences cantBeRecycled;
    String[] electronicsR = {"Computer", "Musical instrument", "Mobile phone", "Television", "Watch", "Clock"};
    String[] plasticsR = {"Tableware", "Cup", "Flowerpot", "Saucer", "Bottle"};
    String[] metalR = {"Tableware", "Nail", "Cutlery", "Musical instrument", "Vehicle", "Clock", "Watch", "Cans", "Cups"};
    String[] paper = {"all"};
    String[] batteries = {"all"};
    String[] woodRs = {"Musical instrument", "Chair", "Toy", "Tableware"};
    String[] glassRs = {"Cup", "Porcelain"};
    String[] slideTitles = {"WELCOME TO SmartCycle", "SNAP", "CHOOSE", "FIND OUT!"};
    String[] descriptions = {"", "Take a quick snap of the object(s) you wish to recycle", "In order to further refine the classification, choose the closest personal classification of the object's type.", "Find out if the object or objects are recyclable and learn more about the process of recycling"};
    int[] slideImages = {R.drawable.mainlogo, R.drawable.snap, R.drawable.chooserimage, R.drawable.logoimage};
    String mainBackgroundColor = "#FFB37A24";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Context contex = getBaseContext();
        //SharedPreferences sharedPreferences = contex.getSharedPreferences("DataStuff", Context.MODE_PRIVATE);
        sharedPref = getBaseContext().getSharedPreferences("SharedFile", Context.MODE_PRIVATE);
        //sharedPref.getBoolean()
        //change the newInstance method
        for (int index = 0; index < slideTitles.length; index++){addSlide(AppIntroFragment.newInstance(slideTitles[index], descriptions[index], slideImages[index], Color.parseColor(mainBackgroundColor)));}
        showSkipButton(false);
        setProgressButtonEnabled(true);

        setZoomAnimation();
        if (sharedPref.getBoolean("isVisited", false)) {
            Log.d("Already here bro", "bruhhh");
            Intent i = new Intent(getBaseContext(), SecondActivity.class);
            startActivity(i);

            finish();
            return;
        }
        Log.d("bruhh", "Already");
        sharedPref.edit().putBoolean("isVisited", true).apply();
        addElectronics();
        addBatteries();
        addGlass();
        addPlastics();
        addWoods();
        addMetals();

    }

    private List<RecyclingOptions> produceList(String[] names, String typer) {
        List<RecyclingOptions> lister = new ArrayList<RecyclingOptions>();
        for (int i = 0; i < names.length; i++) {
            RecyclingOptions a = new RecyclingOptions();
            a.name = names[i];
            a.type = typer;
            lister.add(a);
        }
        return lister;
    }
    //use the new insertAll method
    public void addElectronics() {
        for (int i = 0; i < produceList(electronicsR, "Electronics").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(electronicsR, "Electronics").get(i));
        }
    }

    public void addGlass() {
        for (int i = 0; i < produceList(glassRs, "Glass").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(glassRs, "Glass").get(i));
        }
    }

    public void addBatteries() {
        for (int i = 0; i < produceList(batteries, "Batteries").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(batteries, "Batteries").get(i));
        }
    }

    public void addPlastics() {
        for (int i = 0; i < produceList(plasticsR, "Plastic").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(plasticsR, "Plastic").get(i));
        }
    }

    public void addMetals() {
        for (int i = 0; i < produceList(metalR, "Metal").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(metalR, "Metal").get(i));
        }
    }

    public void addWoods() {
        for (int i = 0; i < produceList(woodRs, "Wood").size(); i++) {
            RecyclingDatabase.getInstance().recyclingDAO().insertAll(produceList(woodRs, "Wo").get(i));
        }
    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, SecondActivity.class));
        // Do something when users tap on Done button.
    }
    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}



