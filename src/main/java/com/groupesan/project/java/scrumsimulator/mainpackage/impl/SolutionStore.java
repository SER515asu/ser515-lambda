package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class SolutionStore {
    private static SolutionStore solutionStore;

    public static SolutionStore getInstance() {
        if (solutionStore == null) {
            solutionStore = new SolutionStore();
        }
        return solutionStore;
    }

    private List<String> blockers;

    private List<String> solutions;

    private SolutionStore() {
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
