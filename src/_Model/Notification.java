package _Model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Notification {

	private static AnchorPane rootPane;
	private static VBox vBox = new VBox();
	static Pane tempPane;
	
	public static void setRootPane(AnchorPane pane) {
		rootPane = pane;
		AnchorPane.setRightAnchor(vBox, 20.0);
		AnchorPane.setBottomAnchor(vBox, 10.0);
		vBox.setSpacing(10.0);
		rootPane.getChildren().add(vBox);
	}
	
	public static void error(String msg) {
		Pane pane = new Pane();
		tempPane = pane;
		
		Timer timer = new Timer();
		
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        vBox.getChildren().remove(pane);
		        timer.cancel();
		    }
		});
		
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	Platform.runLater(() -> {
            		vBox.getChildren().remove(0);
            	});
            	timer.cancel();
            }
        }, 4000, 4000);
		
		pane.setPrefWidth(200);
		pane.setPrefHeight(100);
		pane.setStyle("-fx-background-radius: 10; "
					+ "-fx-background-color: rgba(255, 0, 0, .5);"
					+ "-fx-padding: 15;");
		
		ImageView img = new ImageView();
		img.setImage(new Image("/resources/error.png"));
		img.setFitWidth(30); img.setFitHeight(30);
		img.setX(10); img.setY(35);
		pane.getChildren().add(img);
		
		Text text = new Text(msg);
		text.setFont(Font.font("Segoe UI", 15));
		text.setLayoutX(50);
		text.setLayoutY(40);
		text.setWrappingWidth(140);
		pane.getChildren().add(text);
		
		vBox.getChildren().add(pane);
	}
	
	public static void info(String msg) {
		Pane pane = new Pane();
		tempPane = pane;
		
		Timer timer = new Timer();
		
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        vBox.getChildren().remove(pane);
		        timer.cancel();
		    }
		});
		
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	Platform.runLater(() -> {
            		vBox.getChildren().remove(0);
            	});
            	timer.cancel();
            }
        }, 4000, 4000);
		
		pane.setPrefWidth(200);
		pane.setPrefHeight(100);
		pane.setStyle("-fx-background-radius: 10; "
					+ "-fx-background-color: rgba(0, 97, 255, .5);"
					+ "-fx-padding: 15;");
		
		ImageView img = new ImageView();
		img.setImage(new Image("/resources/info.png"));
		img.setFitWidth(30); img.setFitHeight(30);
		img.setX(10); img.setY(35);
		pane.getChildren().add(img);
		
		Text text = new Text(msg);
		text.setFont(Font.font("Segoe UI", 15));
		text.setLayoutX(50);
		text.setLayoutY(40);
		text.setWrappingWidth(140);
		pane.getChildren().add(text);
		
		vBox.getChildren().add(pane);
	}
	
	public static void success(String msg) {
		Pane pane = new Pane();
		tempPane = pane;
		
		Timer timer = new Timer();
		
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent t) {
		        vBox.getChildren().remove(pane);
		        timer.cancel();
		    }
		});
		
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	Platform.runLater(() -> {
            		vBox.getChildren().remove(0);
            	});
            	timer.cancel();
            }
        }, 4000, 4000);
		
		pane.setPrefWidth(200);
		pane.setPrefHeight(100);
		pane.setStyle("-fx-background-radius: 10; "
					+ "-fx-background-color: rgba(0, 255, 0, .5);"
					+ "-fx-padding: 15;");
		
		ImageView img = new ImageView();
		img.setImage(new Image("/resources/success.png"));
		img.setFitWidth(30); img.setFitHeight(30);
		img.setX(10); img.setY(35);
		pane.getChildren().add(img);
		
		Text text = new Text(msg);
		text.setFont(Font.font("Segoe UI", 15));
		text.setLayoutX(50);
		text.setLayoutY(40);
		text.setWrappingWidth(140);
		pane.getChildren().add(text);
		
		vBox.getChildren().add(pane);
	}
}
