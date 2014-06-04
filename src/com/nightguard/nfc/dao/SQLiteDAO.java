package com.nightguard.nfc.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

/**
 * Defines the methods, which are needed to handle the SQLite connection.
 * @author Patrick Spitzer
 *
 */
public interface SQLiteDAO<DataObject> {
	
	/**
	 * Opens the connection to the SQLite Database
	 * 
	 * @throws SQLiteException
	 */
	public void openConnection() throws SQLiteException;
	
	/**
	 * Closes the connection to the SQLite Database
	 */
	public void closeConnection();
	
	/**
	 * Parses the current Cursor Position to a Data Object
	 * 
	 * @param cursor
	 * @return The parsed Data Object
	 */
	public DataObject cursorPosToDataObject(Cursor cursor);

}
