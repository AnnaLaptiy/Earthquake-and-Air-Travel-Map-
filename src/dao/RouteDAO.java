package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;

public interface RouteDAO {
	 public void setRouteList(List<Marker> routeList);
	    public void addRoute(SimpleLinesMarker Marker);
	    public List<Marker> getRouteList();
	    public void hideAirRouts();
	    public void hideRouteByAir();
	   // public void unhideRoutMarkers();
}
