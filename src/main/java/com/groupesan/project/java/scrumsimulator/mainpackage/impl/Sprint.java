package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Sprint {
    private ArrayList<UserStory> userStories = new ArrayList<>();
    private String name;
    private String description;
    private int length;
    private int remainingDays;
    private int id;
    private Date startDate;
    private Date endDate;
    private int numberOfSprints;

    public Sprint(String name, String description, int length, int id) {
        this.name = name;
        this.description = description;
        this.length = length;
        this.remainingDays = length;
        this.id = id;
    }

    public void addUserStory(UserStory us) {
        userStories.add(us);
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public int getDaysRemaining() {
        return remainingDays;
    }

    public void decrementRemainingDays() {
        if (remainingDays > 0)
            remainingDays--;
    }

    public int getId() {
        return id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberOfSprints() {
        return numberOfSprints;
    }

    public void setNumberOfSprints(int numberOfSprints) {
        this.numberOfSprints = numberOfSprints;
    }

    @Override
    public String toString() {
        String header = "Sprint " + this.id + ": " + this.name + "\n";
        StringBuilder USes = new StringBuilder();

        for (UserStory us : userStories) {
            USes.append(us.toString()).append("\n");
        }
        return header + USes;
    }
}
