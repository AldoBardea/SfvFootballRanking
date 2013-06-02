package ch.keo.sfvfootballranking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import ch.keo.sfvfootballranking.controller.ParserExecuterAgeGroups;

public class AgeGroupChooser extends Activity {
    
    private String baseUrl;
    private String regionUrl;
    private Map<String,String> ageGroupsBack = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.listview);
	
	Bundle extras = getIntent().getExtras();
      
        if (extras != null) {
            this.regionUrl = (String)extras.getSerializable("regionUrl");
            this.baseUrl = (String)extras.getSerializable("baseUrl");
            
            try {
    		ExecutorService executor = Executors.newSingleThreadExecutor();
    		Future<Map<String, String>> future = executor.submit(new ParserExecuterAgeGroups(this.regionUrl));
    		this.ageGroupsBack = future.get();
        	
	    } catch (Exception e) {
		System.out.println("not reachable");
	    }
        }
        
        // Tabelle mit Altergruppen aufbereiten
        List<String> ageGroups = new ArrayList<String>();
        for (String key : this.ageGroupsBack.keySet()) {
            ageGroups.add(key);
        }
        
	ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ageGroups);
	final ListView lv = (ListView)findViewById(R.id.listView1);
	
	lv.setAdapter(adapter);
	
	lv.setOnItemClickListener(new OnItemClickListener() {
	    
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    long arg3) {
		Intent intent = new Intent(AgeGroupChooser.this,GroupChooser.class);
		intent.putExtra("ageGroupUrl", AgeGroupChooser.this.regionUrl + "/?ln=" 
			+ AgeGroupChooser.this.ageGroupsBack.get(lv.getAdapter().getItem(arg2).toString()));
		intent.putExtra("baseUrl", AgeGroupChooser.this.baseUrl);
		startActivity(intent);
	    }
	});
    }
}
