package secondHandler;

import model.CommonMarker;
import model.menuMarker;

public class ConcreteHandlerSecond implements HandlerInterface {
	
	

	@Override
	public boolean markerSetClick() {
		if (CommonMarker.getLastClicked().equals(menuMarker.getMenuMarkerByIndex(1))) {
			menuMarker.secondIsClicked();
			return true;
		}
		return false;
	}

}
