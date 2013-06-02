package ch.keo.sfvfootballranking.model;

import ch.keo.sfvfootballranking.controller.Parser;

import java.io.IOException;
import java.util.Map;

public class AgeGroups {
    
    private String urlRegion;
    private Map<String,String> ageGroups;
    
    public AgeGroups(String urlRegion) {
	this.urlRegion = urlRegion;
	
    }
    
    public Map<String,String> getAgeGroups() throws IOException {
	String urlToParse = this.urlRegion;
	Parser parsedAgeGroups = new Parser();
	this.ageGroups = parsedAgeGroups.parseAgeGroups(urlToParse);
	return ageGroups;
    }
}
