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

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context contex = getBaseContext();
        //SharedPreferences sharedPreferences = contex.getSharedPreferences("DataStuff", Context.MODE_PRIVATE);
        sharedPref = getBaseContext().getSharedPreferences("SharedFile", Context.MODE_PRIVATE);
        Button btn2 = (Button)findViewById(R.id.invisibleButton);
        TextView txt = (TextView)findViewById(R.id.textActual);
        if (sharedPref.contains("visited")){
            txt.setText("You already viewed all the stuff lol");
            btn2.setVisibility(View.VISIBLE);
        }else{
            txt.setText("HEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEEHEHEHEHEEE");
        }
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);

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
}
