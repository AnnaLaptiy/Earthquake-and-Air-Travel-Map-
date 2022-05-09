package dao;

import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import model.AirportMarker;
import model.CityMarker;
import model.CommonMarker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;
import model.menuMarker;

public class MapDaoImplementation implements MapDao {
	
	 public void setMap(UnfoldingMap map) {
			DataBase.getInstance().setMap(map);
	    }
	 public void setSecMap(UnfoldingMap map) {
			DataBase.getInstance().setSecMap(map);
	    } 
	 public UnfoldingMap getMap2() {
		 return DataBase.getInstance().getMap2();
	    }
	 public UnfoldingMap getMap() {
		 return DataBase.getInstance().getMap();
	    }
//	 public void setCountryMarkers(List<Marker> countryMarkers) {
//	    	DataBase.getInstance().setCountryMarkers((countryMarkers));
//	    }
	 
	    public void addMarkersToMap() {
	    	DataBase.getInstance().addMarkersToMap();
	    }
	    public List<String> getCountriesWithQuakes(){
	    	return DataBase.getInstance().getCountriesWithQuakes();
	    }
	    
	    public List<Marker> getCountryMarkers(){
	    	return  DataBase.getInstance().getCountryMarkers();
	    }
	    public void addMenuMarkersToMap() {
	    	DataBase.getInstance().addMenuMarkersToMap();
	    }
//	    public void unhideMarkers(List<Marker> array) {
//	    	DataBase.getInstance().unhideMarkers(array);
//	    }
	    ////
	    
//	    public void setRouteList(List<Marker> routeList) {
//	    	DataBase.getInstance().setRouteList(routeList);
//	    }
//	    public void addRoute(SimpleLinesMarker Marker) {
//	    	DataBase.getInstance().addRoute(Marker);
//	    }
//	    public List<Marker> getRouteList(){
//	    	return DataBase.getInstance().getRouteList();
//	    }
	    ////
	    
//	    
//	    public void setAirportList(List<Marker> airportList) {
//	    	DataBase.getInstance().setAirportList(airportList);
//	    }
//	    public void addAirport(AirportMarker Marker) {
//	    	DataBase.getInstance().addAirport(Marker);
//	    }
	    ////
	    
//	    public void setQuakeMarkers(List<Marker> quakeMarkers) {
//	    	DataBase.getInstance().setQuakeMarkers(quakeMarkers);
//	    }
//	    public void addQuakeMarker(LandQuakeMarker Marker) {
//	    	DataBase.getInstance().addQuakeMarker(Marker);
//	    }
//	    public void addQuakeMarker(OceanQuakeMarker Marker) {
//	    	DataBase.getInstance().addQuakeMarker(Marker);
//	    }
	    ///
	  
//	    public void setCityMarkers(List<Marker> cityMarkers) {
//	    	DataBase.getInstance().setCityMarkers(cityMarkers);
//	    }
//	    public void addCityMarker(CityMarker city) {
//	    	DataBase.getInstance().addCityMarker(city);
//	    }
	    ///
//	    public void setMenuMarkers(List<Marker> menuMarkers) {
//	    	DataBase.getInstance().setMenuMarkers(menuMarkers);
//	    }
//	    public void addMenuMarker(menuMarker menuMarker) {
//	    	DataBase.getInstance().addMenuMarker(menuMarker);;
//	    }
	    
	    public void unselected() {
	    	DataBase.getInstance().unselected();
	    }
	    
//	    public void hideAirRouts() {
//	    	DataBase.getInstance().hideAirRouts();
//	    }
//	    
//	    public List<Marker> getMenuMarkers(){
//	    	return DataBase.getInstance().getMenuMarkers();
//	    }
//	    public List<Marker> getCityMarkers(){
//	    	
//	    	return DataBase.getInstance().getCityMarkers();
//	    }
//	    public List<Marker> getAirportList(){
//	    	return DataBase.getInstance().getAirportList();
//	    }
//	    public List<Marker> getQuakeMarkers(){
//	    	return DataBase.getInstance().getQuakeMarkers();
//	    }
//	    
	    public CommonMarker getLastSelected() {
	    	return DataBase.getInstance().getLastSelected();
	    }
	    public CommonMarker getLastClicked() {
	    	return DataBase.getInstance().getLastClicked();
	    }
	    public void setLastClickedNull() {
	    	DataBase.getInstance().setLastClickedNull();
	    }
	    
	    public void selectByCountry(String country) {
	    	DataBase.getInstance().selectByCountry(country);
		}
		
		public void selectMarkerIfHover(CommonMarker marker)
		{
			DataBase.getInstance().selectMarkerIfHover(marker);	
		}
		
		
//		public void hideRouteByAir() {
//			DataBase.getInstance().hideRouteByAir();
//		}
//		public void secondIsClicked() {
//			DataBase.getInstance().secondIsClicked();
//		}
//		public void firstIsClicked() {
//			DataBase.getInstance().firstIsClicked();
//		}
//		public void thirdIsClicked() {
//			DataBase.getInstance().thirdIsClicked();
//		}
//		

		
//		public void hidefirst(Marker mark) {
//			DataBase.getInstance().hidefirst(mark);
//		}
//		public void hidesecond(Marker mark) {
//			DataBase.getInstance().hidesecond(mark);
//		}
//		public void hidethird(Marker mark) {
//			DataBase.getInstance().hidethird(mark);
//			}
//		
		
		
		
		
		
//	  public void checkCitiesForClick(Marker marker)
//		{
//		  DataBase.getInstance().checkCitiesForClick(marker);
//					
//		}
//	  public void checkAirForClick(Marker marker )
//		{
//		  DataBase.getInstance().checkAirForClick(marker);
//					
//		}
//	  public void checkEarthquakesForClick(EarthquakeMarker marker)
//		{
//		  DataBase.getInstance().checkEarthquakesForClick(marker);
//			
//		}
		
		// loop over and unhide all markers
		public void unhideMarkers() {
			DataBase.getInstance().unhideMarkers();
		}
//		public void unhideQuakeMarkers() {
//			DataBase.getInstance().unhideQuakeMarkers();
//		}
//		public void unhideRoutMarkers() {
//			DataBase.getInstance().unhideRoutMarkers();
//		}
//		public void unhideCityMarkers() {	
//			DataBase.getInstance().unhideCityMarkers();
//		}
//		public void unhideAirportMarkers() {	
//			DataBase.getInstance().unhideAirportMarkers();
//		}
//		
		
		
//		public boolean isLand(PointFeature earthquake) {
//		
//			return DataBase.getInstance().isLand(earthquake);
//		}
//		
//		
//		public void printQuakes() {
//			DataBase.getInstance().printQuakes();
//		}
//		
//		
//		public boolean isInCountry(PointFeature earthquake, Marker country) {
//			return DataBase.getInstance().isInCountry(earthquake, country);
//		}



}
