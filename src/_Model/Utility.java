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
	public double getDouble(JFXTextField tf, double min, double max) {
		int caret = tf.getCaretPosition();
		String text = tf.getText();
			
		// eliminate all characters (with exeption[. e E])
		text = text.replaceAll("[^\\d[.eE-]]", "");										//https://regexr.com/
		
		// eliminate more than one point
		int countPoints = (int) text.codePoints().filter(ch -> ch =='.').count();
		for (int i = 1; i < countPoints; i++) {
			text = text.replaceAll("\\.(?!.*\\.)","");
		}
		
		// eliminate more than one E
		int count_E = (int) text.codePoints().filter(ch -> ch =='E').count();
		int count_e = (int) text.codePoints().filter(ch -> ch =='e').count();
		
		if (count_e >= 1 && count_E >= 1) {
			text = text.replaceAll("[^\\d[.e]]", "");
		} else if (count_E > 1) {
			for (int j = 1; j < count_E; j++) {
				text = text.replaceAll("E(?!.*E)","");
			}
		} else if (count_e > 1) {
			for (int k = 1; k < count_e; k++) {
				text = text.replaceAll("e(?!.*e)","");
			}
		}
		
		tf.setText(text);
		tf.positionCaret(caret);
		
		double res;
		try {
			res = Double.parseDouble(text);
		} catch (Exception e) {
			res = 2.1%0;		// NaN
		}
		
		if (res > max) {	tf.setText(""+max);	return max;	}
		if (res < min) {	tf.setText(""+min); return min; }
		return res;
	}
	
	public Integer getInt(JFXTextField tf, Integer min, Integer max) {
		int caret = tf.getCaretPosition();
		String text = tf.getText();
			
		// eliminate all characters (with exeption[. e E])
		text = text.replaceAll("[^\\d[-]]", "");										//https://regexr.com/
		
		tf.setText(text);
		tf.positionCaret(caret);
		
		Integer res = 0;
		try {
			res = Integer.parseInt(text);
			if (res > max) {	tf.setText(""+max);	tf.positionCaret(caret); return max;	}
			if (res < min) {	tf.setText(""+min); tf.positionCaret(caret); return min; }
		} catch (Exception e) {
			res = null;
		}
		
		return res;
	}
}
