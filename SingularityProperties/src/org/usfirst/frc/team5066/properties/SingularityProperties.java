package org.usfirst.frc.team5066.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Class for reading and writing to properties files.
 * 
 * @author Saline Singularity 5066
 * 
 * @version 2.1
 * Now with properties-file writing functionality that actually works well
 */

public class SingularityProperties {

	private Properties defaultProps;
	private Properties props;
	private String propFileURL;
	
	//define lists necessary to re-write file at writing time
	private ArrayList changedProperties;
	private ArrayList<PropFileLine> propFileLines;
	
	/**
	 * Constructor for SingularityPropsReader
	 * 
	 * @param propFileURL
	 *            Which file to use as the properties file
	 * @throws IOException
	 *             If the file is invalid or lacks read access
	 */
	public SingularityProperties(String propFileURL) throws IOException {

		changedProperties = new ArrayList();
		propFileLines = new ArrayList<PropFileLine>();
		
		this.propFileURL = propFileURL;
		props = readProperties(propFileURL);
		defaultProps = new Properties();
		
	}

	/*
	public SingularityProperties() throws IOException{
		this.propFileURL = null;
		defaultProps = new Properties();
	}
	*/

	/**
	 * Used to get the file URL
	 * 
	 * @return Which file is being read
	 */
	public String getFileURL() {
		return propFileURL;
	}

	/**
	 * Used to add a property to the default properties. A default property is
	 * used when a property fails to load from the properties file. Default
	 * properties are only backups in case a true property fails to load.
	 * 
	 * @param propName
	 *            The name of the default property. Must match the name of the
	 *            property in the properties file that this value is the default
	 *            for
	 * @param value
	 *            The value to which the default property will be set The value
	 *            to which the default property will be set
	 */
	public void addDefaultProp(String propName, int value) {
		defaultProps.setProperty(propName, "" + value);
	}

	/**
	 * Used to add a property to the default properties. A default property is
	 * used when a property fails to load from the properties file. Default
	 * properties are only backups in case a true property fails to load.
	 * 
	 * @param propName
	 *            The name of the default property. Must match the name of the
	 *            property in the properties file that this value is the default
	 *            for
	 * @param value
	 *            The value to which the default property will be set
	 */
	public void addDefaultProp(String propName, double value) {
		defaultProps.setProperty(propName, "" + value);
	}

	/**
	 * Used to add a property to the default properties. A default property is
	 * used when a property fails to load from the properties file. Default
	 * properties are only backups in case a true property fails to load.
	 * 
	 * @param propName
	 *            The name of the default property. Must match the name of the
	 *            property in the properties file that this value is the default
	 *            for
	 * @param value
	 *            The value to which the default property will be set
	 */
	public void addDefaultProp(String propName, float value) {
		defaultProps.setProperty(propName, "" + value);
	}

	/**
	 * Used to add a property to the default properties. A default property is
	 * used when a property fails to load from the properties file. Default
	 * properties are only backups in case a true property fails to load.
	 * 
	 * @param propName
	 *            The name of the default property. Must match the name of the
	 *            property in the properties file that this value is the default
	 *            for
	 * @param value
	 *            The value to which the default property will be set
	 */
	public void addDefaultProp(String propName, boolean value) {
		defaultProps.setProperty(propName, "" + value);
	}

	/**
	 * Used to add a property to the default properties. A default property is
	 * used when a property fails to load from the properties file. Default
	 * properties are only backups in case a true property fails to load.
	 * 
	 * @param propName
	 *            The name of the default property. Must match the name of the
	 *            property in the properties file that this value is the default
	 *            for
	 * @param value
	 *            The value to which the default property will be set
	 */
	public void addDefaultProp(String propName, String value) {
		if (defaultProps.containsKey(propName)) {
			DriverStation.reportError("Default property with name \"" + propName + "\n aready exists... Overwriting",
					false);
		}
		defaultProps.setProperty(propName, value);
	}

	/**
	 * Rereads file properties
	 * 
	 * @throws IOException
	 *             If the file is invalid or lacks read access
	 */
	public void reloadProperties() throws IOException {
		props = readProperties(propFileURL);
	}

	/**
	 * Actually reads the property file
	 * 
	 * @param propFileURL
	 *            Which file to read
	 * @return An object which encapsulates the properties found in the given
	 *         file
	 * @throws IOException
	 *             If the file lacks read access
	 */
	private Properties readProperties(String propFileURL) throws IOException {
		
		//TODO Test all of this!
		
		Properties prop = new Properties();
		File f = new File(propFileURL);
		FileInputStream fileInputStream;

		fileInputStream = new FileInputStream(f);
		prop.load(fileInputStream);
		
		fileInputStream.close();
		//Begin new code
		
		String line;
		
		//must re-create the FileInputStream or else the reader will start at the end of the file
		fileInputStream = new FileInputStream(f);
		
		//TODO 
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
		while ((line = br.readLine()) != null) {
			PropFileLine lineObject = new PropFileLine(line);
			propFileLines.add(lineObject);
			System.out.print(line);
			System.out.println("   <-- " + lineObject.getLineType());
		}
		
		br.close();
		//fileInputStream.close();

		
		return prop;
	}
	

	/**
	 * Sets a certain property in the file to a certain object
	 * 
	 * @param propName
	 *            Which property to change
	 * @param o
	 *            What to change it to (uses the {@code .toString()} method)
	 * @throws IOException
	 *             If file is not valid or does not allow write access
	 */
	public void setProperty(String propName, Object o) throws IOException {
		
		//TODO fix this method. Right now it saves all Properties to the file in a pattern that
		//is in no particular order. It also delets all comments and original spacing.
		
		/*TODO Replace the whole "props.store()" method with a method where the code iterates through all
		 * Property lines in the list of file lines until it find the one that needs to be changed, changes
		 * it to what it needs to be, and then saves the entire list of properties to the properties file.
		 */
		
		//load the properties file so that direct user changes to the file are not overwritten
		reloadProperties();
		
		FileOutputStream out = new FileOutputStream(propFileURL);

		props.setProperty(propName, o.toString());
		props.store(out, null);
		out.close();

		/*
		 * TODO See if calling this over and over again creates lag. If it does,
		 * a solution might be to separate the reloadProperties() method into
		 * two segments. We only really want the part that reads the file to the
		 * Properties object, not the one that totally reloads the array of
		 * lines (we should already have that)
		 */
		reloadProperties();
	}

	/**
	 * Method to find access a certain string in the properties file
	 * 
	 * @param name
	 *            Which string to get
	 * @return The value of the string
	 * @throws SingularityPropertyNotFoundException
	 */
	public String getString(String name) throws SingularityPropertyNotFoundException {
		return loadProperty(name);
	}

	/**
	 * Method to find access a certain integer in the properties file
	 * 
	 * @param name
	 *            Which integer to get
	 * @return The value of the integer
	 * @throws SingularityPropertyNotFoundException
	 * @throws NumberFormatException
	 */
	public int getInt(String name) throws NumberFormatException, SingularityPropertyNotFoundException {
		return Integer.parseInt(loadProperty(name));
	}

	/**
	 * Method to find access a certain float in the properties file
	 * 
	 * @param name
	 *            Which float to get
	 * @return The value of the float
	 * @throws SingularityPropertyNotFoundException
	 * @throws NumberFormatException
	 */
	public float getFloat(String name) throws NumberFormatException, SingularityPropertyNotFoundException {
		return Float.parseFloat(loadProperty(name));
	}

	/**
	 * Method to find access a certain double in the properties file
	 * 
	 * @param name
	 *            Which double to get
	 * @return The value of the double
	 * @throws SingularityPropertyNotFoundException
	 * @throws NumberFormatException
	 */
	public double getDouble(String name) throws NumberFormatException, SingularityPropertyNotFoundException {
		return Double.parseDouble(loadProperty(name));
	}

	/**
	 * Method to find access a certain boolean in the properties file
	 * 
	 * @param name
	 *            Which boolean to get
	 * @return The value of the boolean
	 * @throws SingularityPropertyNotFoundException
	 */
	public boolean getBoolean(String name) throws SingularityPropertyNotFoundException {
		return Boolean.parseBoolean(loadProperty(name));
	}

	private String loadProperty(String name) throws SingularityPropertyNotFoundException {
		String prop;
		if (propFileURL != null) {
			prop = props.getProperty(name);
		} else {
			prop = null;
		}
		// TODO - accomplish this better with a do{} - while()?
		if (prop != null) {
			return prop;
		} else {
			// Note - all messages such as the following are automatically
			// logged by DriverStation
			DriverStation.reportError("Failed to find property in file: " + name
					+ "\n - Resorting to default property for " + name + "\n \n .", false);
			prop = defaultProps.getProperty(name);
			if (prop != null) {
				return prop;
			} else {
				// Note - all messages such as the following are automatically
				// logged by DriverStation
				DriverStation.reportError(
						"Failed to find property in defaults: " + name + "\n - THROWING EXCEPTION!!! \n \n ...", false);

				SingularityPropertyNotFoundException spnfe = new SingularityPropertyNotFoundException(name);
				throw spnfe;
			}
		}

		// TODO make it so that all properties that are missing can be found and
		// pointed out if using a single loadProperties() method.
	}
}
