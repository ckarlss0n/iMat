package application;

import java.io.File;
import java.io.IOException;
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
	
	public List_Nx1_view(List<ShoppingItem> theItemList){
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemListPanel.fxml"));
	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }    
	        
	        
	       
	        setHeight(theItemList.size() * 140);
	        
	        for(int i = 0; i < theItemList.size(); i++){ 	
	        	Product p = theItemList.get(i).getProduct();
	        	//System.out.println(theProductList.get(i).toString());
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
	
	
}
