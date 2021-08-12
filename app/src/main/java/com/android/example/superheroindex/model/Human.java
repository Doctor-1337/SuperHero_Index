package com.android.example.superheroindex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Human {

    @SerializedName("full-name")
    public String humanName;

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public ArrayList<String> aliases;

    public String getFirst_appearance() {
        return first_appearance;
    }

    public void setFirst_appearance(String first_appearance) {
        this.first_appearance = first_appearance;
    }

    @SerializedName("first-appearance")
    public String first_appearance;

    public String getHumanName() {
        return humanName;
    }

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }
}