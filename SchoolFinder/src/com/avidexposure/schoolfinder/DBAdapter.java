//
//  AvidSchoolFinder
//
//  Created by Laura Wright Teclemariam on 12/21/12.
//  Copyright (c) 2012 Laura Wright. All rights reserved.
//

package com.avidexposure.schoolfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
		static final String KEY_ROWID = "_id";
		static final String KEY_COUNTY = "County"; 
		static final String KEY_DISTRICT = "District";
		static final String KEY_SCHOOL = "School"; 
		static final String KEY_STREET = "Street"; 
		static final String KEY_ABR = "StreetAbr";
		static final String KEY_CITY = "City";
		static final String KEY_ZIP = "Zip";
		static final String KEY_STATE = "State"; 
		static final String KEY_API = "API"; 
 		static final String TAG = "DBAdapter"; 
		
		static final String DATABASE_NAME="Android_SchoolDB"; 
		static final String DATABASE_TABLE="School_DB";
		static final int DATABASE_VERSION = 1; 
		
		final Context context;
		
		DatabaseHelper DBHelper;  //SQLiteOpenHelper
		SQLiteDatabase data;
		
		
//		"Create table School_DB (_id integer primary key autoincrement, " + "name text not null, score text not null);";
		static final String DATABASE_CREATE = "create table " 
				+ DATABASE_TABLE
				+ "("
				+ KEY_ROWID + " integer primary key autoincrement, "
				+ KEY_COUNTY + " text not null, "
				+ KEY_DISTRICT + " text not null, "
				+ KEY_SCHOOL + " text not null, "
				+ KEY_STREET + " text not null, "
				+ KEY_ABR + " text not null, "
				+ KEY_CITY + " text not null, "
				+ KEY_ZIP + " text not null, "
				+ KEY_STATE + " text not null, "
				+ KEY_API + " text not null "
				+ ");";
				
		
		public DBAdapter(Context ctx){
			this.context = ctx;
			DBHelper = new DatabaseHelper(context); 
		}
		
		private static class DatabaseHelper extends SQLiteOpenHelper
		{
			DatabaseHelper (Context context)
			{
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
		
		
		@Override
		public void onCreate(SQLiteDatabase data){
			try {
				data.execSQL(DATABASE_CREATE);
			}
			catch(SQLException e){
				e.printStackTrace(); 
			}
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase data, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version" + oldVersion + "to" + newVersion +",which will destroy all old data");
			data.execSQL("DROP TABLE IF EXISTS schools"); 
			onCreate(data);
		}
	}
		
	//opens the databases
	public DBAdapter open() throws SQLException
	{
		data = DBHelper.getWritableDatabase(); 
		return this; 
	}
	
	//closes the databases
	public void close(){
		DBHelper.close();
	}
	
	//insert school info into the database 
	public long insertSchool (String county, String district)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_COUNTY, county);
		initialValues.put(KEY_DISTRICT, district); 
		return data.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//deletes a particular school
	public boolean deleteSchool(long rowID)
	{
		return data.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0; 
	}
	
	//retrieves all the schools
	public Cursor getAllSchools()
	{
		return data.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_COUNTY, KEY_DISTRICT, KEY_SCHOOL, KEY_STREET, KEY_ABR, KEY_CITY, KEY_ZIP, KEY_STATE, KEY_API}, null, null, null, null, null); 
	}
	
	//retrieves a particular school
	public Cursor getSchool(long rowID) throws SQLException 
	{
		//Cursor mCursor =  data.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_COUNTY, KEY_DISTRICT}, KEY_ROWID + "=" + rowID, null, null, null, null, null);
	/*	Cursor mCursor =  data.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_COUNTY, KEY_DISTRICT, KEY_SCHOOL, KEY_STREET, 
				KEY_ABR, KEY_CITY, KEY_ZIP, KEY_STATE, KEY_API}, KEY_API +"=" +rowID, null, null, null, null, null);
	*/	
		
		//RAW QUERY EXAMPLE
	String q= "SELECT rowid _id, * FROM School_DB";
		//String q= "SELECT * FROM School_DB WHERE School =" + rowID +";";
		Cursor mCursor = data.rawQuery(q, null);
		
	if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		
		return mCursor; 
	}
	
	//updates a school
	public boolean updateSchool(long rowID, String county, String district)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_COUNTY, county); 
		args.put(KEY_DISTRICT, district); 
		return data.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowID, null) >0;
	}

}