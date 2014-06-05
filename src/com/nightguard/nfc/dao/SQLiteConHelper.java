package com.nightguard.nfc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  Helper for creating the SQLite database and defining names of tables and columns.
 *  
 * @author Patrick Spitzer
 *
 */
public class SQLiteConHelper extends SQLiteOpenHelper {

	// General Informations
	public static final String DB_NAME = "NightguardNFC";
	public static final String DB_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String COL_ID = "_id";
	private static final int DB_VERSION = 1;
	
	
	// Table: NFCTagLocation
	public static final String NFCTAGLOCATION_TABLE = "NFCTagLocation";
	public static final String NFCTAGLOCATION_COL_NFCTAGID = "nfcTagId";
	public static final String NFCTAGLOCATION_COL_LOCATION = "location";
	private static final String NFCTAGLOCATION_CREATE = "CREATE TABLE " + NFCTAGLOCATION_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NFCTAGLOCATION_COL_NFCTAGID + " BLOB NOT NULL, " + NFCTAGLOCATION_COL_LOCATION + " TEXT NOT NULL );";
	private static final String NFCTAGLOCATION_DROP = "DROP TABLE IF EXISTS " + NFCTAGLOCATION_TABLE;

	// Table: NFCTagReadTime
	public static final String NFCTAGREADTIME_TABLE = "NFCTagReadTime";
	public static final String NFCTAGREADTIME_COL_NFCTAGID = "nfcTagId";
	public static final String NFCTAGREADTIME_COL_READTIME = "readTime";
	private static final String NFCTAGREADTIME_CREATE = "CREATE TABLE" + NFCTAGREADTIME_TABLE + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NFCTAGREADTIME_COL_NFCTAGID + " BLOB NOT NULL, " + NFCTAGREADTIME_COL_READTIME + " DATETIME NOT NULL);";
	private static final String NFCTAGREADTIME_DROP = "DROP TABLE IF EXISTS " + NFCTAGREADTIME_TABLE;
	
	// Table: Bossmode
	public static final String BOSSMODE_TABLE = "Bossmode";
	public static final String BOSSMODE_COL_PASSWORD = "password";
	private static final String BOSSMODE_CREATE = "CREATE TABLE " + BOSSMODE_TABLE + " (" + COL_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + BOSSMODE_COL_PASSWORD + " BLOB NOT NULL);";
	private static final String BOSSMODE_DROP = "DROP TABLE IF EXISTS " + BOSSMODE_TABLE;
	
	public SQLiteConHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NFCTAGLOCATION_CREATE);
		db.execSQL(NFCTAGREADTIME_CREATE);
		db.execSQL(BOSSMODE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL(NFCTAGLOCATION_DROP);
	db.execSQL(NFCTAGREADTIME_DROP);
	db.execSQL(BOSSMODE_DROP);
	onCreate(db);
	}

}
