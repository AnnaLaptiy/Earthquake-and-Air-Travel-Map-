package secondHandler;

import secondHandler.HandlerInterface;
import model.CommonMarker;
import model.menuMarker;

public class ConcreteHandlerFirst implements HandlerInterface{
	

	@Override
	public boolean markerSetClick() {
		if (CommonMarker.getLastClicked().equals(menuMarker.getMenuMarkerByIndex(0))) {
			menuMarker.firstIsClicked();
			return true;
		}
		return false;
	}
}
