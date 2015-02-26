package application;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ItemInList extends BorderPane {
	@FXML 
	private ImageView productImage;
	
	@FXML 
	private Label lblProductName;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Product theProduct;
	
	private PropertyChangeEvent changeListner;
	
	private MainPanel mainPanel;
	public ItemInList(Product p, MainPanel m){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
        
        //changeListner = 
        
        //mainPanel = m;
        theProduct = p;
        
        File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
 
        setProductName(theProduct.getName());
        
        productImage.setImage(new Image(image.toURI().toString()));
        lblPrice.setText(String.valueOf(theProduct.getPrice()) + " kr");
        
        
        
	}
	
	
	
	public void setProductImage(Image img){
		productImage.setImage(img);
	}
	
	public void setProductName(String name){
		lblProductName.setText(name);
	}
	
	public void setPrice(double price){
		lblPrice.setText(String.valueOf(price) + " kr");
	}
	
	public void addToCart(ActionEvent evt){
		mainPanel.changeShoppingCart(theProduct);
		IMatDataHandler.getInstance().addProduct(theProduct);
		IMatDataHandler.getInstance().getShoppingCart().addProduct(theProduct, 1);
		
		System.out.println(IMatDataHandler.getInstance().getShoppingCart().getTotal());
	}
	
}