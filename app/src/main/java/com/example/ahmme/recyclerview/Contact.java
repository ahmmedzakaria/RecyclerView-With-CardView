package com.example.ahmme.recyclerview;

/**
 * Created by ahmme on 1/27/2017.
 */

public class Contact {
    int img_id;
    String name;
    String emai;

    public Contact(int img_id, String name, String emai) {
        this.img_id = img_id;
        this.name = name;
        this.emai = emai;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }
}
