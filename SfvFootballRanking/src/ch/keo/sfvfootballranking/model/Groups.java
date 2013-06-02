package ch.keo.sfvfootballranking.model;

import ch.keo.sfvfootballranking.controller.Parser;

import java.io.IOException;
import java.util.Map;

public class Groups {
    
    private String urlAgeGroups;
    private Map<String,String> groups;
    
    public Groups(String urlAgeGroups) {
	this.urlAgeGroups = urlAgeGroups;
	
    }

    public Map<String,String> getGroups() throws IOException {
	String urlToParse = this.urlAgeGroups;
	Parser parsedGroups = new Parser();
	this.groups = parsedGroups.parseGroups(urlToParse);
	return groups;
    }
}
