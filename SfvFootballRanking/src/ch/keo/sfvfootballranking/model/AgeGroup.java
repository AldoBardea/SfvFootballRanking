package ch.keo.sfvfootballranking.model;

public class AgeGroup {
    
    private String choosenAgeGroup;
    private String ageGroupUrl;

    public AgeGroup() {
	
    }
    
    public String getAgeGroupUrl() {
	this.ageGroupUrl = "/?ln=" + this.choosenAgeGroup;
	
	return ageGroupUrl;
    }
    
    public String getAgeGroup() {
	
	return choosenAgeGroup;
    }

    public void setAgeGroup(String choosenAgeGroup) {
	this.choosenAgeGroup = choosenAgeGroup;
	
    }  

}
