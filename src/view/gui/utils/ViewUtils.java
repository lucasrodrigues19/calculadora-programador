package view.gui.utils;

import javafx.scene.control.ListView;

public class ViewUtils {

	public static Integer tryParseInt(String num) {
		try {
			return Integer.parseInt(num);
		}catch(Exception e) {
			return null;
		}
	}

	public static Double tryParseDouble(String num) {
		try {
			return Double.parseDouble(num);
		}catch(Exception e) {
			return null;
		}
	}
	
}
