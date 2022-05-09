package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;

public class QuakeDAOImplementation implements QuakeDAO{
	 public void setQuakeMarkers(List<Marker> quakeMarkers) {
	    	DataBase.getInstance().setQuakeMarkers(quakeMarkers);
	    }
	    public void addQuakeMarker(LandQuakeMarker Marker) {
	    	DataBase.getInstance().addQuakeMarker(Marker);
	    }
	    public void addQuakeMarker(OceanQuakeMarker Marker) {
	    	DataBase.getInstance().addQuakeMarker(Marker);
	    }
	    public List<Marker> getQuakeMarkers(){
	    	return DataBase.getInstance().getQuakeMarkers();
	    }
	    public void checkEarthquakesForClick(EarthquakeMarker marker)
		{
		  DataBase.getInstance().checkEarthquakesForClick(marker);
			
		}
//	    public void unhideQuakeMarkers() {
//			DataBase.getInstance().unhideQuakeMarkers();
//		}
	    public boolean isLand(PointFeature earthquake) {
			
			return DataBase.getInstance().isLand(earthquake);
		}
		
		
		public void printQuakes() {
			DataBase.getInstance().printQuakes();
		}
		
		
		public boolean isInCountry(PointFeature earthquake, Marker country) {
			return DataBase.getInstance().isInCountry(earthquake, country);
		}

}
