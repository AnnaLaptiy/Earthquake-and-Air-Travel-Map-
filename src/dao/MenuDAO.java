package dao;

import java.util.List;

import dataBase.DataBase;
import de.fhpotsdam.unfolding.marker.Marker;
import model.CommonMarker;
import model.menuMarker;

public interface MenuDAO {
	    public void setMenuMarkers(List<Marker> menuMarkers);
	    public void addMenuMarker(menuMarker menuMarker);
	    public List<Marker> getMenuMarkers();
	    public void secondIsClicked();
		public void firstIsClicked();
		public void thirdIsClicked();

		
		//public int hideFirstSecondThird(Marker mark);
		public Marker getMenuMarkerByIndex(int index);
		public void hideMarkers(Marker mark,List<Marker> array,boolean hide);
		public void hideCityMarkers(Marker mark) ;
		public void hideQuakeMarkers(Marker mark);
		public void hideAirMarkers(Marker mark);
		public int getIndexOfMenu(Marker mark);
}
