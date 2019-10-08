package com.example.smartcyclev1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;
public class ProjectClassV2 extends AppCompatActivity {
    CustomItem2 adapter;
    String[] notApplicableList = {"Muscle", "Bird", "Flesh", "Human", "Dog", "Fun", "Selfie", "Room", "Bumper", "Pattern", "Cat", "Monochrome", "Eyelash", "Foot", "Ear", "Insect", "Sitting"};
    String[] customResponses = {"Not Applicable", "Yes", "No", "More information necessary"};
    String[] sureFireRecyclables = {"Paper", "Jeans", "Textile"};
    Button learnMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String stringExtra = getIntent().getStringExtra("materialClicked");
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
        learnMore = (Button)findViewById(R.id.button5);

        learnMore.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Uri uriUrl = Uri.parse("https://www.epa.gov/recycle/recycling-basics");
                                             Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                                             startActivity(launchBrowser);
                                         }
                                     });
        List<Float> listerConfidence = new ArrayList<Float>();
        List<ImageRecogObject> listerImageRecogObs = ImageRecogDatabase.getInstance().imgDAO().getAll();
        for (int j = 0; j < listerImageRecogObs.size(); j++){
            listerName.add(listerImageRecogObs.get(j).imageType);
            listerConfidence.add(listerImageRecogObs.get(j).confidenceLevel);
        }


        String namer = listerName.remove(0);
        t1.setText(namer);
        t2.setText(""+ listerConfidence.remove(0));
        if (recyclableFactory(namer, stringExtra) == false){
            imgViewer.setImageResource(R.drawable.no);
            t3.setText("Recyclable: Not Applicable or Not Recyclable");
        }else{
            imgViewer.setImageResource(R.drawable.logoimage);
            t3.setText("Recyclable: YES!");
        }
        for (int i = 0; i < listerName.size(); i++){
            Log.d("I am adding the stuff", "to stuff");
            String a = listerName.get(i);
            boolean recyclability = recyclableFactory(a, stringExtra);
            if (recyclability == true){
                stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.logoimage));
            }else{
                stuff.add(new DiffMaterialFactory(listerName.get(i) + " " + listerConfidence.get(i), R.drawable.no));
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

    private boolean recyclableFactory(String a, String str){
        List<RecyclingOptions> fullResults = new ArrayList<>();
        fullResults = RecyclingDatabase.getInstance().recyclingDAO().findByName(a);
        if (fullResults != null) {
            if (isWithinList(a, notApplicableList)){
                return false;
            }else if (isWithinList(a, sureFireRecyclables)){
                return true;
            }else if (str.equals("Metal")){
                if (isPresent("Metal", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }else if (str.equals("Cardboard/Paper")){
                if (!isPresent("Metal", fullResults) && !isPresent("Electronics", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }else if (str.equals("Electronics")){
                if (isPresent("Electronics", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }else if (str.equals("Glass")){
                if (isPresent("Glass", fullResults)  && !isPresent("Metal", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }else if (str.equals("Plastic")){
                if (isPresent("Plastic", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }else if (str.equals("Batteries")){
                if (!isPresent("Batteries", fullResults) && !isPresent("Electronics", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }
            else if (str.equals("Wood")){//Need to recheck conditions for this - need to change the DAO though?!
                if (!isPresent("Metal", fullResults) && isPresent("Wood", fullResults)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    private boolean isPresent(String a, List<RecyclingOptions> lister){
        for (int i = 0; i < lister.size(); i++){
            if (lister.get(i).type.equals("a")){
                return true;
            }
        }return false;
    }
}
