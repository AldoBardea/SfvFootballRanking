package ch.keo.sfvfootballranking.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import ch.keo.sfvfootballranking.R;
import ch.keo.sfvfootballranking.controller.ImageAdapter;
import ch.keo.sfvfootballranking.controller.ReadSfv;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.gridview);
	
	GridView gridview = (GridView) findViewById(R.id.gridview);
	gridview.setAdapter(new ImageAdapter(this));
	
	gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    
	    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	    
	    ReadSfv rsfv = new ReadSfv();
	    String baseUrl = rsfv.getBaseUrl();
	    String regionUrlPart = rsfv.getRegionUrlPart(position);
	    rsfv.setRegionChoosen(baseUrl + regionUrlPart);
	    
	    Intent i = new Intent(MainActivity.this,AgeGroupChooser.class);
	    i.putExtra("regionUrl", baseUrl + regionUrlPart);
	    i.putExtra("baseUrl", baseUrl);
	    startActivity(i);
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
}
