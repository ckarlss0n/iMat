package application;

import javafx.scene.Node;

public interface ControlledScreen {
	
	//This method will allow the injection of the Parent ScreenPane 
    public void setScreenParent(ScreensController screenParent); 
    
}
