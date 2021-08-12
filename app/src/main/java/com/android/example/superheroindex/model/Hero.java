package com.android.example.superheroindex.model;

import com.google.gson.annotations.SerializedName;

public class Hero {

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public Connections connections;

    @SerializedName("name")
    public String heroName;

    @SerializedName("biography")
    public Human humanForm;

    public HeroImage getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(HeroImage heroImage) {
        this.heroImage = heroImage;
    }

    @SerializedName("image")
    public HeroImage heroImage;

    public LifeForm getLifeForm() {
        return lifeForm;
    }

    public void setLifeForm(LifeForm lifeForm) {
        this.lifeForm = lifeForm;
    }

    @SerializedName("appearance")
    public LifeForm lifeForm;

    public PowerIndex getPowerIndexHero() {
        return powerIndexHero;
    }

    public void setPowerIndexHero(PowerIndex powerIndexHero) {
        this.powerIndexHero = powerIndexHero;
    }

    @SerializedName("powerstats")
    PowerIndex powerIndexHero;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Human getHumanForm() {
        return humanForm;
    }

    public void setHumanForm(Human humanForm) {
        this.humanForm = humanForm;
    }
}