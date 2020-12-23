package util;

public class Util {

	public static float interpolate(float a, float b, double f) {
		return (float) ((a * (1.0 - f)) + (b * f));
	}
	
}
