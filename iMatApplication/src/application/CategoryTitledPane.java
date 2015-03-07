package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	private List<ItemInList> itemInList = new ArrayList<ItemInList>();
	
	
	private AnchorPane ancPane;
	private GridPane gridPane;
	
	public CategoryTitledPane(String name ,Map<String, Map<String, List<ShoppingItem>>> theMap){
		this.setText(name);
		
	}
	
	public CategoryTitledPane(String name, List<ShoppingItem> productList, int i){
		
		this.setText(name);
		this.productList = productList;
		
	}
	
	public CategoryTitledPane(String name, List<SubcategoryButton> buttons, List<ShoppingItem> allProductsInCategory){
		this.setText(name);
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(0,0,0,0));
		gridPane.setPrefHeight(0); //Make sub-categories compact
		
		if(buttons.size() > 1){
			int i = 0;
			for(SubcategoryButton b: buttons){
				b.setPrefWidth(250);
				gridPane.add(b, 0, i);
				i++;
				
			}
			gridPane.setMaxHeight(i*buttons.get(0).getHeight());
			this.setContent(gridPane);
		} else{
			this.setCollapsible(false); //Don't show arrow if no sub-categories
		}
		this.productList = allProductsInCategory;
		
		for(ShoppingItem i:productList){
			ItemInList iil = new ItemInList(i);
			itemInList.add(iil);
		}
		//System.out.println(itemInList.size());
	}	
	
	public List<ItemInList> getItemInList(){
		return itemInList;
	}
	
	public List<ShoppingItem> getItemsInCategory(){
		return productList;
	}
	
}
