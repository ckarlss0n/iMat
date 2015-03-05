package application;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
	
	public synchronized void addListner(ChangeListener listener){
		pcs.add(listener);
	}
	
	public synchronized void removeListner(ChangeListener listener){
		pcs.remove(listener);

	}
	
	public void fireNewEvent(String nameOfEvent){
		TheEvent event = new TheEvent(this, nameOfEvent);
		Iterator i = pcs.iterator();
		while(i.hasNext()){
			((ChangeListener) i.next()).eventRecieved(event);
		}
	}

}
