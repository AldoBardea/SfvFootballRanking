package ch.keo.sfvfootballranking.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import ch.keo.sfvfootballranking.R;
import ch.keo.sfvfootballranking.controller.MatchController;
import ch.keo.sfvfootballranking.controller.ParserExecuterGroups;
import ch.keo.sfvfootballranking.controller.ParserExecutorRanking;
import ch.keo.sfvfootballranking.model.Matches;
import ch.keo.sfvfootballranking.model.Teams;

public class GroupChooser extends Activity{
    
    private String baseUrl;
    private String ageGroupUrl;
    private Map<String,String> groupsBack = new HashMap<String,String>();
    
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.listview);
	
	Bundle extras = getIntent().getExtras();
	
        if (extras != null) {
            this.ageGroupUrl = (String)extras.getSerializable("ageGroupUrl");
            this.baseUrl = (String)extras.getSerializable("baseUrl");
           
            try {
    		ExecutorService executor = Executors.newSingleThreadExecutor();
    		Future<Map<String, String>> future = executor.submit(new ParserExecuterGroups(this.ageGroupUrl));
    		this.groupsBack = future.get();
        	
	    } catch (Exception e) {
		System.out.println("not reachable");
	    }
        }
        
        // Tabelle mit Gruppen aufbereiten
        List<String> groups = new ArrayList<String>();
        for (String key : this.groupsBack.keySet()) {
            groups.add(key);
        }
//        Collections.replaceAll(groups, "[0-9]", "0$0"); // Für Sortierung führende Null einfügen
//        System.out.println(groups);
        Collections.sort(groups); // Sortieren der Gruppen
        System.out.println(groups);

	ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, groups);
	final ListView lv = (ListView)findViewById(R.id.listView1);
	
	lv.setAdapter(adapter);
        
	lv.setOnItemClickListener(new OnItemClickListener() {
	    
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
    		Teams allTeams = new Teams();
    		Matches allMatches = new Matches();
    		MatchController matchController = new MatchController(allMatches, allTeams);
    		
    		List<List<String>> container = new ArrayList<List<String>>();
    		ExecutorService executor = Executors.newSingleThreadExecutor();
    		
    		Future<List<List<String>>> future = executor.submit(new ParserExecutorRanking(GroupChooser.this.baseUrl 
    			+ GroupChooser.this.groupsBack.get(lv.getAdapter().getItem(arg2).toString())));
		
    		try {
		    container = future.get();
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (ExecutionException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
    		
    		matchController.generateTeamsAndMatches(container);

		Intent intent = new Intent(GroupChooser.this,RankingActivity.class);
		intent.putExtra("teams", matchController.getAllTeams());
		startActivity(intent);
	    }
	});
    }
}
