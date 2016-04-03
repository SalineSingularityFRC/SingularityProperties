package org.usfirst.frc.team5066.properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropFileLine {
	
	private LineType lineType;
	private String lineText;
	private Property prop;
	
	private Matcher m;
	
	PropFileLine(String lineText) {
		
		String commentPattern = "(\\s*)(#)(.*)";
		String propertyPattern = "(\\s*)(\\w*)(\\s*)([=:])(\\s*)(\\w*)(\\s*)";
		String propAndCommentPattern = propertyPattern + commentPattern;
		String whiteSpacePattern = "\\s*";
		
		this.lineText = lineText;
		//TODO write code to determine line type from line text -- use Regex
				
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
		if(lineType == LineType.PROPERTY ){
			
			//use substring to set name to the name and value to the value
		} else {
			prop = null;
		}
	}
	
	private boolean lineMatchesRegex(String regex) {
		return (m = (Pattern.compile(regex)).matcher(lineText)).matches();
	}
	
	public LineType getLineType() {
		return lineType;
	}
	
}
