package com.example.talent4gig;

import javax.xml.bind.annotation.XmlElement;

public class ErrorsInput {

	@XmlElement(name="cod")
	private String sCod;
	
	@XmlElement(name="desc")
	private String sDesc;
	
	public ErrorsInput() {
	}
	
	public ErrorsInput(String sCod, String sDesc) {
		super();
		this.sCod = sCod;
		this.sDesc = sDesc;
	}

	public String getsCod() {
		return sCod;
	}

	public void setsCod(String sCod) {
		this.sCod = sCod;
	}

	public String getsDesc() {
		return sDesc;
	}

	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}
	
	
}
