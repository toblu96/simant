package __MVCFramework;

import java.io.IOException;

import _MenuView.MenuView;
import _Model.Notification;
import _Model.Utility;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

// Edited by Tobias
public class Main extends Application {
	
	private Utility util = new Utility();
	
	private Stage primaryStage;
	private AnchorPane menuPane;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		// set application icon and text
		this.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/Icon.png")));
		this.primaryStage.setTitle("SIMANT Simulation Tool");
		
		initMenuLayout();
		
		
	}
	
	
	public void initMenuLayout() {
        try {
            // Load menu layout from fxml file.
        	FXMLLoader menuViewLoader = new FXMLLoader(getClass().getResource("/_MenuView/MenuView.fxml"));
        	// set Controller
        	MenuView menuViewController = new MenuView();
			menuViewLoader.setController(menuViewController);
			
			menuPane = (AnchorPane) menuViewLoader.load();
			
			Notification.setRootPane(menuPane);
			
            // Show the scene containing the root layout.
			double height = util.getScreenHeightPercentage(80);
			double width = util.getScreenWidthPercentage(80);
            Scene scene = new Scene(menuPane,width,height);
            scene.getStylesheets().add(Main.class.getResource("/_MenuView/application.css").toExternalForm());
            primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinHeight(util.getScreenHeightPercentage(80));
			primaryStage.setMinWidth(util.getScreenWidthPercentage(20));
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
