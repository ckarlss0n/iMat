package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static final String SCR1ID = "screen1";
    public static final String SCR1_FXML = "Screen1.fxml";
    public static final String SCR2ID = "screen2"; 
    public static final String SCR2_FXML = "Screen2.fxml"; 
    public static final String SCR3ID = "screen3"; 
    public static final String SCR3_FXML = "Screen3.fxml"; 
    
    
	@Override
	public void start(Stage primaryStage) {
		
		ScreensController mainContainer = new ScreensController();
		
		mainContainer.loadScreen(SCR1ID, SCR1_FXML);
		mainContainer.loadScreen(Main.SCR2ID, Main.SCR2_FXML);
		mainContainer.loadScreen(SCR3ID, SCR3_FXML);
		
		mainContainer.setScreen(SCR1ID);
		
		/*FXMLLoader myLoader = null;
		
		try{
			myLoader = new FXMLLoader(getClass().getResource("SamplePane.fxml"));	 
		} catch(Exception e){
			System.out.println("Fel 1");
		
		}
		
		Parent loadScreen = null;
		try{
			loadScreen = (Parent) myLoader.load();	 
		} catch(Exception e){
			System.out.println("Fel 2");
		}
		
		Screen1Controller myScreenControler = null;
		
		try{
			myScreenControler = ((Screen1Controller) myLoader.getController()); 
			
			
		} catch(Exception e){
			System.out.println("Fel 3");
			e.printStackTrace();
		}
		
		try{
			System.out.println(loadScreen.toString());
			myScreenControler.add(loadScreen);
		}catch(Exception e){
			System.out.println("Fel 4");
			System.out.println(e.getMessage());
		}
		
		
		*/
		
		Group root = new Group(); 
	    root.getChildren().addAll(mainContainer); 
	    Scene scene = new Scene(root); 
	    //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    primaryStage.setScene(scene); 	
	    primaryStage.show(); 
	    
	    /*
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Screen2.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
