package view.gui.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ViewUtils {

	public static Integer tryParseInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return null;
		}
	}

	public static Double tryParseDouble(String num) {
		try {
			return Double.parseDouble(num);
		} catch (Exception e) {
			return null;
		}
	}

	public static void setColorItemsComboBox(ComboBox<String> cmb, String background_color, String text_fill_color) {
		Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				
				super.updateItem(item, empty);
				if(empty || item == null)
					setStyle("-fx-text-fill:"+text_fill_color+"; -fx-background-color:"+background_color);
				else {
					setStyle("-fx-text-fill:"+text_fill_color+"; -fx-background-color:"+background_color);
					setText(item.toString());
				}
			}
		};
		cmb.setCellFactory(factory);
		cmb.setButtonCell(factory.call(null));
	
	}

}
