package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserIdentifier;

public class User extends ScrumObject {
    //this app is only for one user now
    private static User currentUser;
    private String name = "default";
    private ScrumRole scrumRole;
    private ScrumIdentifier id;

    public User(String username) {
        this.name = username;
    }

    public User(String username, ScrumRole scrumRole) {
        this(username);
        this.scrumRole = scrumRole;
    }

    public void register() {
        this.id = new UserIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
        this.scrumRole.doRegister();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScrumRole getRole() {
        return scrumRole;
    }

    public void setRole(ScrumRole scrumRole) {
        this.scrumRole = scrumRole;
    }

    public UserIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This User has not been registered and does not have an ID yet!");
        }
        return (UserIdentifier) id;
    }

    public String toString() {
        if (isRegistered()) {
            return this.getId().toString();
        }
        return getName() + " " + getRole() + " (unregistered)";
    }

    //return the only user instance
    //create the user instance if it is null
    //update the role
    //return the user instance without updating it: name and role are null
    public static User getCurrentUser(String name, ScrumRole role) {
        if (currentUser == null) {
            currentUser = new User(name, role);
        }
        else if (name == null && role == null) {
            return currentUser;
        }
        else if (currentUser.getRole() == null || 
        !currentUser.getRole().getName().equals(role.getName())) {
            currentUser.setRole(role);
        }
        return currentUser;
    }

}
