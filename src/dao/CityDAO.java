package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import model.CityMarker;

public interface CityDAO {
	 public void setCityMarkers(List<Marker> cityMarkers);
	    public void addCityMarker(CityMarker city);
public List<Marker> getCityMarkers();
	
public void checkCitiesForClick(Marker marker);
//public void unhideCityMarkers();
}
