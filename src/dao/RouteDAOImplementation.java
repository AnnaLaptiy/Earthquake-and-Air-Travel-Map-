package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;

public class RouteDAOImplementation implements RouteDAO{
	  public void setRouteList(List<Marker> routeList) {
	    	DataBase.getInstance().setRouteList(routeList);
	    }
	    public void addRoute(SimpleLinesMarker Marker) {
	    	DataBase.getInstance().addRoute(Marker);
	    }
	    public List<Marker> getRouteList(){
	    	return DataBase.getInstance().getRouteList();
	    }
	    public void hideAirRouts() {
	    	DataBase.getInstance().hideAirRouts();
	    }
	    public void hideRouteByAir() {
			DataBase.getInstance().hideRouteByAir();
		}
//	    public void unhideRoutMarkers() {
//			DataBase.getInstance().unhideRoutMarkers();
//		}

}
