package model;


import java.util.List;

import dao.MapDao;
import dao.MapDaoImplementation;
import dataBase.DataBase;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;


public abstract class CommonMarker extends SimplePointMarker {

	protected boolean clicked = false;
	private static final MapDao mapDaoImplementation = new MapDaoImplementation();
	
	
	 public static void setMap(UnfoldingMap map) {
		 mapDaoImplementation.setMap(map);
		 
	 }
	 public static void setsecMap(UnfoldingMap map) {
		 mapDaoImplementation.setSecMap(map);
	 }
	 public static UnfoldingMap getMap() {
		 return mapDaoImplementation.getMap();
	 }
	 public static UnfoldingMap getConsole() {
		 return mapDaoImplementation.getMap2();
	    }
	    public static void addMarkersToMap() {
	    	mapDaoImplementation.addMarkersToMap();
	    }
	    public static void addMenuMarkersToMap() {
	    	mapDaoImplementation.addMenuMarkersToMap();
	    }
	    public static void unselected() {
	    	mapDaoImplementation.unselected();
	    }
	    public static CommonMarker getLastSelected() {
	    	return mapDaoImplementation.getLastSelected();
	    }
	    public static CommonMarker getLastClicked() {
	    	return mapDaoImplementation.getLastClicked();
	    }
	    public static void setLastClickedNull() {
	    	mapDaoImplementation.setLastClickedNull();
	    }
	    public static void selectByCountry(String country) {
	    	mapDaoImplementation.selectByCountry(country);
	    }
		public static void selectMarkerIfHover(CommonMarker marker) {
			mapDaoImplementation.selectMarkerIfHover(marker);
		}
		public static void unhideMarkers() {
			mapDaoImplementation.unhideMarkers();
		}
		 public static List<String> getCountriesWithQuakes(){
		    	return mapDaoImplementation.getCountriesWithQuakes();
		    }

	public CommonMarker(Location location) {
		
		super(location);
		
	}
	
	public CommonMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}
	
	public boolean getClicked() {
		return clicked;
	}
	
	public void setClicked(boolean state) {
		clicked = state;
	}
	

	public void draw(PGraphics pg, float x, float y) {
		

		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);
			}
		}
	}
	public abstract void drawMarker(PGraphics pg, float x, float y);
	public abstract void showTitle(PGraphics pg, float x, float y);

	@Override
	public String toString() {
		return "CommonMarker [clicked=" + clicked + "]";
	}
	
}