package application;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class List_Nx1_view extends ScrollPane{
	private List<ShoppingItem> theItemList;
	
	public  List_Nx1_view(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemListPanel.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);
	
	    try {
	        fxmlLoader.load();
	    } catch (IOException exception) {
	        throw new RuntimeException(exception);
	    } 
	}
	
	public List_Nx1_view(List<ItemInList> iil, int i){
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemListPanel.fxml"));
		 fxmlLoader.setRoot(this);
		 fxmlLoader.setController(this);

		 try {
			 fxmlLoader.load();
		 } catch (IOException exception) {
            throw new RuntimeException(exception);
		 }   
		 
		 
		setHeight(iil.size() * 140);
			
			
		int in = 0;
		for(ItemInList item : iil){ 	
	       	add(item, in);
	        in++;  	
		}
	        
	}
	
	public List_Nx1_view(List<ShoppingItem> theItemList){
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemListPanel.fxml"));
	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }    
	        
	        
	       this.theItemList = theItemList;
	       setHeight(theItemList.size() * 140);
	        
	        for(int i = 0; i < theItemList.size(); i++){ 	
	        	ItemInList itp = new ItemInList(theItemList.get(i));
	        	add(itp, i);
	        }
	}
	
	@FXML
	private GridPane gridPane;
	
	public void setHeigth(int height){
		gridPane.setPrefHeight(height);
	}
	
	
	public void add(Node node, int row){
		gridPane.add(node, 0, row);
	}
	
	public List<ShoppingItem> getList(){
		return this.theItemList;
	}
	
	ItemInList itp;
	public void fillList(List<ShoppingItem> list){
		gridPane.getChildren().clear();
		theItemList = list;
		setHeight(list.size() * 140);
		
		
		int i = 0;
		for(ShoppingItem si : theItemList){ 	
        	itp = new ItemInList(si);
        	add(itp, i);
        	i++;
        	
        }
		
	}
	
}
