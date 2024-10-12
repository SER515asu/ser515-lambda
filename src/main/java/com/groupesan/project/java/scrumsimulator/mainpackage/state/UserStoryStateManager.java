package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import java.util.ArrayList;
import java.util.List;

public class UserStoryStateManager {

    // Hardcoded sample user stories. This is just for now till the backend for this is completed. 
    private static List<UserStory> sampleUserStories = new ArrayList<>(List.of(
        new UserStory("US_1: Test User Story1", "new"),
        new UserStory("US_2: Test User Story2", "new"),
        new UserStory("US_3: Test User Story3", "new")
    ));

    // Method to get all user stories
    public static List<String> getUserStories() {
        List<String> userStoryDescriptions = new ArrayList<>();
        for (UserStory userStory : sampleUserStories) {
            userStoryDescriptions.add(userStory.getDescription());
        }
        return userStoryDescriptions;
    }

    // Method to update the status of a selected User Story
    public static void updateUserStoryStatus(String userStoryDescription, String newStatus) {
        for (UserStory userStory : sampleUserStories) {
            if (userStory.getDescription().equals(userStoryDescription)) {
                userStory.setStatus(newStatus);
                System.out.println("Updated " + userStoryDescription + " to " + newStatus);
                break;
            }
        }
    }

    // Helper class to represent a User Story
    private static class UserStory {
        private String description;
        private String status;

        public UserStory(String description, String status) {
            this.description = description;
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
