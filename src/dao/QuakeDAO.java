package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;

public interface QuakeDAO {

	 public void setQuakeMarkers(List<Marker> quakeMarkers);
	    public void addQuakeMarker(LandQuakeMarker Marker);
	    public void addQuakeMarker(OceanQuakeMarker Marker);
	    public List<Marker> getQuakeMarkers();
	    public void checkEarthquakesForClick(EarthquakeMarker marker);
	  //  public void unhideQuakeMarkers();
	    public boolean isLand(PointFeature earthquake);
		
		public void printQuakes();
		
		public boolean isInCountry(PointFeature earthquake, Marker country);
}
