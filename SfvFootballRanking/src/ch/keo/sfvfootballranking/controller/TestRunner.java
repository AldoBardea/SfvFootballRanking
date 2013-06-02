package ch.keo.sfvfootballranking.controller;

import java.util.ArrayList;
import java.util.List;

import ch.keo.sfvfootballranking.model.Match;
import ch.keo.sfvfootballranking.model.Matches;
import ch.keo.sfvfootballranking.model.Team;
import ch.keo.sfvfootballranking.model.Teams;

public class TestRunner {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Teams allTeams = new Teams();
	Matches allMatches = new Matches();
	MatchController matchController = new MatchController(allMatches, allTeams);
	
	List<List<String>> container = new ArrayList<List<String>>();
	//String url = "http://www.ifv.ch/Innerschweizerischer-Fussballverband/Spielbetrieb-IFV/Meisterschaft-IFV.aspx/ln-15610/s-2013/ls-11758/sg-35463/a-sp/";
	String url = "http://www.ifv.ch/Innerschweizerischer-Fussballverband/Spielbetrieb-IFV/Meisterschaft-IFV.aspx/ln-15211/s-2013/ls-11601/sg-35010/a-sp/";
	//String url = "http://www.football.ch/ftc/Federazione-ticinese-di-calcio/Competizione-FTC/Campionato-FTC.aspx/ln-15211/s-2013/ls-11457/sg-34540/a-sp/";
	ReadSfv urlParser = new ReadSfv(true); // start cntlm for connection behind proxy
	//ReadSfv urlParser = new ReadSfv(); // connection without proxy
	urlParser.setConnectionUrl(url);
	//container = urlParser.parse();
	
	matchController.generateTeamsAndMatches(container);
	
	System.out.println("ALL TEAMS: **************************************");
	for(Team t: allTeams.getTeams()) {
	    System.out.println(t);
	}
	System.out.println("ALL TEAMS: **************************************");
	System.out.println("");
	System.out.println("ALL MATCHES: **************************************");
	for(Match m: allMatches.getMatches()) {
	    System.out.println(m);
	}
	System.out.println("ALL MATCHES: **************************************");
	
	int cnt = 1;
	for(Team t: allTeams.sortByRankingOverall()) {
	    System.out.println(cnt + ". " + String.format("%-5s %-20s", t.getTeamName(), t.getMatchesPlayedOverall()));
	    cnt++;
	}
	
	for (Match m: allMatches.getMatchesSortedByDate()) {
	    System.out.println(m.getMatchDay() + " - " + m.getTeamHome().getTeamName() + " : " + m.getTeamAway().getTeamName());
	}
	
    }

}
