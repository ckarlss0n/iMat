package application;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class CenterScreenController extends StackPane {
	
	public void setScreen(Node theScreen) {
		try{
			//Is there is more than one screen 
			if(!getChildren().isEmpty()){ 
				//remove displayed screen 
				getChildren().remove(0); 
				//add new screen 
				getChildren().add(0, theScreen);      
			} else {
				getChildren().add(theScreen); 
		       
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		 
		      
		     
	 }
}
