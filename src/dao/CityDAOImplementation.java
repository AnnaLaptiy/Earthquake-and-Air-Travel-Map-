package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import model.CityMarker;

public class CityDAOImplementation implements CityDAO{
	 public void setCityMarkers(List<Marker> cityMarkers) {
	    	DataBase.getInstance().setCityMarkers(cityMarkers);
	    }
	    public void addCityMarker(CityMarker city) {
	    	DataBase.getInstance().addCityMarker(city);
	    }
  public List<Marker> getCityMarkers(){
	    	
	    	return DataBase.getInstance().getCityMarkers();
	    }
	
  public void checkCitiesForClick(Marker marker)
	{
	  DataBase.getInstance().checkCitiesForClick(marker);
				
	}
//  public void unhideCityMarkers() {	
//		DataBase.getInstance().unhideCityMarkers();
//	}
}
