package com.example.smartcyclev1;

public class DiffMaterialFactory {
    private String name;
    private int photo;

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public DiffMaterialFactory(String a, int o){
        name = a;
        photo = o;
    }
}
