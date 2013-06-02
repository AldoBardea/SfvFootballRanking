package ch.keo.sfvfootballranking.model;

public class Group {
    
    private String choosenGroup;
    private String groupUrl;

    public Group() {
	
    }
    
    public String getGroupUrl() {
	this.groupUrl = this.choosenGroup;
	
	return groupUrl;
    }
    
    public String getGroup() {
	
	return choosenGroup;
    }

    public void setGroup(String choosenGroup) {
	this.choosenGroup = choosenGroup;
	
    }  

}
