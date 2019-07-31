package com.example.smartcyclev1;

import android.content.Context;
import android.content.SharedPreferences;
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

public class ProjectionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView txtView;
    private TextView txtView2;
    private TextView txtView3;
    private Spinner spinner;
    private String[] diffObjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection);
        String givenArg = getIntent().getExtras().getString("materialClicked");
        String resultsArg = getIntent().getExtras().getString("results");
        txtView = (TextView)findViewById(R.id.textView2);
        txtView.setText(givenArg);
        txtView2 = (TextView)findViewById(R.id.textView3);

        txtView3 = (TextView)findViewById(R.id.textView4);
        spinner = (Spinner)findViewById(R.id.spinner);

        String stringer = "";
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        Set<String> defaults = new HashSet<String>();
        Set<String> values = sharedPreferences.getStringSet("All the classifications", defaults);
        Object[] arrayedValues = values.toArray();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayedValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for (int i = 0; i < arrayedValues.length; i++){
            stringer += arrayedValues[i].toString() + "/n";
        }
        txtView2.setText(stringer);
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

    private boolean returnRecyclingResutl(String firstParse, int i, String secondParse){
        return true;
    }
}
