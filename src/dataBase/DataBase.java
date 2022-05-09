package dataBase;


import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import model.AirportMarker;
import model.CityMarker;
import model.CommonMarker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;
import model.menuMarker;

public class DataBase {
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	private List<String> countriesWithQuakes;
	private List<Marker> menuMarkers;
	private List<Marker> cityMarkers;
	private List<Marker> quakeMarkers;
	private List<Marker> countryMarkers;
	private List<Marker> airportList;
	private List<Marker> routeList = new ArrayList<>();

	private CommonMarker lastSelected;
	private CommonMarker lastClicked;

	private UnfoldingMap map;
	private UnfoldingMap consolePanel;

	private RepInterface parser = new ParserFromDB();
	private static DataBase instance; // #1

	private DataBase() {
	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	public void setMap(UnfoldingMap map) {
		this.map = map;
	}

	public void setSecMap(UnfoldingMap map) {
		this.consolePanel = map;
	}

	public UnfoldingMap getMap2() {
		return consolePanel;
	}

	public UnfoldingMap getMap() {
		return map;
	}

	public void addMarkersToMap() {
		map.addMarkers(DataBase.getInstance().getRouteList());
		map.addMarkers(DataBase.getInstance().getQuakeMarkers());
		map.addMarkers(DataBase.getInstance().getCityMarkers());
		map.addMarkers(DataBase.getInstance().getAirportList());
	}

	public void addMenuMarkersToMap() {
		consolePanel.addMarkers(DataBase.getInstance().getMenuMarkers());
	}

	public List<String> getCountriesWithQuakes() {
		return countriesWithQuakes;
	}

	public void setRouteList(List<Marker> routeList) {
		this.routeList = routeList;
	}

	public List<Marker> getRouteList() {
		return routeList;
	}

	public void addRoute(SimpleLinesMarker Marker) {
		this.routeList.add(Marker);
	}

	public void setAirportList(List<Marker> airportList) {
		this.airportList = airportList;
	}

	public List<Marker> getAirportList() {
		return airportList;
	}

	public void addAirport(AirportMarker Marker) {
		this.airportList.add(Marker);
	}

	public void setQuakeMarkers(List<Marker> quakeMarkers) {
		this.quakeMarkers = quakeMarkers;
	}

	public void addQuakeMarker(LandQuakeMarker Marker) {
		this.quakeMarkers.add(Marker);
		parser.writeToFile(quakeMarkers, "quakeBD.txt");
	}

	public void addQuakeMarker(OceanQuakeMarker Marker) {
		this.quakeMarkers.add(Marker);
		parser.writeToFile(quakeMarkers, "quakeBD.txt");
	}

	public List<Marker> getQuakeMarkers() {
		return quakeMarkers;
	}

	public List<Marker> getCountryMarkers() {
		return countryMarkers;
	}

	public void setCountryMarkers(List<Marker> countryMarkers) {
		this.countryMarkers = countryMarkers;
	}

	public void setCityMarkers(List<Marker> cityMarkers) {
		this.cityMarkers = cityMarkers;
	}

	public List<Marker> getCityMarkers() {
		return cityMarkers;
	}

	public void addCityMarker(CityMarker city) {
		this.cityMarkers.add(city);
		parser.writeToFile(cityMarkers, "cityBD.txt");
	}

	public void setMenuMarkers(List<Marker> menuMarkers) {
		this.menuMarkers = menuMarkers;
	}

	public List<Marker> getMenuMarkers() {
		return menuMarkers;
	}

	public void addMenuMarker(menuMarker menuMarker) {
		menuMarkers.add(menuMarker);
		parser.writeToFile(menuMarkers, "menuBD.txt");

	}

	public void unselected() {
		lastSelected.setSelected(false);
		lastSelected = null;
	}

	public void hideAirRouts() {

		for (Marker r : routeList) {
			r.setHidden(true);

		}
		for (Marker ai : airportList) {
			ai.setHidden(true);

		}
		parser.writeToFile(airportList, "airportBD.txt");
	}

	public CommonMarker getLastSelected() {
		return lastSelected;
	}

	public CommonMarker getLastClicked() {
		return lastClicked;
	}

	public void setLastClickedNull() {
		lastClicked = null;
	}

	public void selectByCountry(String country) {

		if (country != null && !country.isEmpty()) {
			for (Marker r : airportList) {

				if (r.getProperty("country").toString().equals(country)) {
					r.setHidden(false);
				} else {
					r.setHidden(true);
				}
			}
			for (Marker r : quakeMarkers) {

				String quakeTitle = r.getProperty("title").toString();
				String quakeCountry = quakeTitle.substring(quakeTitle.lastIndexOf(','));

				if (quakeCountry.equals(country)) {
					r.setHidden(false);
				} else {
					r.setHidden(true);
				}

			}
			for (Marker r : cityMarkers) {

				if (r.getProperty("country").toString().equals(country)) {
					r.setHidden(false);
				} else {
					r.setHidden(true);
				}
			}
		}

	}

	public void selectMarkerIfHover(CommonMarker marker) {
		lastSelected = marker;
		marker.setSelected(true);
		return;

	}

	public void hideRouteByAir() {
		if (airportList.contains(lastClicked)) {

			AirportMarker r = (AirportMarker) lastClicked;
			String airStr = r.getFeature().getId().toString();

			for (Marker rout : routeList) {

				String routStr = rout.getProperty("destination").toString();
				String sourceStr = rout.getProperty("source").toString();

				if (routStr.equals(airStr) || sourceStr.equals(airStr)) {
					rout.setHidden(true);
				}
			}
		}
	}

	public void secondIsClicked() {
		// if(lastClicked.equals(menuMarkers.get(1))) {
		lastClicked.setClicked(false);
		unhideMarkers(quakeMarkers);

		// }
	}

	public void firstIsClicked() {
		// if(lastClicked.equals(menuMarkers.get(0))) {
		lastClicked.setClicked(false);
		unhideMarkers(cityMarkers);
		// }
	}

	public void thirdIsClicked() {
		// if(lastClicked.equals(menuMarkers.get(2))) {
		lastClicked.setClicked(true);
		unhideMarkers(airportList);
		// }
	}

	public void hideMarkers(Marker mark, List<Marker> array, boolean hide) {
		lastClicked = (CommonMarker) mark;
		lastClicked.setClicked(hide);
		for (Marker mhide : array) {
			mhide.setHidden(true);

		}

	}

	public void hideCityMarkers(Marker mark) { //
		lastClicked = (CommonMarker) mark;
		lastClicked.setClicked(true);
		for (Marker mhide : cityMarkers) {
			mhide.setHidden(true);

		}

	}

	public void hideQuakeMarkers(Marker mark) {
		lastClicked = (CommonMarker) mark;
		lastClicked.setClicked(true);
		for (Marker mhide : quakeMarkers) {
			mhide.setHidden(true);

		}

	}

	public void hideAirMarkers(Marker mark) {
		lastClicked = (CommonMarker) mark;
		lastClicked.setClicked(false);
		for (Marker mhide : airportList) {
			mhide.setHidden(true);

		}
		for (Marker mhide : routeList) {
			mhide.setHidden(true);

		}

	}

	public int getIndexOfMenu(Marker mark) {
		return (menuMarkers).indexOf(mark) - 1;
	}

	private void hideMarkers(Marker marker, List<Marker> array) {
		lastClicked = (CommonMarker) marker;
		for (Marker mhide : array) {
			if (mhide != lastClicked) {
				mhide.setHidden(true);
			}
		}
	}

	public void checkCitiesForClick(Marker marker) {
		if (!marker.isHidden()) {
			hideMarkers(marker, cityMarkers);
			for (Marker mhide : quakeMarkers) {
				EarthquakeMarker quakeMarker = (EarthquakeMarker) mhide;
				if (quakeMarker.getDistanceTo(marker.getLocation()) > quakeMarker.threatCircle()) {
					quakeMarker.setHidden(true);
				}
			}
			return;
		}

	}

	public void checkAirForClick(Marker marker) {
		if (!marker.isHidden()) {

			hideMarkers(marker, airportList);
			for (Marker m : routeList) {
				AirportMarker r = (AirportMarker) marker;

				String airStr = r.getFeature().getId().toString();
				String routStr = m.getProperty("destination").toString();
				String sourceStr = m.getProperty("source").toString();

				if (routStr.equals(airStr) || sourceStr.equals(airStr)) {
					m.setHidden(false);

					for (Marker mhide : airportList) {
						AirportMarker otherAiroports = (AirportMarker) mhide;
						String airID = otherAiroports.getFeature().getId().toString();
						if (routStr.equals(airID) || sourceStr.equals(airID)) {
							mhide.setHidden(false);
						}
					}
				}
			}

		}
	}

	public void checkEarthquakesForClick(Marker marker) {
		if (!marker.isHidden()) {

			hideMarkers(marker, quakeMarkers);

			EarthquakeMarker earth = (EarthquakeMarker) marker;
			for (Marker mhide : cityMarkers) {
				if (mhide.getDistanceTo(marker.getLocation()) > earth.threatCircle()) {
					mhide.setHidden(true);
				}
			}
			return;
		}

	}

	public void unhideMarkers() {
		unhideMarkers(quakeMarkers);
		unhideMarkers(routeList);
		unhideMarkers(cityMarkers);
		unhideMarkers(airportList);
	}

	private void unhideMarkers(List<Marker> array) {
		for (Marker marker : array) {
			marker.setHidden(false);
		}
	}

	public boolean isLand(PointFeature earthquake) {
		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}
		}
		return false;
	}

	public void printQuakes() {
		countriesWithQuakes = new ArrayList<>();
		int totalWaterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quakeMarkers) {
				EarthquakeMarker eqMarker = (EarthquakeMarker) marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				countriesWithQuakes.add(countryName + ": " + numQuakes);
				// System.out.println(countryName + ": " + numQuakes);
			}
		}
		// System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
		countriesWithQuakes.add("OCEAN QUAKES: " + totalWaterQuakes);
		int res = quakeMarkers.size() - totalWaterQuakes;
		countriesWithQuakes.add("LAND QUAKES: " + res);
	}

	public boolean isInCountry(PointFeature earthquake, Marker country) {
		Location checkLoc = earthquake.getLocation();
		if (country.getClass() == MultiMarker.class) {

			for (Marker marker : ((MultiMarker) country).getMarkers()) {

				if (((AbstractShapeMarker) marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
					return true;
				}
			}
		}

		else if (((AbstractShapeMarker) country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));

			return true;
		}
		return false;
	}

	public Marker getMenuMarkerByIndex(int index) {
		return menuMarkers.get(index);
	}

}
