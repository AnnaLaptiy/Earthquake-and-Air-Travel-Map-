package Handler;

import de.fhpotsdam.unfolding.marker.Marker;
import model.CityMarker;
import model.CommonMarker;
import model.EarthquakeMarker;
import processing.core.PApplet;

public class ConcreteHandlerEarthquakes  implements HandlerInterface {

	

	@Override
	public boolean markerCheckClick(float mouseX,float mouseY) {
		for (Marker m : EarthquakeMarker.getQuakeMarkers()) {
			EarthquakeMarker marker = (EarthquakeMarker)m;
			if ( marker.isInside(CommonMarker.getMap(), mouseX, mouseY)) {
				EarthquakeMarker.checkEarthquakesForClick(marker);
				return true;
				
			}
		}
		return false;
	}

}
