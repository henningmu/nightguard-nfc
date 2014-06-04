package com.nightguard.nfc.dao;

import java.util.Date;

public class NFCTagReadTime {
	private int _id;
	private byte[] nfcTagId;
	private Date readTime;
	
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
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	

}
