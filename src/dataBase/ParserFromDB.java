package dataBase;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import de.fhpotsdam.unfolding.marker.Marker;

public class ParserFromDB implements RepInterface {
	public void writeToFile(List<Marker> list,String fileName) {
		try(FileWriter writer = new FileWriter(fileName, false))
        {
   		 for(Marker marker:list) {
   			// System.out.println(marker.toString());
            writer.write(marker.toString());
   		//	 writer.write("eee");
            writer.append('\n');
           // writer.append('E');
   		 }
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }  
	}

}
