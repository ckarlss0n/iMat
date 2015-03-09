package application;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.Node;


public class ChangeSupport {
	private static ChangeSupport instance = null;
	
	private List pcs;
	
	private ChangeSupport(){
		pcs = new ArrayList();
	}
	
	
	public static ChangeSupport getInstance(){
		if(instance == null){
			instance = new ChangeSupport();
		}
		return instance;
	}
	
	public synchronized void addListner(ChangeScreenListener listener){
		pcs.add(listener);
	}
	
	public synchronized void removeListner(ChangeScreenListener listener){
		pcs.remove(listener);

	}
	
	public void fireNewEvent(String nameOfEvent, Node theScreen){
		TheEvent event = null;
		if(theScreen == null){
			event = new TheEvent(this, nameOfEvent);
		}else{
			event = new TheEvent(this, theScreen);
		}
		
		Iterator i = pcs.iterator();
		while(i.hasNext()){
			((ChangeScreenListener) i.next()).eventRecieved(event);
		}
	}

}
