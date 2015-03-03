package application;

import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class CategoryTitledPane extends TitledPane {
	
	private List<ShoppingItem> productList;
	private List_Nx1_view lnxav;
	public CategoryTitledPane(String name, List<ShoppingItem> productList){
		
		this.setText(name);
		this.productList = productList;
		lnxav = new List_Nx1_view(productList);
	}
	
	public List_Nx1_view getListView(){
		return lnxav;
	}
	
	public List<ShoppingItem> getItemsInCategory(){
		return productList;
	}
	
}
