package com.nightguard.nfc.dao;


import java.util.List;

/**
 * Interface for DAO Stanard Methods: create, read, update, delete
 * @author Patrick Spitzer
 *
 * @param <DataObject> The plain data object which represents the table content
 */
public interface CRUDDAO<DataObject> {

	public DataObject create(DataObject dataObject);

	public List<DataObject> readAll();

	public void update(DataObject dataObject);

	public void delete(DataObject dataObject);

}
