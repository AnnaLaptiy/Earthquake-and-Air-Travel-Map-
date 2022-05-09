package model;

import java.util.List;

import dao.QuakeDAO;
import dao.QuakeDAOImplementation;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class EarthquakeMarker extends CommonMarker 
{

	protected boolean isOnLand;

	// –адиус маркера землетр€сени€
	protected float radius;
	
	
	// константы дл€ рассто€ни€
	protected static final float kmPerMile = 1.6f;
	
    //Ѕольше или равно этому порогу - умеренное землетр€сение
	public static final float THRESHOLD_MODERATE = 5;
	// Ѕольше или равно этому порогу - слабое землетр€сение
	public static final float THRESHOLD_LIGHT = 4;

	//Ѕольше или равно этому порогу - это промежуточна€ глубина
	public static final float THRESHOLD_INTERMEDIATE = 70;
	//Ѕольше или равно этому порогу - больша€ глубина
	public static final float THRESHOLD_DEEP = 300;


	private static final QuakeDAO quakeDAO = new QuakeDAOImplementation();
	
	
	// абстрактный метод, реализованный в производных классах
	public abstract void drawEarthquake(PGraphics pg, float x, float y);
		
	
	public EarthquakeMarker (PointFeature feature) 
	{
		super(feature.getLocation());
		java.util.HashMap<String, Object> properties = feature.getProperties();
		float magnitude = Float.parseFloat(properties.get("magnitude").toString());
		properties.put("radius", 2*magnitude );
		setProperties(properties);
		this.radius = 1.75f*getMagnitude();
	}
	
	
	// вызывает абстрактный метод drawEarthquake, а затем провер€ет возраст и при необходимости рисует 
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.pushStyle();
			
		colorDetermine(pg);
		
		// вызываем абстрактный метод, реализованный в дочернем классе, дл€ рисовани€ формы маркера
		drawEarthquake(pg, x, y);
		
		String age = getStringProperty("age");
		if ("Past Hour".equals(age) || "Past Day".equals(age)) {

			pg.strokeWeight(2);
			int buffer = 2;
			pg.line(x-(radius+buffer), 
					y-(radius+buffer), 
					x+radius+buffer, 
					y+radius+buffer);
			pg.line(x-(radius+buffer), 
					y+(radius+buffer), 
					x+radius+buffer, 
					y-(radius+buffer));
			
		}
		
		pg.popStyle();
		
	}

	public void showTitle(PGraphics pg, float x, float y)
	{
		String title = getTitle();
		pg.pushStyle();
		
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, pg.textWidth(title) +6, 18, 5);
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(title, x + 3 , y +18);
		
		
		pg.popStyle();
		
	}

	
	
	public double threatCircle() {	
		double miles = 20.0f * Math.pow(1.8, 2*getMagnitude()-5);
		double km = (miles * kmPerMile);
		return km;
	}
	
	// We use: грубокое = красное, среднее = синее, низкое = желтое
	private void colorDetermine(PGraphics pg) {
		float depth = getDepth();
		
		if (depth < THRESHOLD_INTERMEDIATE) { //shallow
			pg.fill(153, 255, 255);
		}
		else if (depth < THRESHOLD_DEEP) {//intermediate
			pg.fill(0, 128, 255);
		}
		else {
			pg.fill(0, 0, 255);//deep
		}
	}
	
	 public static void setQuakeMarkers(List<Marker> quakeMarkers) {
		 quakeDAO.setQuakeMarkers(quakeMarkers);
	 }

	public static void addQuakeMarker(LandQuakeMarker Marker) {
		quakeDAO.addQuakeMarker(Marker);
	}
    public static void addQuakeMarker(OceanQuakeMarker Marker) {
    	quakeDAO.addQuakeMarker(Marker);
    }
    public static List<Marker> getQuakeMarkers(){
    	return quakeDAO.getQuakeMarkers();
    }
    public static void checkEarthquakesForClick(EarthquakeMarker marker) {
    	quakeDAO.checkEarthquakesForClick(marker);
    }
//    public static void unhideQuakeMarkers() {
//    	quakeDAO.unhideQuakeMarkers();
//    }
    public static boolean isLand(PointFeature earthquake) {
    	return quakeDAO.isLand(earthquake);
    }
	
	public static void printQuakes() {
		quakeDAO.printQuakes();
	}
	
	public static boolean isInCountry(PointFeature earthquake, Marker country) {
		return quakeDAO.isInCountry(earthquake, country);
	}
	@Override
	public String toString() {
		return "EarthquakeMarker [isOnLand=" + isOnLand + ", radius=" + radius + ", clicked=" + clicked + ", color="
				+ color + ", strokeColor=" + strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor="
				+ highlightColor + ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location
				+ ", properties=" + properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}
	
	public float getMagnitude() {
		return Float.parseFloat(getProperty("magnitude").toString());
	}
	
	public float getDepth() {
		return Float.parseFloat(getProperty("depth").toString());	
	}
	
	public String getTitle() {
		return (String) getProperty("title");	
		
	}
	
	public float getRadius() {
		return Float.parseFloat(getProperty("radius").toString());
	}
	
	public boolean isOnLand()
	{
		return isOnLand;
	}
	

	
	
}
