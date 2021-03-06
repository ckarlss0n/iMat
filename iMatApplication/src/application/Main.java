package application;
	
import java.io.File;
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
			long startTime = System.currentTimeMillis();
			Pane root = new Pane();
			MainPanel mp = new MainPanel();
			
			root.getChildren().addAll(mp);
			Scene scene = new Scene(root);
			
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
			stage.setWidth(bounds.getWidth()*0.83);
			stage.setHeight(bounds.getHeight()*0.85);
			stage.centerOnScreen();
			stage.setTitle("iMat");
			stage.setScene(scene);
			stage.show();
			
			
			
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println(estimatedTime);
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
