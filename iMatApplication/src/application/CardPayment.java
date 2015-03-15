package application;

import java.io.IOException;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class CardPayment extends BorderPane {
	
	@FXML
	private ChoiceBox<String> chbCardType;
	
	@FXML
	private ChoiceBox<String> chbMonth;
	
	@FXML
	private ChoiceBox<String> chbYear;
	
	@FXML
	private TextField txtfCardHolderName;
	
	@FXML
	private TextField txtfCardNumber;
	
	@FXML
	private TextField txtfCVC;
	
	@FXML
	private TextField txtfNbr1;
	
	@FXML
	private TextField txtfNbr2;
	
	@FXML
	private TextField txtfNbr3;
	
	@FXML
	private TextField txtfNbr4;
	
	@FXML
	private Label lblErrorType;
	@FXML
	private Label lblErrorName;
	@FXML
	private Label lblErrorCardNbr;
	@FXML
	private Label lblErrorDate;
	@FXML
	private Label lblErrorYear;
	@FXML
	private Label lblErrorCVC;
	
	private boolean isFilledCorrect;
	
	private ChoosePayment cp;
	public CardPayment(ChoosePayment cp){
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardPaymentNew.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		this.cp = cp;
    
		int sum = 0;
		for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
			sum += i.getProduct().getPrice() * i.getAmount();
		}
		
		
		//chbCardType.setItems(FXCollections.observableArrayList("Välj korttyp","Visa", "MasterCard"));
		
		chbMonth.setItems(FXCollections.observableArrayList("Månad", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12"));
		
		chbYear.setItems(FXCollections.observableArrayList("År"));
		
		for(int i = 0; i < 15; i++){
			chbYear.getItems().add("20" + (15+i));
		}
		
		
		chbMonth.setValue(chbMonth.getItems().get(0));
		chbYear.setValue(chbYear.getItems().get(0));
		
		txtfCardHolderName.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(txtfCardHolderName.getText().matches(".+")){
    	            	txtfCardHolderName.setStyle("-fx-border-width: 0px ;");
    	            	lblErrorName.setOpacity(0);
    	            }else{
    	            	txtfCardHolderName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	lblErrorName.setOpacity(1);
    	            	
    	            }
    	        }
    	    }
    	});
		int maxLength = 4;
		txtfNbr1.setAlignment(Pos.CENTER);
		txtfNbr1.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	if(txtfNbr1.getText().length()==maxLength){
	        		txtfNbr2.requestFocus();
	        	}
	        	if (txtfNbr1.getText().length() > maxLength) {
	                String s = txtfNbr1.getText().substring(0, maxLength);
	                txtfNbr1.setText(s);
	                txtfNbr2.requestFocus();
	               
	            }
	        }
	    });
		
		txtfNbr2.setAlignment(Pos.CENTER);
		txtfNbr2.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	if(txtfNbr2.getText().length()==maxLength){
		        		txtfNbr3.requestFocus();
		       	}
	        	if (txtfNbr2.getText().length() > maxLength) {
	                String s = txtfNbr2.getText().substring(0, maxLength);
	                txtfNbr2.setText(s);
	                txtfNbr3.requestFocus();
	               
	            }
	        }
	    });
		
		txtfNbr3.setAlignment(Pos.CENTER);
		txtfNbr3.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if(txtfNbr3.getText().length()==maxLength){
	        		txtfNbr4.requestFocus();
	        	}
	            if (txtfNbr3.getText().length() > maxLength) {
	                String s = txtfNbr3.getText().substring(0, maxLength);
	                txtfNbr3.setText(s);
	                
	               
	            }
	        }
	    });
		
		txtfNbr4.setAlignment(Pos.CENTER);
		txtfNbr4.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	if (txtfNbr4.getText().length() > maxLength) {
	                String s = txtfNbr4.getText().substring(0, maxLength);
	                txtfNbr4.setText(s);
	                
	               
	            }
	        }
	    });
		
		txtfCVC.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	if (txtfCVC.getText().length() > 3) {
	                String s = txtfCVC.getText().substring(0, 3);
	                txtfCVC.setText(s);
	                
	               
	            }
	        }
	    });
		
		
		
		setTextfieldsMaxLength();
		
		
		BooleanBinding bb = new BooleanBinding() {
		    {
		       super.bind(txtfCVC.textProperty(),
		    		   chbMonth.getSelectionModel().selectedItemProperty(),
		    		   chbYear.getSelectionModel().selectedItemProperty(), txtfCardHolderName.textProperty());
		    }
		    @Override
		    protected boolean computeValue() {
		    	
		      return (!(txtfCVC.getText().length() >0
		            && txtfCardHolderName.getText().length() > 0));
		    }
		};
		cp.setBinding(bb);
		
		
		txtfCardHolderName.setText(IMatDataHandler.getInstance().getCreditCard().getHoldersName());
		//txtfCardNumber.setText(IMatDataHandler.getInstance().getCreditCard().getCardNumber());
		
		
		
	}
	
	public void setTextfieldsMaxLength(){
		int maxLength = 4;
		txtfNbr1.setAlignment(Pos.CENTER);
		txtfNbr1.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	if(txtfNbr1.getText().length()==maxLength){
	        		txtfNbr2.requestFocus();
	        	}
	        	if (txtfNbr1.getText().length() > maxLength) {
	                String s = txtfNbr1.getText().substring(0, maxLength);
	                txtfNbr1.setText(s);
	                txtfNbr2.requestFocus();
	               
	            }
	        }
	    });
		
		txtfNbr2.setAlignment(Pos.CENTER);
		txtfNbr2.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	if(txtfNbr2.getText().length()==maxLength){
		        		txtfNbr3.requestFocus();
		       	}
	        	if (txtfNbr2.getText().length() > maxLength) {
	                String s = txtfNbr2.getText().substring(0, maxLength);
	                txtfNbr2.setText(s);
	                txtfNbr3.requestFocus();
	               
	            }
	        }
	    });
		
		txtfNbr3.setAlignment(Pos.CENTER);
		txtfNbr3.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if(txtfNbr3.getText().length()==maxLength){
	        		txtfNbr4.requestFocus();
	        	}
	            if (txtfNbr3.getText().length() > maxLength) {
	                String s = txtfNbr3.getText().substring(0, maxLength);
	                txtfNbr3.setText(s);
	                
	               
	            }
	        }
	    });
		
		txtfNbr4.setAlignment(Pos.CENTER);
		txtfNbr4.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	if (txtfNbr4.getText().length() > maxLength) {
	                String s = txtfNbr4.getText().substring(0, maxLength);
	                txtfNbr4.setText(s);
	                
	               
	            }
	        }
	    });
		
		txtfCVC.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	if (txtfCVC.getText().length() > 3) {
	                String s = txtfCVC.getText().substring(0, 3);
	                txtfCVC.setText(s);
	                
	               
	            }
	        }
	    });
		
	}
	
	public void fillCardInfo(){
		String cardNbr = "";
		int CVC = 0;
		String type = "";
		String cardHolder = "";
		int validMonth = 0;
		int validYear = 0;
		
		try{
			if((txtfCardHolderName.getText().matches(".+"))){
				cardHolder = txtfCardHolderName.getText();
				lblErrorName.setOpacity(0);
				
				
				isFilledCorrect = true;
				
			}else{
				System.out.println("Error name");
				throw new IllegalArgumentException();
			}
			
		} catch(Exception e){
			lblErrorName.setOpacity(1);
			
			isFilledCorrect = false;
		}
		
		
		
		try{
			CVC = Integer.parseInt(txtfCVC.getText());
			
			if(txtfCVC.getText().length() <= 2){
				//System.out.println("Error cvc");
				throw new IllegalArgumentException();
			}
			lblErrorCVC.setOpacity(0);
			
			if(isFilledCorrect == true){
				isFilledCorrect = true;
			}
		} catch(Exception e){
			lblErrorCVC.setOpacity(1);
			
			
			isFilledCorrect = false;
			
			//isFilledCorrect = false;
		}
		
		try{
			
			int n1 = Integer.parseInt(txtfNbr1.getText());
			int n2 = Integer.parseInt(txtfNbr2.getText());
			int n3 = Integer.parseInt(txtfNbr3.getText());
			int n4 = Integer.parseInt(txtfNbr4.getText());
			
			cardNbr = "" + n1 + n2 + n3 + n4;
			lblErrorCardNbr.setOpacity(0);
			if(isFilledCorrect == true){
				isFilledCorrect = true;
			}
		}catch (Exception e){
			lblErrorCardNbr.setOpacity(1);
			
			isFilledCorrect = false;
		}
		
		
		try{
			
			validMonth = Integer.parseInt(chbMonth.getSelectionModel().getSelectedItem());
			if(isFilledCorrect == true){
				isFilledCorrect = true;
			}
			lblErrorDate.setOpacity(0);
		} catch(Exception e){
			lblErrorDate.setOpacity(1);
		
			isFilledCorrect = false;
			
			//isFilledCorrect = false;
		}
		
		try{
			validYear = Integer.parseInt(chbYear.getSelectionModel().getSelectedItem());
			lblErrorDate.setOpacity(0);
			if(isFilledCorrect == true){
				isFilledCorrect = true;
			}
			
		} catch(Exception e){
			
			isFilledCorrect = false;
			
			//isFilledCorrect = false;
			lblErrorDate.setOpacity(1);
			//lblErrorYear.setOpacity(1);
		}
		
		
		if(isFilledCorrect){
			IMatDataHandler.getInstance().getCreditCard().setHoldersName(txtfCardHolderName.getText());
			IMatDataHandler.getInstance().getCreditCard().setCardNumber(cardNbr);
			IMatDataHandler.getInstance().getCreditCard().setVerificationCode(CVC);
			IMatDataHandler.getInstance().getCreditCard().setCardType(type);
			IMatDataHandler.getInstance().getCreditCard().setValidMonth(validMonth);
			IMatDataHandler.getInstance().getCreditCard().setValidMonth(validYear);
			
		}
		
			
	}
	
	public boolean getIsCorrect(){
		return isFilledCorrect;
	}
	
	
	
	
	
}
