package ch.keo.sfvfootballranking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Matches {
    private List<Match> matches;
    
    public Matches() {
	this.matches = new ArrayList<Match>();
    }
    
    public void addMatch(Match m) {
	this.matches.add(m);
    }
    
    public List<Match> getMatches() {
	return this.matches;
    }
   
    public List<Match> getMatchesSortedByDate() {
	List<Match> sorted = new ArrayList<Match>(this.matches);
	Collections.sort(sorted);
	return sorted;
    }
}
