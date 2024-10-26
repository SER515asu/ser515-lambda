package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.UUID;

public class PossibleBlocker {
    private String id;
    private String name;
    private String description;
    private int probability;

    public PossibleBlocker(String name, String desc){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = desc;
        this.probability = 0;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getProbability() {
        return String.valueOf(probability);
    }

    public String print(){
        return this.id + " - " + this.name + " - " + this.description + " - " + this.probability;
    }
}
