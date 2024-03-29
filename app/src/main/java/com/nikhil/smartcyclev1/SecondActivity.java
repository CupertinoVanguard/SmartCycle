package com.nikhil.smartcyclev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import java.util.HashMap;
import java.util.List;

//remove the static, remove Second Activity, remove all the arrays as a static. Static will be there all the way until the end
//try assets maybe
public class SecondActivity extends Activity {
    Button confirmNext;
    Button takePic;
    ImageView imageView;
    TextView textView;
    static HashMap<String, Float> vals = new HashMap<>();
    private static final int cameraRequest = 1888;
    static Intent resutlsIntent;
    ListView resultsList;
    static String[] array;
    static String[] confidenceArray;
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        confirmNext = findViewById(R.id.button2);
        takePic = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        resultsList = (ListView) findViewById(R.id.simpleListView);

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
                if (array == null || array.length == 0) {
                    return;
                }
                    StringBuilder sb = new StringBuilder();
                    Object[] array = SecondActivity.vals.keySet().toArray();
                    for (int i = 0; i < array.length; i++) {
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

    public int contains(String[] array, String a) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(a)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK) {
            Bitmap bMap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bMap);
            runDetector(bMap);
        }
    }

    public void runDetector(Bitmap bitmap) {
        FirebaseVisionImage img = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionImageLabeler detector = FirebaseVision.getInstance().getOnDeviceImageLabeler();

        detector.processImage(img).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                String a = "";
                SecondActivity.array = new String[firebaseVisionImageLabels.size()];
                SecondActivity.confidenceArray = new String[firebaseVisionImageLabels.size()];
                //database = Room.inMemoryDatabaseBuilder(getApplicationContext(), ImageRecogDatabase.class).allowMainThreadQueries().build();
                List<ImageRecogObject> deleteList = ImageRecogDatabase.getInstance().imgDAO().getAll();
                if (deleteList.size() != 0) {
                    for (int index = 0; index < deleteList.size(); index++) {
                        ImageRecogDatabase.getInstance().imgDAO().delete(deleteList.get(index));
                        Log.d("here", "deleting");
                    }
                }
                for (int i = 0; i < firebaseVisionImageLabels.size(); i++) {
                    String text = firebaseVisionImageLabels.get(i).getText();
                    float confidence = firebaseVisionImageLabels.get(i).getConfidence();
                    SecondActivity.vals.put(text, confidence);

                    ImageRecogObject thing = new ImageRecogObject();
                    thing.confidenceLevel = confidence;
                    thing.imageType = text;
                    ImageRecogDatabase.getInstance().imgDAO().insertAll(thing);
                    String b = text + " " + confidence;
                    array[i] = text;
                    confidenceArray[i] = confidence + "";
                    if (i != firebaseVisionImageLabels.size() - 1) {
                        a += b + ", ";
                    } else {
                        a += b;
                    }
                }

                SecondActivity.resutlsIntent = new Intent(getBaseContext(), ChooserActivity.class);
                //You can send it as an arrayList
                SecondActivity.resutlsIntent.putExtra("results", a);
                SecondActivity.arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.activity_list_view, R.id.textViewList, SecondActivity.array);
                resultsList.setAdapter(arrayAdapter);
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
