package application;

import java.io.IOException;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class HistoryTitledPanel extends TitledPane{
	
	@FXML
	private Label dateLabel;
	@FXML
	private Label sumLabel;
	@FXML
	private Label smallSumLabel;
	@FXML
	private GridPane gridPane;
	@FXML
	private ScrollPane scrollPane;
	
	private Order order;
	
	private List<ShoppingItem> shoppingItems;
	public HistoryTitledPanel(Order order) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyTitledPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	this.order = order;
    	dateLabel.setText(order.getDate().toString());
    	shoppingItems = order.getItems();
    	int i = 0;
    	int sum = 0;
    	for(ShoppingItem shoppingItem : shoppingItems){
    		double amount = shoppingItem.getAmount();
    		ShoppingItem si = new ShoppingItem(shoppingItem.getProduct());
    		si.setAmount(amount);
    		gridPane.add(new HistoryCartItem(si), 0, i);
    		i++;
    		sum += si.getTotal();
    	}
    	sumLabel.setText(Integer.toString(sum));
    	smallSumLabel.setText(Integer.toString(sum));
	}
	
	public void addAllProducts(ActionEvent evt){
		for(ShoppingItem shoppingItem : shoppingItems){
			ShoppingItem sci = new ShoppingItem(shoppingItem.getProduct());
			try{
				
				for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
					if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
						i.setAmount(sci.getAmount());
						sci = i;
					}
				}
				
				if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){
					sci.setAmount(sci.getAmount()+1);
					IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
				}else {
					sci.setAmount(sci.getAmount());
					IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
				}
			
			} catch(IllegalArgumentException r){
				System.out.println("Error");
			}
		}
	}
	
	public void showDetails(ActionEvent evt){
		System.out.println("Show details.");
	}
}
