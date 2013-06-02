package ch.keo.sfvfootballranking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Teams implements Serializable {

    private static final long serialVersionUID = 6883658691708218501L;
    private List<Team> teams;
    
    public Teams() {
	this.teams = new ArrayList<Team>();
    }
    
    private void setRankingOnTeamsOverall() {
	for(Team t: this.teams) {
	    t.setCurrentRankingMethodOverall();
	}
    }
    
    private void setRankingOnTeamsHome() {
	for(Team t: this.teams) {
	    t.setCurrentRankingMethodHome();
	}
    }
    
    private void setRankingOnTeamsAway() {
	for(Team t: this.teams) {
	    t.setCurrentRankingMethodAway();
	}
    }
    
    public boolean teamExists(String team) {
	for(Team t: this.teams) {
	    if (t.getTeamName().equalsIgnoreCase(team)) {
		return true;
	    }
	}
	return false;
    } 
    
    public boolean addTeam(String team) {
	boolean alreadyThere = this.teamExists(team);
	if (!alreadyThere) {
	    this.teams.add(new Team(team));
	    return true;
	}
	return false;
    }
    
    public Team getTeam(String team) {
	for (Team t: this.teams) {
	    if (t.getTeamName().equalsIgnoreCase(team)) {
		return t;
	    }
	}
	return null;
    }
    
    public ArrayList<Team> sortByRankingOverall() {
	this.setRankingOnTeamsOverall();
	return this.sortList();
    }
    
    public ArrayList<Team> sortByRankingHome() {
	this.setRankingOnTeamsHome();
	return this.sortList();
    }
    
    public ArrayList<Team> sortByRankingAway() {
	this.setRankingOnTeamsAway();
	return this.sortList();
    }
    
    private ArrayList<Team> sortList() {
	List<Team> sorted = new ArrayList<Team>(this.teams);
	Collections.sort(sorted);
	return new ArrayList<Team>(sorted);
    }
    
    public List<Team> getTeams() {
	return this.teams;
    }
    
}
