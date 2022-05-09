package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import model.AirportMarker;
import model.CommonMarker;

public class AirDAOImplementation implements AirDAO{
	 public void setAirportList(List<Marker> airportList) {
	    	DataBase.getInstance().setAirportList(airportList);
	    }
	    public void addAirport(AirportMarker Marker) {
	    	DataBase.getInstance().addAirport(Marker);
	    }
	    public List<Marker> getAirportList(){
	    	return DataBase.getInstance().getAirportList();
	    }
	    public void checkAirForClick(Marker marker )
		{
		  DataBase.getInstance().checkAirForClick(marker);
					
		}
	  
}
