package ch.keo.sfvfootballranking.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import ch.keo.sfvfootballranking.R;
import ch.keo.sfvfootballranking.model.Team;
import ch.keo.sfvfootballranking.model.Teams;

public class RankingActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        TableLayout tl = (TableLayout)findViewById(R.id.tableLayout1);
        
        Bundle extras = getIntent().getExtras();
        Teams allTeams = null;
        
        if (extras != null) {
            allTeams = (Teams)extras.getSerializable("teams");
        }

        TableRow tr_head = new TableRow(this);
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

        TextView label_ranking = new TextView(this);
        label_ranking.setId(20);
        //label_ranking.setText("");
        label_ranking.setTextColor(Color.WHITE);
        label_ranking.setPadding(0, 5, 5, 5);
        tr_head.addView(label_ranking);// add the column to the table row here
        
        TextView label_team = new TextView(this);
        label_team.setId(21);
        label_team.setText("TEAM");
        label_team.setTextColor(Color.WHITE);
        label_team.setPadding(5, 5, 5, 5);
        tr_head.addView(label_team);// add the column to the table row here

        TextView label_matchesPlayed = new TextView(this);
        label_matchesPlayed.setId(22);// define id that must be unique
        label_matchesPlayed.setText("G"); // set the text for the header 
        label_matchesPlayed.setTextColor(Color.WHITE); // set the color
        label_matchesPlayed.setGravity(Gravity.CENTER);
        label_matchesPlayed.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_matchesPlayed); // add the column to the table row here
        
        TextView label_matchesWon = new TextView(this);
        label_matchesWon.setId(23);// define id that must be unique
        label_matchesWon.setText("W"); // set the text for the header 
        label_matchesWon.setTextColor(Color.WHITE); // set the color
        label_matchesWon.setPadding(5, 5, 5, 5); // set the padding (if required)
        label_matchesWon.setGravity(Gravity.CENTER);
        tr_head.addView(label_matchesWon); // add the column to the table row here
        
        TextView label_matchesDraw = new TextView(this);
        label_matchesDraw.setId(24);// define id that must be unique
        label_matchesDraw.setText("D"); // set the text for the header 
        label_matchesDraw.setTextColor(Color.WHITE); // set the color
        label_matchesDraw.setGravity(Gravity.CENTER);
        label_matchesDraw.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_matchesDraw); // add the column to the table row here
        
        TextView label_matchesLost = new TextView(this);
        label_matchesLost.setId(25);// define id that must be unique
        label_matchesLost.setText("L"); // set the text for the header 
        label_matchesLost.setTextColor(Color.WHITE); // set the color
        label_matchesLost.setGravity(Gravity.CENTER);
        label_matchesLost.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_matchesLost); // add the column to the table row here
        
        TextView label_goalsShot = new TextView(this);
        label_goalsShot.setId(26);// define id that must be unique
        label_goalsShot.setText("+"); // set the text for the header 
        label_goalsShot.setTextColor(Color.WHITE); // set the color
        label_goalsShot.setGravity(Gravity.CENTER);
        label_goalsShot.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_goalsShot); // add the column to the table row here
        
        TextView label_doublePoint = new TextView(this);
        label_doublePoint.setId(27);// define id that must be unique
        label_doublePoint.setText("/"); // set the text for the header 
        label_doublePoint.setTextColor(Color.WHITE); // set the color
        label_doublePoint.setGravity(Gravity.CENTER);
        label_doublePoint.setWidth(1);
        label_doublePoint.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_doublePoint); // add the column to the table row here
        
        TextView label_goalsGot = new TextView(this);
        label_goalsGot.setId(28);// define id that must be unique
        label_goalsGot.setText("-"); // set the text for the header 
        label_goalsGot.setTextColor(Color.WHITE); // set the color
        label_goalsGot.setGravity(Gravity.CENTER);
        label_goalsGot.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_goalsGot); // add the column to the table row here
        
        TextView label_points = new TextView(this);
        label_points.setId(29);// define id that must be unique
        label_points.setText("P"); // set the text for the header 
        label_points.setTextColor(Color.WHITE); // set the color
        label_points.setGravity(Gravity.CENTER);
        label_points.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_points); // add the column to the table row here
        
        tl.addView(tr_head, new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        
        Integer count=0;
        int ranking = 0;
        int prev_rank = -1;
        for (Team t : allTeams.sortByRankingOverall()) {
            if (!(prev_rank == 0 && t.__internal_rank_order == 0))
        	ranking++;
            if (ranking > 0)
        	prev_rank = t.__internal_rank_order;

           String team = t.getTeamName();// get the first variable
           Integer games = t.getMatchesPlayedOverall();// get the second variable
           Integer gamesWon = t.getTotalMatchesWon();// get the third variable
           Integer gamesDraw = t.getTotalMatchesDraw();// get the fourth variable
           Integer gamesLost = t.getTotalMatchesLost();// get the fifth variable
           Integer goalsShot = t.getGoalsShotOverall();// get the sixth variable
           String doublePoint = ":";// get the seventh variable
           Integer goalsGot = t.getGoalsGotOverall();// get the eighth variable
           Integer points = t.getPoints();// get the ninth variable
           
           // Create the table row
           TableRow tr = new TableRow(this);
           if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
           tr.setId(100+count);
           tr.setLayoutParams(new LayoutParams(
           LayoutParams.MATCH_PARENT,
           LayoutParams.WRAP_CONTENT));
    
           //Create two columns to add as table data
            // Create a TextView to add date
           
           TextView labelRANKING = new TextView(this);
           labelRANKING.setId(200+count); 
           labelRANKING.setText(String.valueOf(ranking));
           labelRANKING.setPadding(0, 0, 5, 0);
           labelRANKING.setTextColor(Color.BLACK);
           labelRANKING.setGravity(Gravity.RIGHT);
           tr.addView(labelRANKING);
           
           TextView labelTEAM = new TextView(this);
           labelTEAM.setId(200+count); 
           labelTEAM.setText(team);
           labelTEAM.setPadding(5, 0, 5, 0);
           labelTEAM.setTextColor(Color.BLACK);
           tr.addView(labelTEAM);

           TextView labelGAMES = new TextView(this);
           labelGAMES.setId(200+count);
           labelGAMES.setText(games.toString());
           labelGAMES.setTextColor(Color.BLACK);
           labelGAMES.setGravity(Gravity.CENTER);
           tr.addView(labelGAMES);

           TextView labelWINS = new TextView(this);
           labelWINS.setId(200+count);
           labelWINS.setText(gamesWon.toString());
           labelWINS.setTextColor(Color.BLACK);
           labelWINS.setGravity(Gravity.CENTER);
           tr.addView(labelWINS);

           TextView labelDRAWS = new TextView(this);
           labelDRAWS.setId(200+count);
           labelDRAWS.setText(gamesDraw.toString());
           labelDRAWS.setTextColor(Color.BLACK);
           labelDRAWS.setGravity(Gravity.CENTER);
           tr.addView(labelDRAWS);

           TextView labelLOST = new TextView(this);
           labelLOST.setId(200+count);
           labelLOST.setText(gamesLost.toString());
           labelLOST.setTextColor(Color.BLACK);
           labelLOST.setGravity(Gravity.CENTER);
           tr.addView(labelLOST);
    
           TextView labelGOALSSHOT = new TextView(this);
           labelGOALSSHOT.setId(200+count);
           labelGOALSSHOT.setText(goalsShot.toString());
           labelGOALSSHOT.setTextColor(Color.BLACK);
           labelGOALSSHOT.setGravity(Gravity.CENTER);
           tr.addView(labelGOALSSHOT);
    
           TextView labelDOUBLEPOINT = new TextView(this);
           labelDOUBLEPOINT.setId(200+count);
           labelDOUBLEPOINT.setText(doublePoint.toString());
           labelDOUBLEPOINT.setTextColor(Color.BLACK);
           labelDOUBLEPOINT.setGravity(Gravity.CENTER);
           labelDOUBLEPOINT.setWidth(1);
           tr.addView(labelDOUBLEPOINT);
    
           TextView labelGOALSGOT = new TextView(this);
           labelGOALSGOT.setId(200+count);
           labelGOALSGOT.setText(goalsGot.toString());
           labelGOALSGOT.setTextColor(Color.BLACK);
           labelGOALSGOT.setGravity(Gravity.CENTER);
           tr.addView(labelGOALSGOT);
    
           TextView labelPOINTS = new TextView(this);
           labelPOINTS.setId(200+count);
           //labelPOINTS.setText(points.toString());
           labelPOINTS.setText(Html.fromHtml("<strong>" + points.toString() + "</strong>"));
           labelPOINTS.setTextColor(Color.BLACK);
           labelPOINTS.setGravity(Gravity.CENTER);
           tr.addView(labelPOINTS);
    
           // finally add this to the table row
           tl.addView(tr, new TableLayout.LayoutParams(
           	                    LayoutParams.MATCH_PARENT,
           	                    LayoutParams.WRAP_CONTENT));
           		       count++;
           		    }
            
    }

}
