package ch.keo.sfvfootballranking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class ParserExecutorRanking implements Callable<List<List<String>>> {
     
    private String groupUrl;
    
    public ParserExecutorRanking(String groupUrl) {
	this.groupUrl = groupUrl;
    }
    
    @Override
    public List<List<String>> call() throws Exception {
	
	List<List<String>> container = new ArrayList<List<String>>();

	Parser urlParser = new Parser(); // start cntlm for connection behind proxy
	container = urlParser.parse(this.groupUrl);
	
	return container;
    }
}
