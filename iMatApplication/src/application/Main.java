package application;
	
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			MainPanel mp = new MainPanel();
			mp.setPrefWidth(1366);
			mp.setPrefHeight(768);
			
			root.getChildren().addAll(mp);
			Scene scene = new Scene(root);


			String defaultCharacterEncoding = System.getProperty("file.encoding");
			
			scene.widthProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue, 
			    		Number oldSceneWidth, Number newSceneWidth) {
			       
			    	mp.setPrefWidth(newSceneWidth.doubleValue());
			    }
			});
			
			scene.heightProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue,
			    		Number oldSceneHeight, Number newSceneHeight) {
			    	mp.setPrefHeight(newSceneHeight.doubleValue());
			    }
			});
			
			
			Stage stage = new Stage();
			
			File file = new File("iMatLogo.png");
			Image icon = new Image(file.toURL().toString());
			
			stage.getIcons().add(icon);
			
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth()*0.8);
			stage.setHeight(bounds.getHeight()*0.8);
			stage.centerOnScreen();
			stage.setTitle("iMat");
			stage.setScene(scene);
			stage.show();
			IMatDataHandler.getInstance().reset();
			IMatDataHandler.getInstance().resetFirstRun();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	            	IMatDataHandler.getInstance().shutDown();
	                System.exit(0);
	            }
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
