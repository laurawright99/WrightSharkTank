package com.avidexposure.schoolfinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import android.app.AlertDialog;
//import android.app.Dialog;
import android.content.DialogInterface;
//import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;


public class FindSchoolView extends Activity {
	String [] miles;  //Must create arrays in advance. 
	String [] grade;
	String [] api;
	String score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findschoolactivity);

	} //END OF ONCREATE METHOD
	
		
public void onClickMiles(View v){
	miles = getResources().getStringArray(R.array.miles_array);
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("Choose Miles Radius");
	builder.setItems(miles, new DialogInterface.OnClickListener() {		
			
		    @Override
			public void onClick(DialogInterface dialog, int which){
				TextView txt = (TextView) findViewById(R.id.btn_miles); 
				txt.setText(miles[which]);
			} 	
		 });
		   
	AlertDialog alert = builder.create();
    alert.show();
    //builder.show(); SAME THING AS ABOVE

} //End of Method

public void onClickGrade(View v){
	grade= getResources().getStringArray(R.array.grade_level);
	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	builder.setTitle("Choose Grade Level");
	builder.setItems(grade, new DialogInterface.OnClickListener(){
		
		@Override
		public void onClick(DialogInterface dialog, int which){
			TextView txt = (TextView) findViewById(R.id.btn_grade);
			txt.setText(grade[which]);
		}
	});
	
	builder.show();
	
}
	
public String onClickRange(View v){

	api= getResources().getStringArray(R.array.api_score);
	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
	builder.setTitle("Choose minimum API Level");
	builder.setItems(api, new DialogInterface.OnClickListener(){
		
		@Override
		public void onClick(DialogInterface dialog, int which){
			TextView txt = (TextView) findViewById(R.id.btn_range);
			txt.setText(api[which]);
			score = api[which];
		}
	});
	
	builder.show();
	 
	return score;  //send information to search in database 
	
	
}


public void onClickSearch(View v){
	String two = "250";
	
	 Toast.makeText(getBaseContext(), "TEST" + score, Toast.LENGTH_SHORT).show();
	 
	 
	 if(two.equals(score)){
		 Toast.makeText(getBaseContext(), "score equals works" + score, Toast.LENGTH_SHORT).show();
	 } 
	 else{
		 Toast.makeText(getBaseContext(), "score too low" + score, Toast.LENGTH_SHORT).show();
	 }
	
}




	//HOME BUTTON 
	public void onClickHome(View v) {
		// TODO Auto-generated method stub
		Intent myintent = new Intent (this, SchoolActivity.class); 
		startActivity(myintent);  
	}

	
	
	
} //END OF CLASS


/*	
//Code works for Spinner view
	miles = getResources().getStringArray(R.array.miles_array);
	 
	 Spinner s1 = (Spinner) findViewById(R.id.spinner1); 
	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			 android.R.layout.simple_spinner_item, miles);
	 
	 s1.setAdapter(adapter);
	 s1.setOnItemSelectedListener(new OnItemSelectedListener()
	 {
		@Override
		 public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
		 {
			 int index = arg0.getSelectedItemPosition();
			 Toast.makeText(getBaseContext(),
					 "You have selected item: " + miles[index], Toast.LENGTH_SHORT).show();
		 }
		 @Override
		 public void onNothingSelected(AdapterView<?> arg0){}
	
	 }
	 );  
*/
