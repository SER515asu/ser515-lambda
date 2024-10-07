package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

import java.util.HashMap;

public class UserStoryStore {
    private static UserStoryStore userStoryStore;
    private HashMap<ScrumIdentifier, UserStory> userStories;

    /**
     * returns the shared instance of the UserStoryStore which contains all user stories in the
     * system.
     *
     * @return
     */
    public static UserStoryStore getInstance() {
        if (userStoryStore == null) {
            userStoryStore = new UserStoryStore();
        }
        return userStoryStore;
    }


    private UserStoryStore() {
        // userStories = new ArrayList<>();
        userStories = new HashMap<>();
    }

    public void addUserStory(UserStory userStory) {
        // userStories.add(userStory);
        userStories.put(userStory.getId(), userStory);
    }

    public UserStory deleteUserStory(UserStory us){
        UserStory userStoryToDelete = userStories.get(us.getId());
        userStories.remove(us.getId());
        return userStoryToDelete;
    }

    public List<UserStory> getUserStories() {
        // return new ArrayList<>(userStories);
        return new ArrayList<>(userStories.values());
    }
}
