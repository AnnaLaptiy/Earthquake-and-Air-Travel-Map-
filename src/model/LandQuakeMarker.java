package model;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

public class LandQuakeMarker extends EarthquakeMarker {
	
	
	public LandQuakeMarker(PointFeature quake) {
		super(quake);
		isOnLand = true;
	}


	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//pg.stroke(204, 102, 0);

		pg.ellipse(x, y, 2*radius, 2*radius);
		
	}
	
	public String getCountry() {
		return (String) getProperty("country");
	}


	@Override
	public String toString() {
		return "LandQuakeMarker [isOnLand=" + isOnLand + ", radius=" + radius + ", clicked=" + clicked + ", color="
				+ color + ", strokeColor=" + strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor="
				+ highlightColor + ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location
				+ ", properties=" + properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}

		
}