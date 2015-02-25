package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;



public class MainPanel extends AnchorPane {
	
	private List<Product> productList;
	private IMatDataHandler dataHandler;
	
	public MainPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	
		dataHandler = IMatDataHandler.getInstance();
		productList = dataHandler.getProducts();
		
		User theUser = dataHandler.getUser();
		
		theUser.setUserName("Emil");
		theUser.setPassword("123");
    	
	}
	
	@FXML
	private StackPane stackPane;
	
	
	
	int i = 0;
	public void buttonClicked(ActionEvent evt){
		
		System.out.println(productList.size());
		if(i == 0){
			List_Nx1_view l = new List_Nx1_view();
			
			ProfilePanel pp = new ProfilePanel();
			stackPane.getChildren().addAll(l);
			i++;
		}else if(i == 1){
			
			RegisterPanel rp = new RegisterPanel();
			
			stackPane.getChildren().clear();
			LoginPanel lp = new LoginPanel();
			stackPane.getChildren().add(rp);
			
		}else{
			stackPane.getChildren().clear();
			OfflinePanel op = new OfflinePanel();
			OnlinePanel onp = new OnlinePanel();
			
			onp.setWidth(5 * 190);
			for(int i = 0; i < 5; i++){
				SmallProductPanel smpp = new SmallProductPanel();
				
				Product p = productList.get(i);
				File fimg = new File(dataHandler.getImageIcon(p).getDescription());
				smpp.setProductImage(new Image(fimg.toURI().toString()));
				smpp.setProductName(p.getName());
				
				onp.add(smpp, i);
			}
			stackPane.getChildren().addAll(onp);
		}
		
		System.out.println("Click");
	}
	
}
