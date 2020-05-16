package utils;


public class CalculadoraUtils {

	public static Integer tryParseInt(String num) {
		try {
			return Integer.parseInt(num);
		}catch(Exception e) {
			return null;
		}
	}
}
