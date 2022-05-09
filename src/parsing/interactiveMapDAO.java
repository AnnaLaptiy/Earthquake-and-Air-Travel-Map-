package parsing;

import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import processing.core.PApplet;

public interface interactiveMapDAO {
	
	List<PointFeature> parseEarthquake(PApplet p);
	
	List<PointFeature> parseAirports(PApplet p);
	
	List<ShapeFeature> parseRoutes(PApplet p);
	 
	HashMap<String, Float> loadLifeExpectancyFromCSV(PApplet p, String fileName);
	

}
