package com.shangyong.backend.entity.approval;

import java.io.Serializable;

public class ContactInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String index;
	
	private String Contact;
	
	private String ContactCount;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getContactCount() {
		return ContactCount;
	}

	public void setContactCount(String contactCount) {
		ContactCount = contactCount;
	}
	
	

}
