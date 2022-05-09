package parsing;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PApplet;
import processing.data.XML;

public class Parser implements interactiveMapDAO{

	static Configurator config=new Configurator();
	private static final boolean offline = false;
//	public static String mbTilesString = "blankLight-1-3.mbtiles";
//	public static String mbTilesString2 = "key";
//
//	private static String earthquakesURL="https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
//	private static String URLearthquakesFile ="URL.txt";
//	private static String cityFile = "city-data.json";
//	private static String airFile = "airports.dat";
//	private static String routFile = "routes.dat";
//
//	private static String menuFile = "MenuMark.JSON";
//	private static String countryFile = "countries.geo.json";
	
	
	public static String getcityFile() {
		//return cityFile;
		return config.getFileName(FileNames.cityFile);
	}
	public static String getmenuFile() {
		//return menuFile;
		return config.getFileName(FileNames.menuFile);

	}
	public static String getcountryFile() {
		//return countryFile;
		return config.getFileName(FileNames.countryFile);
	}
	public static String getearthquakesURL() {
		//return earthquakesURL;
		return config.getFileName(FileNames.earthquakesURL);

	}
	public static void offnlineearthquakesURL() {
		// earthquakesURL="2.5_week.atom";
		
		config.changeFileName(FileNames.earthquakesURL, "2.5_week.atom");
	}
	public static String getmbTilesString() {
		//return mbTilesString;
		return config.getFileName(FileNames.mbTilesString);

	}
	public static String getmbTilesString2() {
		//return mbTilesString2;
		return config.getFileName(FileNames.mbTilesString2);

	}
	
	
	public static void readURL () {
		char[] buf=null;
	//	 try(FileReader reader = new FileReader(URLearthquakesFile))
		 try(FileReader reader = new FileReader(config.getFileName(FileNames.URLearthquakesFile)))

	        {
	             buf = new char[256];
	            int c;
	            while((c = reader.read(buf))>0){
	                 
	                if(c < 256){
	                    buf = Arrays.copyOf(buf, c);
	                }
	                System.out.print(buf);
	            } 
	        }
		 
	        catch(IOException ex){
	        	ex.printStackTrace();
	        }
		 
		 //earthquakesURL=String.valueOf(buf);
		 config.changeFileName(FileNames.earthquakesURL, String.valueOf(buf));
	}
	

	
	public  List<PointFeature> parseEarthquake(PApplet p) {
		List<PointFeature> features = new ArrayList<PointFeature>();
		//XML rss = p.loadXML(earthquakesURL);
		XML rss = p.loadXML(config.getFileName(FileNames.earthquakesURL));

		XML[] itemXML = rss.getChildren("entry");
		PointFeature point;
		
		for (int i = 0; i < itemXML.length; i++) {
			
				// получает локацию и создаем feature 
				Location location = getLocationFromPoint(itemXML[i]);
				
				// если удалось, то создаем PointFeature и добавляем в list
				if( location != null) {
					point = new PointFeature(location);
					features.add(point);
				}
				else {
					continue;
				}

				// Устанавливаем заголовок, если он существует
				String titleStr = getStringVal(itemXML[i], "title");
				if (titleStr != null) {
					point.putProperty("title", titleStr);
					//по заголовку получаем магнитуду
					point.putProperty("magnitude", Float.parseFloat(titleStr.substring(2, 5)));
				}

				// устанавливаем глубину, если она есть
				float depthVal = getFloatVal(itemXML[i], "georss:elev");
				
				// получаем один десятичный знак при преобразовании в км
				int interVal = (int)(depthVal/100);
				depthVal = (float) interVal/10;
				point.putProperty("depth", Math.abs((depthVal)));
				

				// устанавливаем age если он есть
				XML[] catXML = itemXML[i].getChildren("category");
				for (int c = 0; c < catXML.length; c++) {
					String label = catXML[c].getString("label");
					if ("Age".equals(label)) {
						String ageStr = catXML[c].getString("term");
						point.putProperty("age", ageStr);
					}
				}
		

			}
		
			return features;
		}

	
	/*
	 *Получаем местоположение от georss:point
	 */
	private  Location getLocationFromPoint(XML itemXML) {
		// устанавливаем для loc значение null в случае сбоя
		Location loc = null;
		XML pointXML = itemXML.getChild("georss:point");
		
		// устанавливаем location 
		if (pointXML != null && pointXML.getContent() != null) {
			String pointStr = pointXML.getContent();
			String[] latLon = pointStr.split(" ");
			float lat = Float.valueOf(latLon[0]);
			float lon = Float.valueOf(latLon[1]);

			loc = new Location(lat, lon);
		}
		
		return loc;
	}	
	
	private  String getStringVal(XML itemXML, String tagName) {
		// устанавливаем заголовок
		String str = null;
		XML strXML = itemXML.getChild(tagName);
		
		// проверка содержимого
		if (strXML != null && strXML.getContent() != null) {
			str = strXML.getContent();
		}
		
		return str;
	}
	
	
	private  float getFloatVal(XML itemXML, String tagName) {
		return Float.parseFloat(getStringVal(itemXML, tagName));
	}
	

	/*
	 * Этот метод предназначен для анализа файла, содержащего информацию об аэропорте.
	 * http://openflights.org/data.html#airport
	 * 
	 */
	public  List<PointFeature> parseAirports(PApplet p) {
		List<PointFeature> features = new ArrayList<PointFeature>();

		//String[] rows = p.loadStrings(airFile);
		String[] rows = p.loadStrings(config.getFileName(FileNames.airFile));

		for (String row : rows) {
			
			int i = 0;
			
			// разбиваем строки
			String[] columns = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			
			// получаем location и создаем feature
			//System.out.println(columns[6]);
			float lat = Float.parseFloat(columns[6]);
			float lon = Float.parseFloat(columns[7]);
			
			Location loc = new Location(lat, lon);
			PointFeature point = new PointFeature(loc);
			
			// устанавливаем ID 
			point.setId(columns[0]);
			
			// получаем остальные поля из csv
			point.addProperty("name", columns[1]);
			point.putProperty("city", columns[2]);
			point.putProperty("country", columns[3]);
			
			if(!columns[4].equals("")) {
				point.putProperty("code", columns[4]);
			}
			else if(!columns[5].equals("")) {
				point.putProperty("code", columns[5]);
			}
			
			point.putProperty("altitude", columns[8 + i]);
			
			features.add(point);
		}

		return features;
		
	}
	
	

	/*
	 * Этот метод предназначен для анализа файла, содержащего информацию о маршруте аэропорта.
	 * http://openflights.org/data.html#route
	 */
	public  List<ShapeFeature> parseRoutes(PApplet p) {
		List<ShapeFeature> routes = new ArrayList<ShapeFeature>();
		
		//String[] rows = p.loadStrings(routFile);
		String[] rows = p.loadStrings(config.getFileName(FileNames.routFile));

		
		for(String row : rows) {
			String[] columns = row.split(",");
			
			ShapeFeature route = new ShapeFeature(Feature.FeatureType.LINES);
			
			// устанавливаем id в качестве идентификатора OpenFlights для исходного аэропорта
			
			//проверка, что оба аэропорта на маршруте имеют идентификатор OpenFlights
			if(!columns[3].equals("\\N") && !columns[5].equals("\\N")){
				// устанавливаем "source" для аэропорта-источника
				route.putProperty("source", columns[3]);
				// "destination property" -- OpenFlights id
				route.putProperty("destination", columns[5]);
				
				routes.add(route);
			}
		}
			
		
		return routes;
		
		
		
	}
	
	

	public  HashMap<String, Float> loadLifeExpectancyFromCSV(PApplet p, String fileName) {
		HashMap<String, Float> lifeExpMap = new HashMap<String, Float>();

		String[] rows = p.loadStrings(fileName);
		
		for (String row : rows) {
			String[] columns = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			
			//
			for(int i = columns.length - 1; i > 3; i--) {
				
				if(!columns[i].equals("..")) {
					lifeExpMap.put(columns[3], Float.parseFloat(columns[i]));
					
					break;
				}
			}
			
		}

		return lifeExpMap;
	}
	
	

}