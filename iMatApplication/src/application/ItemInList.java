package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.scenario.effect.DropShadow;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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
        adjectives.add("Enast�ende");
        adjectives.add("Kockens");
        adjectives.add("�kta");
        adjectives.add("Prima");
        
        List<String> phrases = new ArrayList<String>();
        phrases.add("Perfekt en varm sommardag!");
        phrases.add("Ett m�ste till fredagsmyset!");
        phrases.add("Barnens favorit!");
        phrases.add("Kockens special!");
        phrases.add("En vinnare p� middagsbordet!");
       
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
        productDescription.setText(adjectives.get(random.nextInt(adjectives.size())) + " " + theProduct.getName().toLowerCase() + " f�r " + theProduct.getPrice() + " " + theProduct.getUnit() + ". " + phrases.get(random.nextInt(phrases.size())));
        
        //this.changeListner.addPropertyChangeListener(m);
        
        //lblPrice.setOnMouseClicked(mousehandler);
        
       // heartImage.setOnMouseClicked(mousehandler);
        setStar();
	}
	
	DecimalFormat noDec = new DecimalFormat("#");
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	public void setStar(){
		File file;
		if(IMatDataHandler.getInstance().isFavorite(sci.getProduct())){
			file = new File("icon32/star-full-yellow.png");
		} else {
			file = new File("icon32/star-empty.png");
		}
		Image icon = new Image(file.toURI().toString());
	    starImage.setImage(icon);
	}
	
	public String getName(){
		return this.getName();
	}
	
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
		File file;
		if(!IMatDataHandler.getInstance().isFavorite(sci.getProduct())){
			IMatDataHandler.getInstance().addFavorite(sci.getProduct());
			file = new File("icon32/star-full-yellow.png");
		} else {
			IMatDataHandler.getInstance().removeFavorite(sci.getProduct());
			file = new File("icon32/star-empty.png");
		}
		Image image = new Image(file.toURI().toString());
	    starImage.setImage(image);
	}
	
	public void setToHalfStar(MouseEvent evt){
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), starImage);
		fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.3);
		fadeOut.play();
//		File file;
//		file = new File("icon32/star-half.png");
//		Image image = new Image(file.toURI().toString());
//	    starImage.setImage(image);
	}

	public void setToNormal(MouseEvent evt){
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), starImage);
		fadeIn.setFromValue(0.3);
		fadeIn.setToValue(1.0);
		fadeIn.play();
		setStar();
	}
	
	public void addToCart(ActionEvent evt){
		int selectedValue;
		try{
			selectedValue = Integer.parseInt(productAmount.getValue());
		} catch (NumberFormatException e){
			selectedValue = 1;
		}
		
		try{
			for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
				if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
					System.out.println("same");
					sci = i;
				}
			}
			
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){ //Already in cart
			
				
				if(selectedValue>0){
					sci.setAmount(sci.getAmount() + selectedValue+0.0);
				} else {
					sci.setAmount(sci.getAmount() + 1);
				}
		
				
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
