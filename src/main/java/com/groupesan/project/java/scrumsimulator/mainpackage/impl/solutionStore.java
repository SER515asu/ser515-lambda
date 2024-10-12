package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class solutionStore {
    private static solutionStore solutionStore;

    public static solutionStore getInstance() {
        if (solutionStore == null) {
            solutionStore = new solutionStore();
        }
        return solutionStore;
    }

    private List<String> blockers;

    private List<String> solutions;

    private solutionStore() {
        blockers = new ArrayList<>();
        solutions = new ArrayList<>();
    }

    public void addBlocker(String blocker) {
        blockers.add(blocker);
    }

    public void addSolution(String solution) {
        solutions.add(solution);
    }

    public List<String> getBlockers() {
        return new ArrayList<>(blockers); 
    }

    public List<String> getSolutions() {
        return new ArrayList<>(solutions); 
    }

    public void clearStore() {
        blockers.clear();
        solutions.clear();
    }
}
