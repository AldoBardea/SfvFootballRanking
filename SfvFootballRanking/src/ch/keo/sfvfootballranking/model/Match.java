package ch.keo.sfvfootballranking.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Match implements Comparable<Match> {
    
    private Date matchDateTime;
    private Team teamHome;
    private Team teamAway;
    private Integer goalsHome;
    private Integer goalsAway;

    public Match(String matchDateTime, Team teamHome, Team teamAway, Integer goalsHome, Integer goalsAway) {
	this.teamHome = teamHome;
	this.teamAway = teamAway;
	this.goalsHome = goalsHome;
	this.goalsAway = goalsAway;
	this.matchDateTime = this.calculateDateTime(matchDateTime);
    }
    
    private Date calculateDateTime(String matchDateTime) {
	Date dateTime = null;
	try {
	    dateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(matchDateTime);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return dateTime;
    }

    public String getMatchDay() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(this.matchDateTime);
    }
    
    public Team getTeamHome() {
        return teamHome;
    }
    
    public Team getTeamAway() {
        return teamAway;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public Integer getGoalsAway() {
        return goalsAway;
    }
    
    @Override
    public String toString() {
	String out = "matchDateTime = " + this.getMatchDay();
	out += "\nteamHome = " + teamHome + "\nteamAway = " + teamAway;
	out += "\ngoalsHome = " + goalsHome + " goalsAway = " + goalsAway;
	return out;
    }

    @Override
    public int compareTo(Match matchToCompare) {
	final int BEFORE = -1;
	final int EQUAL = 0;
	final int AFTER = 1;
	
	if (this.matchDateTime.after(matchToCompare.matchDateTime))
	    return AFTER;
	if (this.matchDateTime.before(matchToCompare.matchDateTime))
	    return BEFORE;
	
	return EQUAL;
    }

}
