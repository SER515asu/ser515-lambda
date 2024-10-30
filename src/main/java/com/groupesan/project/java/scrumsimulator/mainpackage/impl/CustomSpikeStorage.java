package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import java.util.HashMap;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpike;

//store all the spkies and their corresponding blocker ids
public class CustomSpikeStorage {
    private static CustomSpikeStorage customSpikeStorage;
    private HashMap<String, CustomSpike> customSpikeMap;

    private CustomSpikeStorage() {
        customSpikeMap = new HashMap<>();
    }

    public static CustomSpikeStorage getInstance() {
        if (customSpikeStorage == null) {
            customSpikeStorage = new CustomSpikeStorage();
        }
        return customSpikeStorage;
    }

    public void addCustomSpike(String blockerId, CustomSpike customSpike) {
        customSpikeMap.put(blockerId, customSpike);
    }

    
    
}
