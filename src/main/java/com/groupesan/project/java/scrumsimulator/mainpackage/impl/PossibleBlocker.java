package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.UUID;

public class PossibleBlocker {
    private String id;
    private String name;
    private String description;
    private UserStory userStory;

    public PossibleBlocker(String name, String desc, UserStory userStory){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = desc;
        this.userStory = userStory;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public UserStory getUserStory(){
        return this.userStory;
    }

    public String print(boolean printId){
        if(printId){
            return this.id + " - " + this.name + " - " + this.description;
        }

        return this.name + " - " + this.description;
    }
}