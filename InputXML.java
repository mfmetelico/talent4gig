package com.example.talent4gig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "input")
public class InputXML {

	private Long id;
	private String source;
	private int value;
	private String tag1;
	private String tag2;
	private String tag3;
	
	
	public InputXML() {
	}
	
	public InputXML(Long id, int value, String tag1, String tag2, String tag3) {
		super();
		this.id = id;
		this.value = value;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name = "source")
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "value") //, nullable = false)
    public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Column(name = "tag1")
	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	@Column(name = "tag2")
	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	@Column(name = "tag3")
	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	
	
}
