package com.example.talent4gig;

import java.util.ArrayList;

public class DataInput {

	private String source;
	private float value;
	private ArrayList<String> tags = new ArrayList<>();

	
	public DataInput() {}
	
	public DataInput(String source, float value, ArrayList<String> tags) {
		super();
		this.source = source;
		this.value = value;//Float.valueOf(value);
		this.tags = tags;
	}
	
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTag(ArrayList<String> item) {
		this.tags = item;
	}


}

