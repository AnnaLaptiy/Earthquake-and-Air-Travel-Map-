package model;

import java.util.List;

import dao.MenuDAO;
import dao.MenuDAOImplementation;
import dataBase.DataBase;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

public class menuMarker extends CommonMarker 
{

	private static final MenuDAO menuDAO = new  MenuDAOImplementation();
	// boolean changeColor=false;
	
	
	public menuMarker(Location loc) {
		super(loc);
	}
	public menuMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	}
	
	public static void setMenuMarkers(List<Marker> menuMarkers) {
		menuDAO.setMenuMarkers(menuMarkers);
	}
    public static void addMenuMarker(menuMarker menuMarker) {
    	menuDAO.addMenuMarker(menuMarker);
    }
    public static List<Marker> getMenuMarkers(){
    	return menuDAO.getMenuMarkers();
    }
    public static void secondIsClicked() {
    	menuDAO.secondIsClicked();
    }
	public static void firstIsClicked() {
		menuDAO.firstIsClicked();
	}
	public static void thirdIsClicked() {
		menuDAO.thirdIsClicked();
	}

	public static Marker getMenuMarkerByIndex(int index) {
		return menuDAO.getMenuMarkerByIndex(index);
	}
	public void hideMarkers(Marker mark,List<Marker> array,boolean hide) {
		menuDAO.hideMarkers(mark, array, hide);
		
	}
//	public static int hideFirstSecondThird(Marker mark) {
//		return menuDAO.hideFirstSecondThird(mark);
//	}
	public static int getIndexOfMenu(Marker mark) {
		return menuDAO.getIndexOfMenu(mark);
	}
	public static void hideCityMarkers(Marker mark) {     //
		menuDAO.hideCityMarkers(mark);
		
	}
	public static void hideQuakeMarkers(Marker mark) {
		menuDAO.hideQuakeMarkers(mark);
		
	}
	public static void hideAirMarkers(Marker mark) {
		menuDAO.hideAirMarkers(mark);
		
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		String name = getName();
		String description = getDescription();
		
//		
//
           pg.pushStyle();
   		//pg.stroke(110);

		pg.fill(255, 250, 240);
		pg.textSize(12);
		pg.stroke(204, 102, 0);

		pg.rectMode(PConstants.CORNER);
		pg.rect(x-30, y-15-39, (Math.max(pg.textWidth(name), pg.textWidth(description))) + 	11, 64);
		pg.fill(0,0,0);

		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0,0,0);
		pg.text(name, x-26, y-15-33);
		pg.fill(0,0,0);
		pg.text(description, x-26, y - 15 -18);
		pg.popStyle();
		
		
		
		pg.pushStyle();
		if(getClicked()) {
			pg.fill(255, 510, 51);
		}
		else {
			pg.fill(255, 51, 51);

		}
		pg.stroke(0, 0, 0);
		pg.ellipse(x, y, 10, 10);

		
		pg.popStyle();

	}



	@Override
	public void showTitle(PGraphics pg, float x, float y) {}
	private String getName()
	{
		return getStringProperty(("name"));
	}
	private String getIndex()
	{
		return getStringProperty(("index"));
	}
	private String getDescription()
	{
		return getStringProperty(("description"));
	}
	@Override
	public String toString() {
		return "menuMarker [clicked=" + clicked + ", radius=" + radius + ", color=" + color + ", strokeColor="
				+ strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor=" + highlightColor
				+ ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location + ", properties="
				+ properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}
	
}
