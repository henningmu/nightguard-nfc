package com.nightguard.nfc.dao;

public class NFCTagLocation {
	private int _id;
	private byte[] nfcTagId;
	private String location;
	
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public byte[] getNfcTagId() {
		return nfcTagId;
	}
	public void setNfcTagId(byte[] nfcTagId) {
		this.nfcTagId = nfcTagId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
