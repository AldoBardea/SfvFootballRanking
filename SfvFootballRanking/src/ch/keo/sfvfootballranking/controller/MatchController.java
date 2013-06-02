package ch.keo.sfvfootballranking.controller;

import java.util.List;

import ch.keo.sfvfootballranking.model.Match;
import ch.keo.sfvfootballranking.model.Matches;
import ch.keo.sfvfootballranking.model.Teams;

public class MatchController {
    private Matches allMatches;
    private Teams   allTeams;
    
    public MatchController(Matches allMatches, Teams allTeams) {
	this.allMatches = allMatches;
	this.allTeams = allTeams;
    }
    
    public void generateTeamsAndMatches(List<List<String>> data) {
	for(List<String> row: data) {
	    String matchDateTime = row.get(0) + " " + row.get(1);
	    String teamHomeStr = row.get(2);
	    String teamAwayStr = row.get(3);
	    Integer goalsHome = Integer.valueOf(row.get(4));
	    Integer goalsAway = Integer.valueOf(row.get(5));
	    
	    this.allTeams.addTeam(teamHomeStr);
	    this.allTeams.addTeam(teamAwayStr);
	    // add match
	    this.allMatches.addMatch(new Match(matchDateTime, this.allTeams.getTeam(teamHomeStr), this.allTeams.getTeam(teamAwayStr), goalsHome, goalsAway));
	    // update stats
	    this.allTeams.getTeam(teamHomeStr).incrementStatistics(true, goalsHome, goalsAway);
	    this.allTeams.getTeam(teamAwayStr).incrementStatistics(false, goalsAway, goalsHome);
	    
	}
    }
    
    public Matches getAllMatches() {
	return this.allMatches;
    }
    
    public Teams getAllTeams() {
	return this.allTeams;
    }
    
}
