package parsing;

import java.util.HashMap;
import java.util.Map;

public class Configurator {
private  Map<FileNames,String> fileNames=new HashMap<>();

public Configurator() {
	fileNames.put(FileNames.mbTilesString,"blankLight-1-3.mbtiles");
	fileNames.put(FileNames.mbTilesString2,"key");
	fileNames.put(FileNames.earthquakesURL,"https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom");
	fileNames.put(FileNames.URLearthquakesFile,"URL.txt");
	fileNames.put(FileNames.cityFile,"city-data.json");
	fileNames.put(FileNames.airFile,"airports.dat");
	fileNames.put(FileNames.routFile,"routes.dat");
	fileNames.put(FileNames.menuFile,"MenuMark.JSON");
	fileNames.put(FileNames.countryFile,"countries.geo.json");
	
}



public  void changeFileName(FileNames typeFile,String newName) {
	fileNames.put(typeFile,newName);
}
public  String getFileName(FileNames typeFile) {
	return fileNames.get(typeFile);
}


}
