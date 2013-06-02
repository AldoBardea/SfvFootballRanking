package ch.keo.sfvfootballranking.controller;

import java.util.Map;
import java.util.concurrent.Callable;

import ch.keo.sfvfootballranking.model.AgeGroups;

public class ParserExecuterAgeGroups implements Callable<Map<String,String>> {
    
    private String regionUrl;
    
    
    public ParserExecuterAgeGroups(String regionUrl) {
	this.regionUrl = regionUrl;
    }

    @Override
    public Map<String,String> call() throws Exception {	
	AgeGroups ags = new AgeGroups(this.regionUrl);
	Map<String,String> ageGroups = ags.getAgeGroups();
	
	return ageGroups;
    }
}