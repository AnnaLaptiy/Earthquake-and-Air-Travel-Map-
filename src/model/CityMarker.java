package model;

import java.util.ArrayList;
import java.util.List;

import dao.CityDAO;
import dao.CityDAOImplementation;
import dao.CountryDAO;
import dao.CountryDaoImplementation;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PConstants;
import processing.core.PGraphics;


public class CityMarker extends CommonMarker {
	
	public static int TRI_SIZE = 5;  
	
	private static final CityDAO cityDAO = new CityDAOImplementation();
	private static final CountryDAO countryDAO= new CountryDaoImplementation();
	
	
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		//  properties: "name" (city name), "country" (country name)
		//  "population" (population, in millions)
	}
	
	
	public void drawMarker(PGraphics pg, float x, float y) {
	//	pg.beginDraw();
		
		//System.out.println("Drawing a city");
		pg.pushStyle();
	//	pg.stroke(204, 102, 0);

		
		pg.fill(204, 102, 0);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		
		pg.popStyle();
		// buffer.endDraw();
		
		 
		
	}
	
	public void showTitle(PGraphics pg, float x, float y)
	{
		String name = getCity() + " " + getCountry() + " ";
		String pop = "Pop: " + getPopulation() + " Million";
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-TRI_SIZE-39, Math.max(pg.textWidth(name), pg.textWidth(pop)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-TRI_SIZE-33);
		pg.text(pop, x+3, y - TRI_SIZE -18);
		
		pg.popStyle();
	}
	
	private String getCity()
	{
		return getStringProperty("name");
	}
	
	private String getCountry()
	{
		return getStringProperty("country");
	}
	
	private float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
	public static void setCityMarkers() {
		cityDAO.setCityMarkers(new ArrayList<Marker>());
	}
	public static void setCountryMarkers(List<Marker> countryMarkers) {
		countryDAO.setCountryMarkers(countryMarkers);
	}
	public static void addCityMarker(CityMarker city) {
		cityDAO.addCityMarker(city);
	}
	public static List<Marker> getCityMarkers() {
		return cityDAO.getCityMarkers();
	}
	public static void checkCitiesForClick(Marker marker) {
		cityDAO.checkCitiesForClick(marker);	}


	@Override
	public String toString() {
		return "CityMarker [clicked=" + clicked + ", radius=" + radius + ", color=" + color + ", strokeColor="
				+ strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor=" + highlightColor
				+ ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location + ", properties="
				+ properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}
	
}
