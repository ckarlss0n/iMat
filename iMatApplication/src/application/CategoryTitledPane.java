package application;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CategoryTitledPane extends TitledPane {
	
	private List<ShoppingItem> productList;
	private List_Nx1_view lnxav;
	
	private AnchorPane ancPane;
	private GridPane gridPane;
	
	public CategoryTitledPane(String name, List<ShoppingItem> productList, int i){
		
		this.setText(name);
		this.productList = productList;
		
	}
	
	public CategoryTitledPane(String name, List<List<ShoppingItem>> productLists){
		
		
		gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(0,0,0,0));
		
		//ancPane.setPrefWidth(250);
		
		for (int i = 0; i < productLists.size(); i++){
			Button btn = new Button(productLists.get(i).get(0).getProduct().getCategory().toString());
			btn.setPrefWidth(250);
			gridPane.add(btn, 0, i);
		}
		
		//ancPane.getChildren().addAll(buttons);
		
		
		this.setContent(gridPane);
		
		
		this.setText(name);
	}
	
	public List_Nx1_view getListView(){
		return lnxav;
	}
	
	public List<ShoppingItem> getItemsInCategory(){
		return productList;
	}
	
}
