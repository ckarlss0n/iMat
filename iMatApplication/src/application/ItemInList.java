package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
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
	private ComboBox<String> productAmount;
	
	@FXML
	private ImageView starImage;
	
	@FXML
	private Label unitSuffix;
	
	@FXML
	private Label productDescription;
	
	private Product theProduct;
	
	private PropertyChangeSupport changeListener;
	
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
        
        List<String> adjectives = new ArrayList<String>();
        adjectives.add("Enastående");
        adjectives.add("Kockens");
        adjectives.add("Äkta");
        adjectives.add("Prima");
        
        List<String> phrases = new ArrayList<String>();
        phrases.add("Perfekt en varm sommardag!");
        phrases.add("Ett måste till fredagsmyset!");
        phrases.add("Barnens favorit!");
        phrases.add("Kockens special!");
        phrases.add("En vinnare på middagsbordet!");
       
        Random random = new Random();
        
        this.sci = sci;
        changeListener = new PropertyChangeSupport(this); 
        
        for(int i=0; i<10; i++){
        	productAmount.getItems().add(i, i+1+"");
        }
        productAmount.setValue(productAmount.getItems().get(0));
        
        
        //mainPanel = m;
        theProduct = sci.getProduct();
        
        File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
        setProductName(theProduct.getName());
        
        productImage.setImage(new Image(image.toURI().toString()));
        lblPrice.setText(String.valueOf(twoDec.format(theProduct.getPrice())));
        unitSuffix.setText(theProduct.getUnit());
        productDescription.setText(adjectives.get(random.nextInt(adjectives.size())) + " " + theProduct.getName().toLowerCase() + " för " + theProduct.getPrice() + " " + theProduct.getUnit() + ". " + phrases.get(random.nextInt(phrases.size())));
        
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
		lblPrice.setText(twoDec.format(price));
	}
	
	public TextField txtAmount;
	
	public void addToFavorite(MouseEvent evt) {
<<<<<<< Updated upstream
		//IMatDataHandler.getInstance().addFavorite(theProduct);
		//System.out.println(IMatDataHandler.getInstance().favorites().size());
		System.out.println("Add to favorites!");
		changeListener.firePropertyChange("addToFavorite", theProduct, null);
=======
		IMatDataHandler.getInstance().addFavorite(theProduct);
>>>>>>> Stashed changes
	}
	

	
	
	public void addToCart(ActionEvent evt){
		int selectedValue;
		try{
<<<<<<< Updated upstream
			selectedValue = Integer.parseInt(productAmount.getValue());
		} catch (NumberFormatException e){
			selectedValue = 1;
		}
		try{
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){ //Already in cart
				if(selectedValue>0){
					sci.setAmount(sci.getAmount() + selectedValue+0.0);
				} else {
					sci.setAmount(sci.getAmount() + 1);
				}
=======
			for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
				if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
					sci = i;
				}
			}
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){
				
				sci.setAmount(sci.getAmount() + Integer.parseInt(txtAmount.getText()));
>>>>>>> Stashed changes
				
				IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
			}else { //Not in cart
				if(selectedValue>0){
					sci.setAmount(selectedValue+0.0);
				} else {
					sci.setAmount(1);
				}
				
				IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
			}
		
		} catch(IllegalArgumentException r){
			System.out.println("Error");
		}
		
	}
	
}
