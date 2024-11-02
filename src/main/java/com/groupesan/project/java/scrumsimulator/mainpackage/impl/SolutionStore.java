package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SolutionStore {
    private static SolutionStore solutionStore;

    public static SolutionStore getInstance() {
        if (solutionStore == null) {
            solutionStore = new SolutionStore();
        }
        return solutionStore;
    }

    private List<String> blockers;

    //private List<String> solutions;

    private HashMap<String, ArrayList<String>> blockerSolutionMap;

    private HashMap<String, String> bestSolutions;

    private SolutionStore() {
        blockers = new ArrayList<>();
        //solutions = new ArrayList<>();
        blockerSolutionMap = new HashMap<>();
        bestSolutions = new HashMap<>();
    }

    public void addBlocker(String blocker) {
        blockers.add(blocker);
    }

    public void addSolution(String solution, String blocker) {
        if (blockerSolutionMap.containsKey(blocker)) {
            blockerSolutionMap.get(blocker).add(solution);
        }
        else {
            ArrayList<String> solutions = new ArrayList<>();
            solutions.add(solution);
            blockerSolutionMap.put(blocker, solutions);
        }
    }

    public List<String> getBlockers() {
        return new ArrayList<>(blockers); 
    }

    public ArrayList<String> getSolutions(String blocker) {
        return blockerSolutionMap.get(blocker);
    }

    public void clearStore() {
        blockers.clear();
        blockerSolutionMap.clear();
    }

    public void updateBestSolution(String solution, String blocker_id) {
        bestSolutions.put(blocker_id, solution);
    }

    public String getBestSolution(String blocker_id) {
        if (bestSolutions != null && bestSolutions.containsKey(blocker_id)) {
            return bestSolutions.get(blocker_id);
        }
        else {
            return "No best solution stored";
        }
    }
}
