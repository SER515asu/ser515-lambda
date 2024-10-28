package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.*;

public class PossibleBlockersStore {
    private static PossibleBlockersStore _possibleBlockersStore;
    private HashMap<String, PossibleBlocker> _blockers;

    PossibleBlockersStore(){
        this._blockers = new HashMap<>();
    }

    public static PossibleBlockersStore getInstance(){
        if(_possibleBlockersStore == null){
            _possibleBlockersStore = new PossibleBlockersStore();
        }

        return _possibleBlockersStore;
    }

    public void addNewBlocker(PossibleBlocker pb){
        this._blockers.put(pb.getId(), pb);
    }

    public int size(){
        return this._blockers.size();
    }

    public List<PossibleBlocker> getListOfPossibleBlockers(){
        return new ArrayList<>(this._blockers.values());
    }

    public PossibleBlocker deleteBlockerById(String id){
        PossibleBlocker blockerToDelete = this._blockers.get(id);
        this._blockers.remove(id);
        return blockerToDelete;
    }

    public void deleteBlocker(PossibleBlocker blockerToDelete){
        this._blockers.remove(blockerToDelete.getId());
    }

    public boolean checkUserStoryHasAnyBlockers(UserStory us){
        for(PossibleBlocker pb: _blockers.values()){
            if(pb.getUserStory().getId() != us.getId()){
                continue;
            }

            return true;
        }
        
        return false;
    }
}
