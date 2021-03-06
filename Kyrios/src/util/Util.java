package util;

import java.io.File;
import java.io.FileWriter;

public class Util {

	public static float interpolate(float a, float b, double f) {
		return (float) ((a * (1.0 - f)) + (b * f));
	}
	
	public static boolean writeToFile(File file, String text) {
		try {
			FileWriter writer = new FileWriter(file.getPath());
			writer.write(text);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean writeToFile(String path, String text) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(text);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
