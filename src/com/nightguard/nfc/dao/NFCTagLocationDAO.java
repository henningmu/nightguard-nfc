package com.nightguard.nfc.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class NFCTagLocationDAO implements CRUDDAO<NFCTagLocation>, SQLiteDAO<NFCTagLocation>{
	
	private SQLiteConHelper dbHelper;
	private SQLiteDatabase database;
	
	
	
	public NFCTagLocationDAO(Context context) {
		super();
		dbHelper = new SQLiteConHelper(context);
	}

	@Override
	public void openConnection() throws SQLiteException {
		database = dbHelper.getWritableDatabase();
	}

	@Override
	public void closeConnection() {
		dbHelper.close();
	}

	@Override
	public NFCTagLocation cursorPosToDataObject(Cursor cursor) {
		NFCTagLocation dataObject = new NFCTagLocation();
		int _idIndex = cursor.getColumnIndex(SQLiteConHelper.COL_ID);
		int nfcTagIdIndex = cursor.getColumnIndex(SQLiteConHelper.NFCTAGLOCATION_COL_NFCTAGID);
		int locationIndex = cursor.getColumnIndex(SQLiteConHelper.NFCTAGLOCATION_COL_LOCATION);
		
		dataObject.set_id(cursor.getInt(_idIndex));
		dataObject.setNfcTagId(cursor.getBlob(nfcTagIdIndex));
		dataObject.setLocation(cursor.getString(locationIndex));
		return dataObject;
		
	}

	@Override
	public NFCTagLocation create(NFCTagLocation dataObject) {
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.NFCTAGLOCATION_COL_NFCTAGID, dataObject.getNfcTagId());
		cv.put(SQLiteConHelper.NFCTAGLOCATION_COL_LOCATION, dataObject.getLocation());
		Long longid = database.insert(SQLiteConHelper.NFCTAGLOCATION_TABLE, null, null);
		dataObject.set_id(longid.intValue());
		return dataObject;
	}

	@Override
	public List<NFCTagLocation> readAll() {
		Cursor cursor = database.query(SQLiteConHelper.NFCTAGLOCATION_TABLE, null, null, null, null, null, null, null);
		List<NFCTagLocation> dataList = new ArrayList<NFCTagLocation>();
		
		while (cursor.moveToNext()) {
			dataList.add(cursorPosToDataObject(cursor));
		}
		
		return  dataList;
	}

	@Override
	public void update(NFCTagLocation dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.NFCTAGLOCATION_COL_NFCTAGID, dataObject.getNfcTagId());
		cv.put(SQLiteConHelper.NFCTAGLOCATION_COL_LOCATION, dataObject.getLocation());
		database.update(SQLiteConHelper.NFCTAGLOCATION_TABLE, cv, where, null);
		
	}

	@Override
	public void delete(NFCTagLocation dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		database.delete(SQLiteConHelper.NFCTAGREADTIME_TABLE, where, null);
		
	}

}
