package Handler;

import de.fhpotsdam.unfolding.marker.Marker;
import interactiveMap.ITextField;
import interactiveMap.Service;
import interactiveMap.TextFieldRoute;
import interactiveMap.TextFieldTest;
import model.CommonMarker;
import model.menuMarker;

public class ConcreteHandlerMenu  implements HandlerInterface {

	
	

	@Override
	public boolean markerCheckClick(float mouseX,float mouseY) {
		CommonMarker lastClicked;
		for (Marker mark : menuMarker.getMenuMarkers()) {
			lastClicked = (CommonMarker) mark;
			if (mark.isInside(CommonMarker.getConsole(), mouseX, mouseY)) {
				checkEventsOnMap(mark);
				checkEventsWithInterface(mark, lastClicked);
				return true;
			}
		}
		return false;
	}
	private void checkEventsOnMap(Marker mark) {
		if (mark.equals(menuMarker.getMenuMarkerByIndex(0))) {
			menuMarker.hideCityMarkers(mark);
		}

		if (mark.equals(menuMarker.getMenuMarkerByIndex(1))) {
			menuMarker.hideQuakeMarkers(mark);
		}

		if (mark.equals(menuMarker.getMenuMarkerByIndex(2))) {
			menuMarker.hideAirMarkers(mark);
		}
	}
	private void checkEventsWithInterface(Marker mark, CommonMarker lastClicked) {
		if (menuMarker.getIndexOfMenu(mark) == 2) {
			
			TextFieldTest t=Service.getTextField();
			boolean fielHidden = showHideField(lastClicked, Service.isHiddenField(), t);
			Service.setFieldHidden(fielHidden);
			
		}

		if (menuMarker.getIndexOfMenu(mark) == 3) {
			boolean keyIsHidden = showHideInterface(lastClicked, Service.isHiddenKey());
			Service.setKeyHidden(keyIsHidden);
		}

		if (menuMarker.getIndexOfMenu(mark) == 4) {
			boolean infoIsHidden = showHideInterface(lastClicked, Service.isHiddenInfo());
			Service.setInfoHidden(infoIsHidden);
		}
		if (menuMarker.getIndexOfMenu(mark) == 5) {
			TextFieldRoute field = Service.getTextFieldRoute();
			boolean secondFieldHidden = showHideField(lastClicked, Service.isHiddenSecondField(), field);
			Service.setSecondFieldHidden(secondFieldHidden);
		}
	}
	private boolean showHideField(CommonMarker lastClicked, boolean isHidden, ITextField t) {
		if (isHidden) {
			t.showTextPanel();
			isHidden = false;
			lastClicked.setClicked(true);
		} else {
			t.hideTextPanel();
			isHidden = true;
			lastClicked.setClicked(false);
		}
		return isHidden;
	}

	private boolean showHideInterface(CommonMarker lastClicked, boolean isHidden) {
		if (isHidden) {
			isHidden = false;
			lastClicked.setClicked(false);
		} else {
			isHidden = true;
			lastClicked.setClicked(true);
		}
		return isHidden;
	}



}
