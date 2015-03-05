package application;

import java.util.List;

import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.scene.control.Button;

public class SubcategoryButton extends Button{
	private List<ShoppingItem> theList;
	
	public SubcategoryButton(String name, List<ShoppingItem> theProductList){
		this.setText(name);
		theList = theProductList;
	}
	
	public List<ShoppingItem> getList(){
		return this.theList;
	}

}
