package Handler;

import de.fhpotsdam.unfolding.marker.Marker;
import model.AirportMarker;
import model.CommonMarker;
import model.menuMarker;
import processing.core.PApplet;

public class ConcreteHandlerAirports  implements HandlerInterface {

	@Override
	public boolean markerCheckClick(float mouseX,float mouseY) {
		for (Marker marker : AirportMarker.getAirportList()) {
			if ( marker.isInside(CommonMarker.getMap(), mouseX, mouseY)) {
				AirportMarker.checkAirForClick(marker);
				//break;
			}
				
		}
		return false;
	}

}

