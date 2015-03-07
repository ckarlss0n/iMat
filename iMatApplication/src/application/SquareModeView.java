package application;

import java.io.IOException;





import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class SquareModeView extends ScrollPane{
	
	@FXML
	private FlowPane flowPane; 
	
	public SquareModeView(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listSquareMode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        setListner();
        
        
	}
	
	public SquareModeView(List<ShoppingItem> theList){
		
		
        
        for(ShoppingItem i : theList){ 	
        	SmallProductPanel smp = new SmallProductPanel(i);
        	add(smp);
        	smp.setPadding((new Insets(2, 2, 2, 2)));
        }
        
        setListner();
	}
	
	
	
	public void setListner(){
		this.widthProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				flowPane.setPrefWidth(newValue.doubleValue());
				
			}
			
		});
	}
	
	public void fillList(List<ShoppingItem> list){
		
		flowPane.getChildren().clear();
		
		for(ShoppingItem i : list){ 	
        	SmallProductPanel smp = new SmallProductPanel(i);
        	add(smp);
        	smp.setPadding((new Insets(2, 2, 2, 2)));
        }
        
	}
	
	public void add(Node node){
		flowPane.getChildren().add(node);
	}
	
}
