package application;

import java.io.IOException;
import java.text.DecimalFormat;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChoosePayment extends ScrollPane {

	@FXML
	private Button finalizeBtn;
	
	@FXML
	private ScrollPane scrlCard;
	@FXML
	private ScrollPane scrlF;
	@FXML
	private ScrollPane scrlPost;
	
	@FXML
	private TitledPane titledInvoice;
	
	@FXML
	private TitledPane titledCard;
	
	@FXML
	private TitledPane titledCOD;
	
	@FXML
	private Label lblInvoice;
	
	@FXML
	private Label lblCard;
	
	@FXML
	private Label lblCOD;
	
	@FXML
	private Label lblCODDisc;
	
	@FXML
	private Label lblCardDisc;
	
	@FXML
	private Label lblInvoiceDisc;
	
	private BooleanBinding bb;
	
	private CardPayment cp;
	private FPayment fp;
	private PostPayment pp;
	MainPanel mainPanel;
	//CheckoutPanel checkoutPanel;
	DecimalFormat twoDec = new DecimalFormat("0.00");
	
	public ChoosePayment(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosePayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		//this.checkoutPanel = checkoutPanel;
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
            
        }
		
		cp = new CardPayment(this);
		//scrlCard.setMaxHeight(cp.getPrefHeight());
		scrlCard.setContent(cp);
		
		fp = new FPayment();
		//scrlF.setMaxHeight(fp.getPrefHeight());
		scrlF.setContent(fp);
		
		pp = new PostPayment();
		//scrlPost.setMaxHeight(pp.getPrefHeight());
		scrlPost.setContent(pp);
	}
	
	public void finalizeBuy(ActionEvent evt){
	
		IMatDataHandler.getInstance().placeOrder(true);
		cp.fillCardInfo();
		
		if(cp.getIsCorrect()){
			CheckoutPanel checkoutPanel = new CheckoutPanel();
			System.out.println("Placing order");
			ChangeSupport.getInstance().fireNewEvent("", checkoutPanel);
		} 
			
	}
	
	public void setBinding(BooleanBinding bp){
		if(bp != null){
			bb = bp;
		}
		finalizeBtn.disableProperty().bind(bb);
	}
	
	public Button getBtn(){
		return finalizeBtn;
	}
	
	public void setFinalizeText(Double sum){
		finalizeBtn.setText("Slutför köp (" + String.valueOf(twoDec.format(sum)) + " kr)");
	}
	
	public void setTitles(){
		if(!titledInvoice.isExpanded() && !titledCard.isExpanded() && !titledCOD.isExpanded()){
			lblInvoice.setFont(Font.font ("System", 36));
			lblInvoiceDisc.setOpacity(1);
			lblInvoiceDisc.setPadding(new Insets(0, 0, 0, 0));
			
			lblCard.setFont(Font.font ("System", 36));
			lblCardDisc.setOpacity(1);
			lblCardDisc.setPadding(new Insets(0, 0, 0, 0));
			
			lblCOD.setFont(Font.font ("System", 36));
			lblCODDisc.setOpacity(1);
			lblCODDisc.setPadding(new Insets(0, 0, 0, 0));
		}
	}
	
	public void invoiceClicked(MouseEvent e){
		
		System.out.println(!titledInvoice.isExpanded() +" " +!titledCard.isExpanded() +" "+ !titledCOD.isExpanded());
		
		lblInvoice.setFont(Font.font ("System", 36));
		lblInvoiceDisc.setOpacity(1);
		lblInvoiceDisc.setPadding(new Insets(0, 0, 0, 0));
		
		lblCard.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblCardDisc.setOpacity(0);
		lblCardDisc.setPadding(new Insets(-15, 0, 0, 0));
		scrlCard.setPrefHeight(USE_COMPUTED_SIZE);
		
		lblCOD.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblCODDisc.setOpacity(0);
		lblCODDisc.setPadding(new Insets(-15, 0, 0, 0));
		
		finalizeBtn.disableProperty().unbind();
		finalizeBtn.setDisable(false);
		setTitles();
	}
	
	public void cardClicked(MouseEvent e){
		
		
		lblInvoice.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblInvoiceDisc.setOpacity(0);
		lblInvoiceDisc.setPadding(new Insets(-15, 0, 0, 0));
		
		lblCard.setFont(Font.font ("System", 36));
		lblCardDisc.setOpacity(1);
		lblCardDisc.setPadding(new Insets(0, 0, 0, 0));
		
		lblCOD.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblCODDisc.setOpacity(0);
		lblCODDisc.setPadding(new Insets(-15, 0, 0, 0));
		setBinding(bb);
		setTitles();
		
	}
	public void codClicked(MouseEvent e){
		
		lblInvoice.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblInvoiceDisc.setOpacity(0);
		lblInvoiceDisc.setPadding(new Insets(-15, 0, 0, 0));
		
		lblCard.setFont(Font.font ("System", FontWeight.BOLD, 13));
		lblCardDisc.setOpacity(0);
		lblCardDisc.setPadding(new Insets(-15, 0, 0, 0));
		
		lblCOD.setFont(Font.font ("System", 36));
		lblCODDisc.setOpacity(1);
		lblCODDisc.setPadding(new Insets(0, 0, 0, 0));
		setTitles();
	}
	
	
	
	
	
}
