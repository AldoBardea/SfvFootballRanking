package ch.keo.sfvfootballranking.model;

import java.io.Serializable;

import ch.keo.sfvfootballranking.model.Team;

public class Team implements Comparable<Team>, Serializable {
    private static final long serialVersionUID = 7696663203897041767L;
    private String teamName;
    
    private Integer totalGoalsShotHome;
    private Integer totalGoalsShotAway;
    private Integer totalGoalsGotHome;
    private Integer totalGoalsGotAway;
    
    private Integer totalMatchesHome;
    private Integer totalMatchesAway;
    
    private Integer totalWinsHome;
    private Integer totalWinsAway;
    private Integer totalDrawsHome;
    private Integer totalDrawsAway;
    private Integer totalLostHome;
    private Integer totalLostAway;
    
    private enum _rankingMethods {OVERALL, HOME, AWAY};
    private _rankingMethods currentRankingMethod;
    public int __internal_rank_order;
        
    public Team(String team) {
	this.teamName = team;
	
	this.totalGoalsShotHome = 0;
	this.totalGoalsShotAway = 0;
	this.totalGoalsGotHome = 0;
	this.totalGoalsGotAway = 0;

	this.totalMatchesHome = 0;
	this.totalMatchesAway = 0;

	this.totalWinsHome = 0;
	this.totalWinsAway = 0;
	this.totalDrawsHome = 0;
	this.totalDrawsAway = 0;
	this.totalLostHome = 0;
	this.totalLostAway = 0;
	
	// set default ranking calculation method
	this.setCurrentRankingMethodOverall();
    }
    
    public void setCurrentRankingMethodOverall() {
        this.currentRankingMethod = _rankingMethods.OVERALL;
    }
    
    public void setCurrentRankingMethodHome() {
	this.currentRankingMethod = _rankingMethods.HOME;
    }
    
    public void setCurrentRankingMethodAway() {
	this.currentRankingMethod = _rankingMethods.AWAY;
    }

    // Main Statistic Calculator
    public void incrementStatistics(boolean isHome, Integer goalsShot, Integer goalsGot) {
	if (isHome) {
	    this.incrementStatisticsHome(goalsShot, goalsGot);
	} else {
	    this.incrementStatisticsAway(goalsShot, goalsGot);
	}
    }
    
    private void incrementStatisticsHome(Integer goalsShot, Integer goalsGot) {
	// total match count home
	this.totalMatchesHome++;
	if (goalsShot >= 0 && goalsGot >= 0) {
	    // WIN
	    if (goalsShot > goalsGot) {
		this.totalWinsHome++;
	    }
	    // LOST
	    else if (goalsShot < goalsGot) {
		this.totalLostHome++;
	    }
	    // DRAW
	    else {
		this.totalDrawsHome++;
	    }
	    this.totalGoalsShotHome += goalsShot;
	    this.totalGoalsGotHome  += goalsGot;
	}
    }
    
    private void incrementStatisticsAway(Integer goalsShot, Integer goalsGot) {
	this.totalMatchesAway++;
	if (goalsShot >= 0 && goalsGot >= 0) {
	    // WIN
	    if (goalsShot > goalsGot) {
		this.totalWinsAway++;
	    }
	    // LOST
	    else if (goalsShot < goalsGot) {
		this.totalLostAway++;
	    }
	    // DRAW
	    else {
		this.totalDrawsAway++;
	    }
	    this.totalGoalsShotAway += goalsShot;
	    this.totalGoalsGotAway  += goalsGot;
	}
    }
    
    
    
    // Calculate Points
    public Integer getPointsOverall() {
	return this.getPointsHome() + this.getPointsAway();
    }
    
    public Integer getPointsHome() {
	Integer totalPointsHome = 0;
	totalPointsHome = (3 * this.totalWinsHome) + (this.totalDrawsHome);
	return totalPointsHome;
    }
    
    public Integer getPointsAway() {
	Integer totalPointsAway = 0;
	totalPointsAway = (3 * this.totalWinsAway) + (this.totalDrawsAway);
	return totalPointsAway;
    }
    
    public Integer getPoints() {
	if (this.currentRankingMethod == _rankingMethods.OVERALL) {
	    return this.getPointsOverall();
	}
	
	if (this.currentRankingMethod == _rankingMethods.HOME) {
	    return this.getPointsHome();
	}
	
	if (this.currentRankingMethod == _rankingMethods.AWAY) {
	    return this.getPointsAway();
	}
	
	return -1;
    }
    
    // Calculate Matches
    public Integer getMatchesPlayedOverall() {
	return this.getMatchesPlayedHome() + this.getMatchesPlayedAway();
    }
    
    public Integer getMatchesPlayedHome() {
	return this.totalWinsHome + this.totalDrawsHome + this.totalLostHome;
    }
    
    public Integer getMatchesPlayedAway() {
	return this.totalWinsAway + this.totalDrawsAway + this.totalLostAway;
    }
    
    public Integer getTotalMatchesOverall() {
	return this.getTotalMatchesHome() + this.getTotalMatchesAway();
    }
    
    public Integer getTotalMatchesHome() {
	return this.totalMatchesHome;
    }
    
    public Integer getTotalMatchesAway() {
	return this.totalMatchesAway;
    }
    
    public Integer getTotalOpenMatchesOverall() {
	return this.getTotalMatchesOverall() - this.getMatchesPlayedOverall();
    }
    
    public Integer getTotalOpenMatchesHome() {
	return this.getTotalMatchesHome() - this.getMatchesPlayedHome();
    }
    
    public Integer getTotalOpenMatchesAway() {
	return this.getTotalMatchesAway() - this.getMatchesPlayedAway();
    }
    
    public Integer getTotalMatchesWon() {
	return this.getMatchesWonHome() + this.getMatchesWonAway();
    }
    
    public Integer getMatchesWonHome() {
	return this.totalWinsHome;
    }
    
    public Integer getMatchesWonAway() {
	return this.totalWinsAway;
    }
    
    public Integer getTotalMatchesDraw() {
	return this.getMatchesDrawHome() + this.getMatchesDrawAway();
    }
    
    public Integer getMatchesDrawHome() {
	return this.totalDrawsHome;
    }
    
    public Integer getMatchesDrawAway() {
	return this.totalDrawsAway;
    }
    
    public Integer getTotalMatchesLost() {
	return this.getMatchesLostHome() + this.getMatchesLostAway();
    }
    
    public Integer getMatchesLostHome() {
	return this.totalLostHome;
    }
    
    public Integer getMatchesLostAway() {
	return this.totalLostAway;
    }
    
    // Calculate Goals
    public Integer getGoalsShotOverall() {
	return this.getGoalsShotHome() + this.getGoalsShotAway();
    }
    
    public Integer getGoalsShotHome() {
	return this.totalGoalsShotHome;
    }
    
    public Integer getGoalsShotAway() {
	return this.totalGoalsShotAway;
    }
    
    public Integer getGoalsGotOverall() {
	return this.getGoalsGotHome() + this.getGoalsGotAway();
    }
    
    public Integer getGoalsGotHome() {
	return this.totalGoalsGotHome;
    }
    
    public Integer getGoalsGotAway() {
	return this.totalGoalsGotAway;
    }
    
    public Integer getTotalGoalDifference() {
	return this.getGoalsShotOverall() - this.getGoalsGotOverall();
    }
    
    public Integer getGoalDifferenceHome() {
	return this.getGoalsShotHome() - this.getGoalsGotHome();
    }
    
    public Integer getGoalDifferenceAway() {
	return this.getGoalsShotAway() - this.getGoalsGotAway();
    }
    
    public Integer getGoalDifference() {
	if (this.currentRankingMethod == _rankingMethods.OVERALL) {
	    return this.getTotalGoalDifference();
	}
	
	if (this.currentRankingMethod == _rankingMethods.HOME) {
	    return this.getGoalDifferenceHome();
	}
	
	if (this.currentRankingMethod == _rankingMethods.AWAY) {
	    return this.getGoalDifferenceAway();
	}
	
	return -1;
    }
    
    // General Setter/Getter
    public String getTeamName() {
	return this.teamName;
    }
    
    @Override
    public String toString() {
	return this.teamName + " = " + " totalGoalsShotHome : " + this.totalGoalsShotHome + " totalGoalsShotAway : " + this.totalGoalsShotAway + " totalGoalsGotHome : " + this.totalGoalsGotHome + " totalGoalsGotAway : " + this.totalGoalsGotAway + " totalMatchesHome : " + this.totalMatchesHome + " totalMatchesAway : " + this.totalMatchesAway + " totalWinsHome : " + this.totalWinsHome + " totalWinsAway : " + this.totalWinsAway + " totalDrawsHome : " + this.totalDrawsHome + " totalDrawsAway : " + this.totalDrawsAway + " totalLostHome : " + this.totalLostHome + " totalLostAway : " + this.totalLostAway;
    }

    @Override
    public int compareTo(Team teamToCompare) {
	final int BEFORE = -1;
	final int EQUAL = 0;
	final int AFTER = 1;
	
	this.__internal_rank_order = EQUAL;
	
	/*  Art. 7
            1.
            Für die Feststellung der Rangordnung von Mannschaften innerhalb einer Gruppe sind massgebend:
            
            in erster Linie die Zahl der erzielten Punkte;
            sodann die bessere Tordifferenz;
            sodann die grössere Zahl der erzielten Tore;
            sodann die Tordifferenz aus den direkten Begegnungen der beteiligten, punktgleichen Mannschaften;
            schliesslich die grössere Zahl der auswärts erzielten Tore.
            
            2.
            Abteilungen und Regionen sind ermächtigt, an zweiter Stelle, nach der Zahl der erzielten Punkte, die 
            Fairplay-Rangliste als für die Feststellung der Rangliste massgebend zu bezeichnen.
	 */
	
	// points not equal
	if (this.getPoints() > teamToCompare.getPointsOverall()) {
	    this.__internal_rank_order = BEFORE;
	    return BEFORE;
	}
	if (this.getPoints() < teamToCompare.getPointsOverall()) {
	    this.__internal_rank_order = AFTER;
	    return AFTER;
	}
	
	// points are equal... then use fairplay points...
	
	// points are equal / fairplay is equal... then use goal difference ...
	if (this.getGoalDifference() > teamToCompare.getGoalDifference())  {
	    this.__internal_rank_order = BEFORE;
	    return BEFORE;
	}
	if (this.getGoalDifference() < teamToCompare.getGoalDifference()) {
	    this.__internal_rank_order = AFTER; 
	    return AFTER;
	}
	
	// points are equal / fairplay is equal / goal difference is equal... then use goals shot...
	if (this.getGoalsShotOverall() > teamToCompare.getGoalsShotOverall()) {
	    this.__internal_rank_order = BEFORE;
	    return BEFORE;
	}
	if (this.getGoalsShotOverall() < teamToCompare.getGoalsShotOverall()) {
	    this.__internal_rank_order = AFTER;
	    return AFTER;
	}
	
	/* points are equal / fairplay is equal / goal difference is equal / goals shot are equal... 
	   then use goal difference in direct matches... */
	
	
	/* points are equal / fairplay is equal / goal difference is equal / goals shot are equal / 
	   goal difference in direct matches is equal... then use goals shot away*/
	if (this.getGoalsShotAway() > teamToCompare.getGoalsShotAway()) {
	    this.__internal_rank_order = BEFORE;
	    return BEFORE;
	}
	if (this.getGoalsShotAway() < teamToCompare.getGoalsShotAway()) {
	    this.__internal_rank_order = AFTER;
	    return AFTER;
	}
	
	// for empty tables ....
	if (this.getMatchesPlayedOverall() == 0 && teamToCompare.getMatchesPlayedOverall() == 0)
	    return this.getTeamName().compareTo(teamToCompare.getTeamName());
	else
	    return EQUAL;
    }

}
