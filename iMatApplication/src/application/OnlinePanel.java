package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class OnlinePanel extends ScrollPane{
	
	@FXML
	private GridPane gridPane;
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private GridPane gridPaneFav;
	@FXML
	private ScrollPane scrollPaneFav;
	
	private List<ShoppingItem> shoppingItems;
	private int index = 0;
	private int numberOfProductsToShow = 3;
	
	private List<ShoppingItem> shoppingItemsFav = new ArrayList<ShoppingItem>();
	private int indexFav = 0;
	private int numberOfProductsToShowFav;
	
	
	public OnlinePanel(List<ShoppingItem> shoppingItems){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homeOnlinePanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
		
		this.shoppingItems = shoppingItems;
		
		for(Product p : IMatDataHandler.getInstance().favorites()){
			shoppingItemsFav.add(new ShoppingItem(p));
		}
		
		if(shoppingItemsFav.size() < 1){
			numberOfProductsToShowFav = 0;
		} else if(shoppingItemsFav.size() < 2){
			numberOfProductsToShowFav = 1;
		} else if(shoppingItemsFav.size() < 3){
			numberOfProductsToShowFav = 2;
		}else{
			numberOfProductsToShowFav = 3;
		}
		
		generateProducts();
		generateProductsFav();
	}
	
	public void setNumberOfProductsToShow(){
		numberOfProductsToShow = (int) (scrollPane.getWidth()/215);
		System.out.println(numberOfProductsToShow);
	}
	
	public void generateProducts(){
		index += numberOfProductsToShow;
		gridPane.getChildren().clear();
		gridPane.setPrefWidth(numberOfProductsToShow*300);
		for(int i = 0; i<numberOfProductsToShow; i++){
        	add(new SmallProductPanel(shoppingItems.get((shoppingItems.size()+index-numberOfProductsToShow+i)%shoppingItems.size())), i);
        }
	}
	
	public void slideBackward(ActionEvent evt){
		setNumberOfProductsToShow();
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), gridPane);
		fadeOut.setOnFinished(event -> backward());
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
	}
	
	public void backward(){
		setNumberOfProductsToShow();
		index -= numberOfProductsToShow;
		System.out.println("Slide backward");
		gridPane.getChildren().clear();
		gridPane.setPrefWidth(numberOfProductsToShow*300);
		for(int i = 0; i<numberOfProductsToShow; i++){
			add(new SmallProductPanel(shoppingItems.get((shoppingItems.size()+index-numberOfProductsToShow+i)%shoppingItems.size())), i);
        }
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPane);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}
	
	public void slideForward(ActionEvent evt){
		setNumberOfProductsToShow();
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPane);
		fadeIn.setOnFinished(event -> forward());
		fadeIn.setFromValue(1.0);
		fadeIn.setToValue(0.0);
		fadeIn.play();
	}
	
	public void forward(){
		setNumberOfProductsToShow();
		index += numberOfProductsToShow;
		System.out.println("Slide forward");
		gridPane.getChildren().clear();
		gridPane.setPrefWidth(numberOfProductsToShow*300);
		for(int i = 0; i<numberOfProductsToShow; i++){
			add(new SmallProductPanel(shoppingItems.get((shoppingItems.size()+index-numberOfProductsToShow+i)%shoppingItems.size())), i);
        }
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPane);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}
	
	//FAVORITES
	
	public void setNumberOfProductsToShowFav(){
		System.out.println("setNumberOfProductsToShowFav");
		numberOfProductsToShowFav = (int) (scrollPaneFav.getWidth()/215);
		System.out.println(numberOfProductsToShowFav);
	}
	
	public void generateProductsFav(){
		System.out.println("generateProductsFav");
		indexFav += numberOfProductsToShowFav;
		gridPaneFav.getChildren().clear();
		gridPaneFav.setPrefWidth(numberOfProductsToShowFav*300);
		for(int i = 0; i<numberOfProductsToShowFav; i++){
        	addFav(new SmallProductPanel(shoppingItemsFav.get((shoppingItemsFav.size()+indexFav-numberOfProductsToShowFav+i)%shoppingItemsFav.size())), i);
        }
	}
	
	public void slideBackwardFav(ActionEvent evt){
		System.out.println("slideBackwardFav");
		setNumberOfProductsToShowFav();
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), gridPaneFav);
		fadeOut.setOnFinished(event -> backwardFav());
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
	}
	
	public void backwardFav(){
		System.out.println("backwardFav");
		setNumberOfProductsToShowFav();
		indexFav -= numberOfProductsToShowFav;
		System.out.println("Slide backward");
		gridPaneFav.getChildren().clear();
		gridPaneFav.setPrefWidth(numberOfProductsToShowFav*300);
		for(int i = 0; i<numberOfProductsToShowFav; i++){
			addFav(new SmallProductPanel(shoppingItemsFav.get((shoppingItemsFav.size()+indexFav-numberOfProductsToShowFav+i)%shoppingItemsFav.size())), i);
        }
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPaneFav);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}
	
	public void slideForwardFav(ActionEvent evt){
		System.out.println("slideForwardFav");
		setNumberOfProductsToShowFav();
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPaneFav);
		fadeIn.setOnFinished(event -> forwardFav());
		fadeIn.setFromValue(1.0);
		fadeIn.setToValue(0.0);
		fadeIn.play();
	}
	
	public void forwardFav(){
		System.out.println("forwardFav");
		setNumberOfProductsToShowFav();
		indexFav += numberOfProductsToShowFav;
		System.out.println("Slide forward");
		gridPaneFav.getChildren().clear();
		gridPaneFav.setPrefWidth(numberOfProductsToShowFav*300);
		for(int i = 0; i<numberOfProductsToShowFav; i++){
			addFav(new SmallProductPanel(shoppingItemsFav.get((shoppingItemsFav.size()+indexFav-numberOfProductsToShowFav+i)%shoppingItemsFav.size())), i);
        }
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gridPaneFav);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}
	
	public void add(Node node, int col){
		gridPane.add(node, col, 0);
	}
	
	public void addFav(Node node, int col){
		System.out.println("addFav");
		gridPaneFav.add(node, col, 0);
	}
	
	public void setWidth(int i){
		gridPane.setPrefWidth(i);
	}
}
