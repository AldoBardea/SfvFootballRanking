package ch.keo.sfvfootballranking.controller;

import java.io.IOException;
import java.util.Map;

import ch.keo.sfvfootballranking.model.AgeGroups;
import ch.keo.sfvfootballranking.model.Regions;

public class ReadSfv {
    
    private String baseUrl = "http://mobile.football.ch";
    private String regionChoosen;
    private String connectionUrl;
    
    public ReadSfv() {
	
    }
    
    public ReadSfv(boolean needsProxy) {
	if (needsProxy) {
	    //System.setProperty("http.proxyHost", "bisonnb1366"); // start cntlm for connection behind proxy
	    System.setProperty("http.proxyHost", "10.224.163.188"); // start cntlm for connection behind proxy
	    System.setProperty("http.proxyPort", "3389");
	}
    }
    
    
    public String getBaseUrl() {

	return this.baseUrl;
    }
    
    public String getRegionUrlPart(int index) {
	Regions r = new Regions(); // Regions.class initialisieren
	String regionUrl = r.getRegion(index); // getRegion(byIndex of Region Array) => later getRegion(position)
	
	return regionUrl;
    }
    
    public void setRegionChoosen(String regionChoosen) {
	this.regionChoosen = regionChoosen;
    }
    
    public String getRegionChoosen() {
	return this.regionChoosen;
    }
    
    
    public Map<String,String> getAgeGroups(String ageGroupUrl) throws IOException {
	AgeGroups ags = new AgeGroups(ageGroupUrl); // AgeGroups.class initialisieren
	System.out.println(ageGroupUrl + " in der Get Methode");
	Map<String, String> ageGroups = ags.getAgeGroups(); // getAgeGroups for Display on Decision Screen (AgeGroupChooser)
	System.out.println(ageGroups);

	return ageGroups;
	
    }
    
        
    public void setConnectionUrl(String url) {
	this.connectionUrl = url;
    }
    
    public String getConnectionUrl() {
	return this.connectionUrl;
    }
}
