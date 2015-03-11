package application;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class SavedListButton extends Button{

	private List<ShoppingItem> theList;
	
	public SavedListButton(String name, List<ShoppingItem> si){
		this.setText("    - " + name);
		this.setAlignment(Pos.CENTER_LEFT);
		
		theList = si;
		
		this.setStyle("-fx-base: #c3c3c3;");
	}
	
	public List<ShoppingItem> getList(){
		return this.theList;
	}
}
