package com.android.example.superheroindex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyPojo {


    @SerializedName("response")
    public String heroExist;

    @SerializedName("results")
    public ArrayList<Hero> heroes;

    public String getHeroExist() {
        return heroExist;
    }

    public void setHeroExist(String heroExist) {
        this.heroExist = heroExist;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }
}

