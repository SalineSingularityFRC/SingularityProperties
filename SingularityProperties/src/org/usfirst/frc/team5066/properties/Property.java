package org.usfirst.frc.team5066.properties;

public class Property {
	private String propName;
	private String propValue;
	
	public Property(String propName, String propValue) {
		this.propName = propName;
		this.propValue = propValue;
	}
	
	public void setName(String propName) {
		this.propName = propName;
	}
	
	public String getName() {
		return propName;
	}
	
	public void setValue(String propValue) {
		this.propValue = propValue;
	}
	
	public String getValue() {
		return propValue;
	}
	
}
