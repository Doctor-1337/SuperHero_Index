package com.android.example.superheroindex.model;

import com.google.gson.annotations.SerializedName;

public class Connections {

    public String getGroup_affiliation() {
        return group_affiliation;
    }

    public void setGroup_affiliation(String group_affiliation) {
        this.group_affiliation = group_affiliation;
    }

    @SerializedName("group-affiliation")
    String group_affiliation;

}
