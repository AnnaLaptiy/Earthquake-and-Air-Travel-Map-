package interactiveMap;

import model.AirportMarker;
import model.CityMarker;
import model.EarthquakeMarker;

public class Service {
	private static boolean keyIsHidden = false;
	private static boolean infoIsHidden = false;
	private static boolean fielHidden = false;
	private static boolean secondFieldHidden = false;
	private static TextFieldTest t;
	private static TextFieldRoute field;

	public static void setFields() {
		t = new TextFieldTest(AirportMarker.getAirportList(), EarthquakeMarker.getQuakeMarkers(),
				CityMarker.getCityMarkers());

		
		field = new TextFieldRoute(AirportMarker.getAirportList(), EarthquakeMarker.getQuakeMarkers(),
				CityMarker.getCityMarkers(), AirportMarker.getRouteList());
	}
   

	// private interactiveMapDAO parseDAO = new Parser();

	public static void setKeyHidden(boolean hidden) {
		keyIsHidden=hidden;
	}
	public static void setInfoHidden(boolean hidden) {
		infoIsHidden=hidden;
	}
	public static void setFieldHidden(boolean hidden) {
		fielHidden=hidden;
	}
	public static void setSecondFieldHidden(boolean hidden) {
		secondFieldHidden=hidden;
	}
	public static boolean isHiddenKey() {
		return keyIsHidden;
	}
	public static boolean isHiddenInfo() {
		return infoIsHidden;
	}
	
	public static boolean isHiddenField() {
		return fielHidden;
	}
	public static boolean isHiddenSecondField() {
		return secondFieldHidden;
	}
	
	public static TextFieldTest getTextField() {
		return t;
	}
	public  static TextFieldRoute getTextFieldRoute() {
		return field;
	}
}
