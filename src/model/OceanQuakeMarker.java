package model;

import java.awt.AlphaComposite;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
				isOnLand = false;
	}
	

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//pg.stroke(204, 102, 0);

		pg.rect(x-radius, y-radius, 2*radius, 2*radius);
	

	}


	@Override
	public String toString() {
		return "OceanQuakeMarker [isOnLand=" + isOnLand + ", radius=" + radius + ", clicked=" + clicked + ", color="
				+ color + ", strokeColor=" + strokeColor + ", strokeWeight=" + strokeWeight + ", highlightColor="
				+ highlightColor + ", highlightStrokeColor=" + highlightStrokeColor + ", location=" + location
				+ ", properties=" + properties + ", selected=" + selected + ", hidden=" + hidden + ", id=" + id + "]";
	}
	

	

}
