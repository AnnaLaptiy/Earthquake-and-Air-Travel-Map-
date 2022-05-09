package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;

public interface CountryDAO {
	 public List<String> getCountriesWithQuakes();
	    
	    public List<Marker> getCountryMarkers();
	    public void setCountryMarkers(List<Marker> countryMarkers);
}
