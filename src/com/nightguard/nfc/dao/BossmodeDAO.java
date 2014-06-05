package com.nightguard.nfc.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class BossmodeDAO implements CRUDDAO<Bossmode>, SQLiteDAO<Bossmode> {

	private SQLiteConHelper dbHelper;
	private SQLiteDatabase database;

	public BossmodeDAO(Context context) {
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
	public Bossmode cursorPosToDataObject(Cursor cursor) {
		Bossmode dataObject = new Bossmode();
		int _idIndex = cursor.getColumnIndex(SQLiteConHelper.COL_ID);
		int passwordIndex = cursor.getColumnIndex(SQLiteConHelper.BOSSMODE_COL_PASSWORD);

		dataObject.set_id(cursor.getInt(_idIndex));
		dataObject.setPassword(cursor.getString(passwordIndex));

		return dataObject;
	}

	@Override
	public Bossmode create(Bossmode dataObject) {
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.BOSSMODE_COL_PASSWORD, dataObject.getPassword());
		Long longid = database.insert(SQLiteConHelper.BOSSMODE_TABLE, null, null);
		dataObject.set_id(longid.intValue());
		return dataObject;
	}

	@Override
	public List<Bossmode> readAll() {
		Cursor cursor = database.query(SQLiteConHelper.BOSSMODE_TABLE, null, null, null, null, null, null, null);
		List<Bossmode> dataList = new ArrayList<Bossmode>();

		while (cursor.moveToNext()) {
			dataList.add(cursorPosToDataObject(cursor));
		}

		return dataList;
	}

	@Override
	public void update(Bossmode dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		ContentValues cv = new ContentValues();
		cv.put(SQLiteConHelper.BOSSMODE_COL_PASSWORD, dataObject.getPassword());

		database.update(SQLiteConHelper.BOSSMODE_TABLE, cv, where, null);

	}

	@Override
	public void delete(Bossmode dataObject) {
		String where = SQLiteConHelper.COL_ID + " = " + dataObject.get_id();
		database.delete(SQLiteConHelper.BOSSMODE_TABLE, where, null);

	}

}
