package _Model;

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
}
