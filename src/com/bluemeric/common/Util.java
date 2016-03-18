package com.bluemeric.common;

import java.io.File;
import java.util.Arrays;

public class Util {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] directories = readDirectories("F:/DemoWorkspace/demotest/config");
		for ( int dir = 0; dir < directories.length; dir++){
		System.out.println(directories[dir].toString());
		}	
	}

	public static String[] readDirectories(String dir){
		File file = new File(dir);
		String[] directories = file.list();
		System.out.println(Arrays.toString(directories));
		return directories;	
	}
}
