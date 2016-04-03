package org.usfirst.frc.team5066.testing;

import java.io.File;
import java.io.IOException;

import org.usfirst.frc.team5066.properties.SingularityProperties;

public class Main {
	
	String sep = File.separator;
	SingularityProperties sp;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
			sp = new SingularityProperties("C:" + sep + "Users" + sep + "Michael" + sep + "Desktop" + sep + "robot.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
