package parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import dto.AirportMarkerRequest;
import dto.LandQuakeMarkerRequest;
import dto.OceanQuakeMarkerRequest;
import model.AirportMarker;
import model.CityMarker;
import model.EarthquakeMarker;
import model.LandQuakeMarker;
import model.OceanQuakeMarker;
import model.menuMarker;
import processing.core.PApplet;

public class ProcessingParse extends PApplet {
	public ProcessingParse () {
		
	}
	
	private interactiveMapDAO parseDAO = new Parser();

	public void parseEarthquakes() {
		 List<PointFeature> earthquakes = parseDAO.parseEarthquake(this);
		    EarthquakeMarker.setQuakeMarkers(new ArrayList<Marker>()); 
		    
		    for(PointFeature feature : earthquakes) {
			  if(isLand(feature)) {
				  LandQuakeMarkerRequest l = new LandQuakeMarkerRequest(feature);//Проверка
				  EarthquakeMarker.addQuakeMarker(new LandQuakeMarker(feature));
			    
			  }
			  else {
		
				  OceanQuakeMarkerRequest o = new OceanQuakeMarkerRequest(feature);
				  EarthquakeMarker.addQuakeMarker(new OceanQuakeMarker(feature));
			  }
		    }
	}
	public void parseMenuMarkers() {
		List<Feature> menu = GeoJSONReader.loadData(this, Parser.getmenuFile());
		menuMarker.setMenuMarkers(new ArrayList<Marker>());
		for(Feature city : menu) {
			menuMarker.addMenuMarker(new menuMarker(city));

		}
	}
	
	public void parseCountries() {
		List<Feature> countries = GeoJSONReader.loadData(this, Parser.getcountryFile());
		CityMarker.setCountryMarkers(MapUtils.createSimpleMarkers(countries));


		List<Feature> cities = GeoJSONReader.loadData(this, Parser.getcityFile()); //Получает информацию из Parser
		CityMarker.setCityMarkers();

		
		for(Feature city : cities) {
			CityMarker.addCityMarker(new CityMarker(city));
		}
	}
	
	public void parseAirports() {
		 List<PointFeature> features = parseDAO.parseAirports(this);
	     AirportMarker.setAirportList(new ArrayList<Marker>());
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// создаем маркеры из feature, который мы получили из Parser
		for(PointFeature feature : features) {
			AirportMarkerRequest a=new AirportMarkerRequest(feature);
			AirportMarker m = new AirportMarker(feature);
	
			
			m.setRadius(5);
			AirportMarker.addAirport(m);
			
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// Парсим информацию для route 
		List<ShapeFeature> routes = parseDAO.parseRoutes(this);
		AirportMarker.setRouteList(new ArrayList<Marker>());
		for(ShapeFeature route : routes) {
			
			// находим место вылета и место прилета
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			String code=(String)route.getProperty("code");
			// зная локации аэропортов, строим маршруты
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
				
			}
			//Линия, показывающая маршрут
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
			AirportMarker.addRoute(sl);
		}
		
		
		AirportMarker.hideAirRouts();
	}
	private boolean isLand(PointFeature earthquake) {
		return EarthquakeMarker.isLand(earthquake);
		

	}
	
}
