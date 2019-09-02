package com.example.smartcyclev1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ChooserActivity extends Activity {
    RecyclerView listshower;
    MAAdapt adapter;
    SearchView searchView;

    List<DiffMaterialFactory> lister = new ArrayList<DiffMaterialFactory>();
    static String[] namesOfObjects = {"Plastic", "Metal", "Glass", "Cardboard/Paper", "Batteries", "Electronics","Rubber", "Other"};
    int[] diffPhotoNums = {R.drawable.plastic, R.drawable.metal, R.drawable.glass, R.drawable.wood, R.drawable.cotton, R.drawable.wool, R.drawable.rubber, R.drawable.other};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        listshower = (RecyclerView)findViewById(R.id.listshow);

        listshower.setHasFixedSize(true);
        listshower.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < namesOfObjects.length; i++){
            lister.add(new DiffMaterialFactory(namesOfObjects[i], diffPhotoNums[i]));
        }

        adapter = new MAAdapt(lister, ChooserActivity.this, getIntent().getExtras().getString("results"), true);
        listshower.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.searchfiler, menu);
        final MenuItem seaarchBar = menu.findItem(R.id.search);
        searchView = (SearchView)seaarchBar.getActionView();
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.color_primary_variant));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                seaarchBar.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                final List<DiffMaterialFactory> filtermodelist = filter(lister, s);
                adapter.filtSys(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<DiffMaterialFactory> filter(List<DiffMaterialFactory> l, String answers){
        answers = answers.toLowerCase();
        final List<DiffMaterialFactory> filteredUpdateList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++){
            String str = l.get(i).getName().toLowerCase();
            if (str.contains(answers)){
                filteredUpdateList.add(l.get(i));
            }
        }
        return filteredUpdateList;
    }


}
