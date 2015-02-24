package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MainPanel extends AnchorPane {
	
	public MainPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	
    	
	}
	
	@FXML
	private StackPane stackPane;
	
	
	
	int i = 0;
	public void buttonClicked(ActionEvent evt){
		
		Product prod = new Product();
		IMatDataHandler im = IMatDataHandler.getInstance();
		List<Product> produ = im.getProducts();
		
		System.out.println(produ.size());
		if(i == 0){
			
			ProfilePanel pp = new ProfilePanel();
			stackPane.getChildren().addAll(pp);
			i++;
		} else{
			stackPane.getChildren().clear();
			OfflinePanel op = new OfflinePanel();
			OnlinePanel onp = new OnlinePanel();
			
			for(int i = 0; i < 4; i++){
				SmallProductPanel smpp = new SmallProductPanel();
				Product p = produ.get(i);
				File fimg = new File(im.getImageIcon(p).getDescription());
				System.out.println(fimg.toURI().toString());
				Image image = new Image(fimg.toURI().toString());
				smpp.setProductImage(image);
				smpp.setProductName(p.getName());
				onp.add(smpp, i);
			}
			stackPane.getChildren().addAll(onp);
		}
		
		System.out.println("Click");
	}
	
}
