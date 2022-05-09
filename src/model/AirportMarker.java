package model;

import java.util.List;

import dao.AirDAO;
import dao.AirDAOImplementation;
import dao.RouteDAO;
import dao.RouteDAOImplementation;
import dataBase.DataBase;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;


public class AirportMarker extends CommonMarker {
	
	public static List<SimpleLinesMarker> routes;
	Feature feature;

	private static final RouteDAO routeDAO = new RouteDAOImplementation();
	private static final AirDAO airDAO = new AirDAOImplementation();
	
	    
	    
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		feature=city;
		
	}
	public Feature getFeature() {
		return feature;
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
	//	pg.beginDraw();
		pg.fill(255, 204, 153);
		
		pg.ellipse(x, y, 5, 5);
		
	//	pg.endDraw();
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		String name = "   "+getName();
		String where = " Country: " + getCountry() + "  City"+getCity();
		String altitude = "   Altitude: "+getAltitude();
		pg.pushStyle();
	
		
		pg.fill(204, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-5-39, Math.max(Math.max(pg.textWidth(name+" "), pg.textWidth(where+" ")),pg.textWidth(altitude+"   ")) + 6, 54,20);
		pg.fill(102, 204, 255);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(57, 90, 255);
		pg.text(name, x+3, y-5-33);
		pg.fill(102, 204, 255);
		pg.text(where, x+3, y - 5 -18);
		pg.text(altitude, x+3, y - 5 -3);
		pg.popStyle();
	
	}
	private String getCountry()
	{
		return getStringProperty("country");
	}
	private int getAltitude()
	{
		return Integer.parseInt(getStringProperty("altitude"));
	}
	private String getCode()
	{
		return getStringProperty("code");
	}
	private String getCity()
	{
		return getStringProperty("city");
	}
	private String getName()
	{
		return getStringProperty("name");
	}
	 public static void setRouteList(List<Marker> routeList) {
		 routeDAO.setRouteList(routeList);
	 }
	    public static void addRoute(SimpleLinesMarker Marker) {
	    	routeDAO.addRoute(Marker);
	    }
	    public static List<Marker> getRouteList(){
	    	return routeDAO.getRouteList();
	    }
	    public static void hideAirRouts() {
	    	routeDAO.hideAirRouts();
	    }
	    public static void hideRouteByAir() {
	    	routeDAO.hideRouteByAir();
	    }
//	    public static void unhideRoutMarkers() {
//	    	routeDAO.unhideRoutMarkers();
//	    }
	    public static void setAirportList(List<Marker> airportList) {
	    	airDAO.setAirportList(airportList);
	    }
	    public static void addAirport(AirportMarker Marker) {
	    	airDAO.addAirport(Marker);
	    }
	    public static List<Marker> getAirportList(){
	    	return airDAO.getAirportList();
	    }
	    public static void checkAirForClick(Marker marker) {
	    	airDAO.checkAirForClick(marker);
	    }
	  
	    
	
	@Override
	public String toString() {
		return "AirportMarker [feature=" + feature + ", clicked=" + clicked + ", radius=" + radius + ", color=" + color
				+ ", strokeColor=" + strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor="
				+ highlightColor + ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location
				+ ", properties=" + properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}
}
