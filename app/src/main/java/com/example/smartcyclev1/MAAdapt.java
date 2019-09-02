package com.example.smartcyclev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MAAdapt extends RecyclerView.Adapter<MAAdapt.HolderView>{
    private List<DiffMaterialFactory> lister;
    private Context context;
    private String str;
    private boolean thinker;
    public MAAdapt(List<DiffMaterialFactory> l, Context c, String str, boolean thinker){
        lister = l;
        context = c;
        this.str = str;
        this.thinker = thinker;
    }
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customitem, viewGroup, false);
        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holderView, final int i) {
        holderView.txt.setText(lister.get(i).getName());
        holderView.img.setImageResource(lister.get(i).getPhoto());
        holderView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ImageRecogDatabase.getInstance().imgDAO().findByName("Musical instrument") != null && (lister.get(i).getName() != "Metal")) || (ImageRecogDatabase.getInstance().imgDAO().findByName("Musical instrument") != null && (lister.get(i).getName() != "Other"))){
                    Toast.makeText(context, "Can't be " + lister.get(i).getName() +". Click another option", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(context, ProjectClassV2.class);
                    intent.putExtra("materialClicked", lister.get(i).getName());
                    intent.putExtra("results", str);
                    context.startActivity(intent);
                    Toast.makeText(context, "click on " + lister.get(i).getName(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lister.size();
    }
    public void filtSys(List<DiffMaterialFactory> matList){
        lister = new ArrayList<>();
        lister.addAll(matList);
        notifyDataSetChanged();
    }
    class HolderView extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

        HolderView(View itemview){
            super(itemview);
            img = (ImageView)itemview.findViewById(R.id.imgCard);
            txt = (TextView)itemview.findViewById(R.id.tView);
        }
    }
}
