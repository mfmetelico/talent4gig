package com.example.talent4gig;

import java.util.HashMap;
import java.util.Map;

public class DataOutputTotSource {

	private Long id;
	private String source;
	private double totValue;
	private  HashMap mTags = new HashMap();
	
	
	public DataOutputTotSource() {
	
	}
	/*
	public DataOutputTotSource(Long id, String source, long totValue, ArrayList<DataOutputTotSourceTag> tag) {
		super();
		this.id = id;
		this.source = source;
		this.totValue = totValue;
		this.tag = tag;
	}
*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public double getTotValue() {
		return totValue;
	}
	public void setTotValue(double totValue) {
		this.totValue = totValue;
	}


	public HashMap getTag() {
		return mTags;
	}
	
	
	public void setTag(String codTag, double value) {
		if ( mTags.get(codTag) == null) {
			mTags.put(codTag, value);
			
		} else {
			
			double lOldValue = (double) mTags.get(codTag); 
			mTags.replace(codTag, (lOldValue+value));
		}
	}
	
}
