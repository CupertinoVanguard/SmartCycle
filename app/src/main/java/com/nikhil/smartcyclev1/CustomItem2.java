package com.nikhil.smartcyclev1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomItem2 extends RecyclerView.Adapter<CustomItem2.NewHolderView>{
    private List<DiffMaterialFactory> lister;
    private Context context;
    public CustomItem2(List<DiffMaterialFactory> l, Context c){
        lister = l;
        context = c;
    }
    @Override
    public CustomItem2.NewHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customitemsecond, viewGroup, false);
        return new CustomItem2.NewHolderView(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomItem2.NewHolderView holderView, final int i) {
        holderView.txt.setText(lister.get(i).getName());
        holderView.img.setImageResource(lister.get(i).getPhoto());;
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
    class NewHolderView extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

        NewHolderView(View itemview){
            super(itemview);
            img = (ImageView)itemview.findViewById(R.id.recycleCard);
            txt = (TextView)itemview.findViewById(R.id.nameText);
        }
    }
}

