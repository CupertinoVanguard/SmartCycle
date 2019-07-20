package com.example.smartcyclev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProjectionActivity extends AppCompatActivity {
    TextView txtView;
    TextView txtView2;
    TextView txtView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection);
        String givenArg = getIntent().getExtras().getString("materialClicked");
        String resultsArg = getIntent().getExtras().getString("results");
        txtView = (TextView)findViewById(R.id.textView2);
        txtView.setText(givenArg);
        txtView2 = (TextView)findViewById(R.id.textView3);
        txtView2.setText(resultsArg);
        txtView3 = (TextView)findViewById(R.id.textView4);
        if(resultsArg.contains("Tire") && givenArg.equals("Rubber")){
            txtView3.setText("Recyclable");
        }else{
            txtView3.setText("Not Rest");
        }
    }
}
