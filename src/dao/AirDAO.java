package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import model.AirportMarker;
import model.CommonMarker;

public interface AirDAO {
	 public void setAirportList(List<Marker> airportList);
	    public void addAirport(AirportMarker Marker);
	    public List<Marker> getAirportList();
	    public void checkAirForClick(Marker marker );
}
