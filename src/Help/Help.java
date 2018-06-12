package Help;

import com.jfoenix.controls.JFXButton;

import _MenuView.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Help {
	
	MenuView view;
	
	@FXML
	AnchorPane ap_hintergrund;
	
	@FXML
	ScrollPane sp_scroll;
	
	@FXML
	JFXButton btn_antenne, btn_layout, btn_diagramm, btn_xml;
	
	@FXML
	ImageView img_1, img_2, img_3, img_4, img_5, img_6;
		
	
	/**
	 * - setzt Referenz der Hauptview in Attribut
	 * 
	 * @param view	-> Referenz auf HauptView
	 */
	public void setParentView(MenuView view) {
		this.view = view;
		
		ap_hintergrund.widthProperty().addListener((obs, oldVal, newVal) -> {		
			if(Math.abs(oldVal.doubleValue() - newVal.doubleValue()) > 5) redraw();	});
	}
	
	@FXML
	private void buttonAction(ActionEvent e) {
		if (e.getSource().equals(btn_antenne)) {
			sp_scroll.setVvalue(0.0);
		} else if (e.getSource().equals(btn_layout)) {
			sp_scroll.setVvalue(0.165);
		} else if (e.getSource().equals(btn_diagramm)) {
			sp_scroll.setVvalue(0.715);
		} else if (e.getSource().equals(btn_xml)) {
			sp_scroll.setVvalue(0.95);
		}
		
	}
	
	private void redraw() {
		img_1.setFitWidth(ap_hintergrund.getWidth()*0.95);
		img_2.setFitWidth(ap_hintergrund.getWidth()*0.95);
		img_3.setFitWidth(ap_hintergrund.getWidth()*0.95);
		img_4.setFitWidth(ap_hintergrund.getWidth()*0.95);
		img_5.setFitWidth(ap_hintergrund.getWidth()*0.95);
		img_6.setFitWidth(ap_hintergrund.getWidth()*0.95);
	}
}
