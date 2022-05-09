package secondHandler;

import model.CommonMarker;
import model.menuMarker;

public class ConcreteHandlerThird  implements HandlerInterface{

	@Override
	public boolean markerSetClick() {
		if (CommonMarker.getLastClicked().equals(menuMarker.getMenuMarkerByIndex(2))) {
			menuMarker.thirdIsClicked();
			return true;
		}
		return false;
	}

}
