package ch.keo.sfvfootballranking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    
    private String urlRegion;
    private String urlAgeGroupToParse;
    private String groupUrl;
    
    
    public Parser() {

    }
    

    public Map<String,String> parseAgeGroups(String urlRegion) throws IOException {
	Map<String,String> ageGroupsMap = new HashMap<String,String>();
	this.urlRegion = urlRegion;
	
	    Document doc = Jsoup.connect(this.urlRegion).get();
	    Elements els = doc.select("li > a[href^=?ln=]");
	    
	    for(Element e : els	) {
		
		String ageGroupTxt = null;
		String ageGroup = null;
		
		ageGroupTxt = e.text(); // Gibt Bezeichnung Liga aus
		ageGroup = e.attr("href").toString().substring(4); // Gibt Nummer der Liga aus
		ageGroupsMap.put(ageGroupTxt, ageGroup);
	    }
	    
        return ageGroupsMap;	
    }
  
    public Map<String,String> parseGroups(String urlRegion) {
	Map<String,String> groupsMap = new HashMap<String,String>();

	this.urlAgeGroupToParse = urlRegion;
	
	try {
	    Document doc = Jsoup.connect(urlAgeGroupToParse).get();
	    Elements els =  doc.select("ul.subNavi > LI > A[href*=&sg=]");
	    
	    for(Element e : els	) {
		
		String groupTxt = null;
		String group = null;
		int index;
		
		groupTxt = e.text(); // Gibt Bezeichnung Gruppe aus
		index = groupTxt.indexOf(" ");
		groupTxt = groupTxt.substring((index + 1), groupTxt.length());
		group = e.attr("href").toString(); // Gibt Link der Gruppe aus
		groupsMap.put(groupTxt, group);
	    }
	    
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
        return groupsMap;
    }
    
    public List<List<String>> parse(String groupUrl) {
	
	this.groupUrl = groupUrl;
        List<List <String>> rows = new ArrayList<List<String>>();
        System.out.println("Fetching :" + this.groupUrl);
        
        Document doc;
	try {
	    doc = Jsoup.connect(this.groupUrl).get();
            Element el =  doc.getElementById("ctl00_cbhContent_sfvSpielbetrieb_ctl00_tbResultate");          
            Elements els = el.select("tr");
            
            int header_count = 1;
            String match_date = null;
            
            for(Element e : els) {
                boolean isDate = false;
                boolean isDateAdded = false;
                int fieldCounter = 0;
                List<String> row = new ArrayList<String>();
                
                if (e.select("td").hasClass("tableNISTT")) {
                    continue;
                }
                
                if (e.select("td").hasClass("sppTitel")) {
                    match_date = e.select("td.sppTitel").text().substring(3);
                    continue;
                }
                
                row.add(match_date);
                
                if (e.select("td > div").hasClass("tabContRes")) {
                    row.add(e.select("td > div.tabContRes").text());
                } else {
                    row.add("00:00");
                }
                
                if (e.select("td.sppTeamsTS > div").hasClass("tabContTeamA")) {
                    row.add(e.select("td.sppTeamsTS > div.tabContTeamA").text());
                }
                
                if (e.select("td.sppTeamsTS > div").hasClass("tabContTeamB")) {
                    row.add(e.select("td.sppTeamsTS > div.tabContTeamB").text());
                }
                
                if (e.select("td > div").hasClass("tabContResA")) {
                    row.add(e.select("td > div.tabContResA").text());
                } else {
                    row.add("-1");
                }
                
                if (e.select("td > div").hasClass("tabContResB")) {
                    row.add(e.select("td > div.tabContResB").text());
                } else {
                    row.add("-1");
                }
                rows.add(row);
            }
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
        return rows;
    }
}
