package com.example.smartcyclev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SecondActivity extends AppCompatActivity {
    Button confirmNext;
    Button takePic;
    ImageView imageView;
    TextView textView;
    static HashMap<String, Float> vals = new HashMap<>();
    List<String> completeList = new ArrayList<String>();
    private static final int cameraRequest = 1888;
    String finalResults = "";
    static Intent resutlsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        confirmNext = findViewById(R.id.button2);
        takePic = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraRequest);
                //getBaseContext().startActivity(SecondActivity.resutlsIntent);
            }
        });
        confirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                Object[] array = SecondActivity.vals.keySet().toArray();
                for (int i = 0; i < array.length; i++){
                    sb.append(array[i].toString()).append(",");
                }
                SharedPreferences sharedPreferences = getSharedPreferences("Results", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("All the classifications", sb.toString());
                editor.apply();
                startActivity(SecondActivity.resutlsIntent);
            }
        });


        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK){
            Bitmap bMap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bMap);
            runDetector(bMap);
        }
    }

    public void runDetector(Bitmap bitmap){
        FirebaseVisionImage img = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionImageLabeler detector = FirebaseVision.getInstance().getOnDeviceImageLabeler();

        detector.processImage(img).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                String a = "";
                for (int i = 0; i < firebaseVisionImageLabels.size(); i++){
                    String text = firebaseVisionImageLabels.get(i).getText(); float confidence = firebaseVisionImageLabels.get(i).getConfidence();
                    SecondActivity.vals.put(text, confidence);
                    String b = text + " " + confidence;
                    if (i != firebaseVisionImageLabels.size() - 1){
                        a += b + ", ";
                    }
                    else{
                        a += b;
                    }
                }
                textView.setText(a);
                SecondActivity.resutlsIntent = new Intent(getBaseContext(), ChooserActivity.class);
                SecondActivity.resutlsIntent.putExtra("results", a);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        textView.setText("The processing of the image failed");
                    }
                });
    }
}
