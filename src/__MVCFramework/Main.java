package __MVCFramework;
	
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
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// set application icon and text
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/Icon.png")));
			primaryStage.setTitle("SIMANT Simulation Tool");
			
			//Load FXML
			AnchorPane root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/_MenuView/MenuView.fxml"));
			double height = util.getScreenHeightPercentage(80);
			double width = util.getScreenWidthPercentage(80);
			Scene scene = new Scene(root,width,height);
			scene.getStylesheets().add(Main.class.getResource("/_MenuView/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMinHeight(util.getScreenHeightPercentage(66));
			primaryStage.setMinWidth(util.getScreenWidthPercentage(16.5));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
