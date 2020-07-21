package com.example.talent4gig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataOutput {

	@XmlElement(name="id")
	public Long id;
	
	@XmlElement(name="status")
	public String status;
	
	
	public DataOutput() {
	}
	
	@Override
	public String toString() {
		return "DataOutput [id=" + id + ", status=" + status + "]";
	}

	public DataOutput(Long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
