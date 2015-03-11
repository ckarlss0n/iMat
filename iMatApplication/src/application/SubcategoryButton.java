package application;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.scene.control.Button;

public class SubcategoryButton extends Button{
	private List<ShoppingItem> theList;
	private List<ItemInList> theItemList = new ArrayList<ItemInList>();
	
	public SubcategoryButton(String name, List<ShoppingItem> theProductList){
		this.setText(name);
		theList = theProductList;

		/*
		for(ShoppingItem i:theProductList){
			ItemInList iil = new ItemInList(i);
			theItemList.add(iil);
		}*/
	}
	
	public List<ShoppingItem> getList(){
		return this.theList;
	}
	
	public List<ItemInList> getItemList(){
		return this.theItemList;
	}

}
