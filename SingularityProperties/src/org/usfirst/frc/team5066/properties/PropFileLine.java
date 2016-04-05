package org.usfirst.frc.team5066.properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropFileLine {
	
	private LineType lineType;
	private String lineText;
	private Property prop;
	
	private Matcher m;
	
	PropFileLine(String lineText) {
		
		final String commentPattern = "(\\s*)(#)(.*)";
		final String propertyPattern = "(\\s*)(\\w*)(\\s*)([=:])(\\s*)(\\w*)(\\s*)";
		final String propAndCommentPattern = propertyPattern + commentPattern;
		final String whiteSpacePattern = "\\s*";
		
		this.lineText = lineText;
				
		//Determine the line's type
		if(lineMatchesRegex(propertyPattern)){
			lineType = LineType.PROPERTY;
		} else if(lineMatchesRegex(commentPattern)) {
			lineType = LineType.COMMENT;
		} else if(lineMatchesRegex(whiteSpacePattern)) {
			lineType = LineType.WHITESPACE;
		} else if(lineMatchesRegex(propAndCommentPattern)) {
			lineType = LineType.PROP_AND_COMMENT;
		} else {
			lineType = LineType.BROKEN;
		}
		
		//set property only if this a PROPERTY type line
		if(lineType == LineType.PROPERTY || lineType == LineType.PROP_AND_COMMENT){
			
			Pattern p = Pattern.compile("\\w*");
			m = p.matcher(lineText);
			
			/*TODO the find() method is not working as expected. Manually use split() and
			 * other String methods to get the nameand value substrings 
			 */
			String temp = lineText.replaceAll("(\\s+)|(#)(.*)", "");
			//System.out.println("========================" + temp);
			temp.split("=|:");
			
			prop = new Property(temp.split("=|:")[0], temp.split("=|:")[1]);
			/*
			System.out.println("============== Property Name: " + prop.getName());
			System.out.println("============== Property Value: " + prop.getValue());
			*/
			//use substring to set name to the name and value to the value
			
		} else {
			prop = null;
		}
	}
	
	private boolean lineMatchesRegex(String regex) {
		return (m = (Pattern.compile(regex)).matcher(lineText)).matches();
	}
	
	public String getLineText() {
		return lineText;
	}
	
	//returns true if successful, false if the line isn't a property
	public boolean setProperty(String newValue) {
		if(lineType == LineType.PROPERTY || lineType == LineType.PROP_AND_COMMENT) {
		prop.setValue(newValue);
		
		//Assuming that the string at index 1 will always be the value string
		String currentValue = lineText.split("=|:|#")[1].replaceAll("\\s", "");
		System.out.println("Current value: " + currentValue);
		//TODO find a way to do this that might not accidentally replace something else... there is a chance that the property name might be the same as the volume
		lineText = lineText.replace(currentValue, newValue);
		System.out.println(lineText);
		
		return true;
		} else {
			return false;
		}
	}
	
	/* Does not work properly -- don't us anymore
	 * 
	private String getSubStringFromMatcher(Matcher m) {
		System.out.println(m.find());
		return (lineText.substring(m.start(), m.end()));
	}
	*/
	
	public Property getProperty() {
		return prop;
	}
	
	public LineType getLineType() {
		return lineType;
	}
	
}
