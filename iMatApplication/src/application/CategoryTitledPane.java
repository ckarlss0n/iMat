package application;

import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class CategoryTitledPane extends TitledPane {
	
	private List<Product> productList;
	
	public CategoryTitledPane(String name, List<Product> productList){
		
		this.setText(name);
		this.productList = productList;
	}
	
	public List<Product> getProducts(){
		return productList;
	}
	
}
