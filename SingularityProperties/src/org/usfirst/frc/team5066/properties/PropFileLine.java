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
		if(lineType == LineType.PROPERTY ){
			
			Pattern p = Pattern.compile("\\w*");
			m = p.matcher(lineText);
			
			/*TODO the find() method is not working as expected. Manually use split() and
			 * other String methods to get the nameand value substrings 
			 */
			String temp = lineText.replaceAll("\\s+", "");
			temp.split("=|:");
			
			prop = new Property(temp.split("=|:")[0], temp.split("=|:")[1]);
			System.out.println("============== Property Name: " + prop.getName());
			System.out.println("============== Property Value: " + prop.getValue());
			//use substring to set name to the name and value to the value
		} else {
			prop = null;
		}
	}
	
	private boolean lineMatchesRegex(String regex) {
		return (m = (Pattern.compile(regex)).matcher(lineText)).matches();
	}
	
	/* Does not work properly
	 * 
	private String getSubStringFromMatcher(Matcher m) {
		System.out.println(m.find());
		return (lineText.substring(m.start(), m.end()));
	}
	*/
	
	public LineType getLineType() {
		return lineType;
	}
	
}
