package ch.keo.sfvfootballranking.controller;

import java.util.Map;
import java.util.concurrent.Callable;

import ch.keo.sfvfootballranking.model.Groups;

public class ParserExecuterGroups implements Callable<Map<String,String>>{

    private String ageGroupsUrl;
    
    
    public ParserExecuterGroups(String ageGroupsUrl) {
	this.ageGroupsUrl = ageGroupsUrl;
    }

    @Override
    public Map<String,String> call() throws Exception {	
	Groups gs = new Groups(this.ageGroupsUrl);
	Map<String,String> ageGroups = gs.getGroups();
	
	return ageGroups;
    }
}
