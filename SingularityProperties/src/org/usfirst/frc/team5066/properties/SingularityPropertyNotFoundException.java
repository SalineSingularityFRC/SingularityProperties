package org.usfirst.frc.team5066.properties;

public class SingularityPropertyNotFoundException extends Exception {

	String propertyName;
	
	public SingularityPropertyNotFoundException(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
}
