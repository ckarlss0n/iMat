package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ItemInList extends BorderPane {
	@FXML 
	private ImageView productImage;
	
	@FXML 
	private Label lblProductName;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private ComboBox<Integer> productAmount;
	
	@FXML
	private ImageView heartImage;
	
	private Product theProduct;
	
	private PropertyChangeSupport changeListner;
	
	private MainPanel mainPanel;
	private ShoppingItem sci;
	
	
	public ItemInList(ShoppingItem sci){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
        
        
       
        this.sci = sci;
        changeListner = new PropertyChangeSupport(this); 
        
        
        //mainPanel = m;
        theProduct = sci.getProduct();
        
        File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
 
        setProductName(theProduct.getName());
        
        productImage.setImage(new Image(image.toURI().toString()));
        lblPrice.setText(String.valueOf(twoDec.format(theProduct.getPrice()) + " kr"));
        
        //this.changeListner.addPropertyChangeListener(m);
        
        //lblPrice.setOnMouseClicked(mousehandler);
        
       // heartImage.setOnMouseClicked(mousehandler);
        

	}
	
	DecimalFormat noDec = new DecimalFormat("#");
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	public void setProductImage(Image img){
		productImage.setImage(img);
	}
	
	public void setProductName(String name){
		lblProductName.setText(name);
	}
	
	public void setPrice(double price){
		lblPrice.setText(twoDec.format(price) + " kr");
	}
	
	public TextField txtAmount;
	
	public void addToFavorite(MouseEvent evt) {
		//IMatDataHandler.getInstance().addFavorite(theProduct);
		//System.out.println(IMatDataHandler.getInstance().favorites().size());
		changeListner.firePropertyChange("addToFavorite", theProduct, null);
	}
	
	
	
	public void addToCart(ActionEvent evt){
		try{
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){
				
				sci.setAmount(sci.getAmount() + Integer.parseInt(txtAmount.getText()));
				
				IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
			}else {
				sci.setAmount(Integer.parseInt(txtAmount.getText()));
				IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
			}
		
		} catch(IllegalArgumentException r){
			System.out.println("Error");
		}
		
	}
	
}
