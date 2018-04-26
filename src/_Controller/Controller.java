package _Controller;
import _Model.Model;
import _Model.SimantInputData;
import _MenuView.MenuView;

public class Controller {

	Model model;
	MenuView menuView;

	public Controller(Model model, MenuView view) {
		this.model = model;
		this.menuView = view;
	}
	
	public void setInputData(SimantInputData data) {
		model.updateInputData(data);
	}
	
}
