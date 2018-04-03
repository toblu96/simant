package _Model;

import com.jfoenix.controls.JFXTextField;

import javafx.stage.Screen;

public class Utility {

	private static final double height = Screen.getPrimary().getVisualBounds().getHeight(); // Screen resolution height
	private static final double width = Screen.getPrimary().getVisualBounds().getWidth();	// Screen resolution width
	
	// get height percentage from screen resolution
	public double getScreenHeightPercentage(double per) {
		return height / 100 * per;
	}
	
	// get width percentage from screen resolution
	public double getScreenWidthPercentage(double per) {
		return width / 100 * per;
	}
	
	// only number input from text-field (double)
	public void onlyNumber(JFXTextField tf, double bottom, double top) {
		int caret = tf.getCaretPosition();
		String text = tf.getText();
		if (!text.matches("^\\d+(\\.)?(\\d+)?")) {
			tf.setText(text.replaceAll("[^\\d\\.]", ""));	//https://regexr.com/
        }
		if (!text.matches("^[^.]*.[^.]*$")) {
			tf.setText(text.replaceAll("(.)\\1+", "."));
		}
		tf.positionCaret(caret);
	}
}
