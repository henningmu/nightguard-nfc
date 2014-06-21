package com.nightguard.nfc.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class NFCTagReadTimeDAO implements CRUDDAO<NFCTagReadTime>, SQLiteDAO<NFCTagReadTime>{
	private SimpleDateFormat dateFormat = new SimpleDateFormat(SQLiteConHelper.DB_DATETIME_FORMAT);
	
	private SQLiteConHelper dbHelper;
	private SQLiteDatabase database;

	public NFCTagReadTimeDAO(Context context) {
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
	public NFCTagReadTime cursorPosToDataObject(Cursor cursor) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(SQLiteConHelper.DB_DATETIME_FORMAT);
		NFCTagReadTime dataObject = new NFCTagReadTime();
		int _idIndex = cursor.getColumnIndex(SQLiteConHelper.COL_ID);
		int nfcTagIdIndex = cursor.getColumnIndex(SQLiteConHelper.NFCTAGREADTIME_COL_NFCTAGID);
		int readTimeIndex = cursor.getColumnIndex(SQLiteConHelper.NFCTAGREADTIME_COL_READTIME);
		
		dataObject.set_id(cursor.getInt(_idIndex));
		dataObject.setNfcTagId(cursor.getBlob(nfcTagIdIndex));
		Date readTime = null;
		try {
			readTime = dateFormat.parse(cursor.getString(readTimeIndex));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dataObject.setReadTime(readTime);
		return dataObject;
	}

	@Override
	public NFCTagReadTime create(NFCTagReadTime dataObject) {
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.NFCTAGREADTIME_COL_NFCTAGID, dataObject.getNfcTagId());
		cv.put(SQLiteConHelper.NFCTAGREADTIME_COL_READTIME, dateFormat.format(dataObject.getReadTime()));
		Long longid = database.insert(SQLiteConHelper.NFCTAGREADTIME_TABLE, null, cv);
		dataObject.set_id(longid.intValue());
		return dataObject;
	}

	/**
	public NFCTagReadTime read(NFCTagReadTime dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		Cursor cursor = database.query(SQLiteConHelper.NFCTAGREADTIME_TABLE, null, where, null, null, null, null, null);
		if (cursor.moveToFirst() == false){
			return null;
		}
		
		cursorPosToDataObject(cursor);
		return dataObject;
	}*/

	@Override
	public List<NFCTagReadTime> readAll() {
		Cursor cursor = database.query(SQLiteConHelper.NFCTAGREADTIME_TABLE, null, null, null, null, null, null, null);
		List<NFCTagReadTime> dataList = new ArrayList<NFCTagReadTime>();
		
		while (cursor.moveToNext()) {
			dataList.add(cursorPosToDataObject(cursor));
		}
		
		return  dataList;
		
	}

	@Override
	public void update(NFCTagReadTime dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.NFCTAGREADTIME_COL_NFCTAGID, dataObject.getNfcTagId());
		cv.put(SQLiteConHelper.NFCTAGREADTIME_COL_READTIME, dateFormat.format(dataObject.getReadTime()));
		database.update(SQLiteConHelper.NFCTAGREADTIME_TABLE, cv, where, null);
	}

	@Override
	public void delete(NFCTagReadTime dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		database.delete(SQLiteConHelper.NFCTAGREADTIME_TABLE, where, null);
		
	}


		
		
	

}
