package com.example.smartcyclev1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

public class ProjectionActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private TextView txtView;
    private TextView txtView2;
    private TextView txtView3;
    private Spinner spinner;
    private String[] diffObjects = {"Plastic", "Metal", "Glass", "Wood", "Cotton", "Wool","Rubber", "Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection);
        String givenArg = getIntent().getExtras().getString("materialClicked");
        String bois = getIntent().getExtras().getString("Main Indicator Found");
        String resultsArg = getIntent().getExtras().getString("results");
        txtView = (TextView)findViewById(R.id.textView2);
        txtView.setText(bois);
        txtView2 = (TextView)findViewById(R.id.textView3);

        txtView3 = (TextView)findViewById(R.id.textView4);
        spinner = (Spinner)findViewById(R.id.spinner);

        String stringer = "";
        SharedPreferences sharedPreferences = getSharedPreferences("Results", Context.MODE_PRIVATE);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences();
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        stringer = sharedPreferences.getString("All the classifications", "The bois");
        String[] newValues = stringer.split(",");
        /*
        Object[] arrayedValues = values.toArray();
        String[] fullArray = new String[arrayedValues.length];
        for (int j = 0; j < arrayedValues.length; j++){
            fullArray[j] = arrayedValues[j].toString();
        }*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diffObjects);


               // new ArrayAdapter.createFromResource(this,
               // R.array.planets_array, android.R.layout.simple_spinner_item);//new ArrayAdapter(this, android.R.layout.simple_spinner_item, fullArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        if (stringer.length() == 0){
            txtView2.setText("It is 0");
        }else {
            txtView2.setText(stringer);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtView.setText((parent.getItemAtPosition(position)).toString());
        if (txtView.getText().equals("Mercury")){
            txtView3.setText("Recyclable");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
