package Handler;

import de.fhpotsdam.unfolding.marker.Marker;
import model.AirportMarker;
import model.CityMarker;
import model.CommonMarker;
import processing.core.PApplet;

public class ConcreteHandlerCities implements HandlerInterface {

	@Override
	public boolean markerCheckClick(float mouseX,float mouseY) {
		for (Marker marker : CityMarker.getCityMarkers()) {
			if ( marker.isInside(CommonMarker.getMap(), mouseX, mouseY)) {            
				CityMarker.checkCitiesForClick(marker);
				return true;
				
			}
		}
		return false;
	}

}

