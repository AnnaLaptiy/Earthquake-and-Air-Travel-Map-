package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import model.AirportMarker;
import model.CityMarker;
import model.CommonMarker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;
import model.menuMarker;

public interface MapDao {

	 public void setMap(UnfoldingMap map);
	 public void setSecMap(UnfoldingMap map);

	 public UnfoldingMap getMap();
	 public UnfoldingMap getMap2();
	    public void addMarkersToMap();
	    public void unselected();
	    public CommonMarker getLastSelected();
	    public CommonMarker getLastClicked();
	    public void setLastClickedNull();
	    public void selectByCountry(String country);
		public void selectMarkerIfHover(CommonMarker marker);
		public void unhideMarkers();
		//public void unhideMarkers(List<Marker> array);
		 public void addMenuMarkersToMap();
		 public List<String> getCountriesWithQuakes();
		 
//

}
