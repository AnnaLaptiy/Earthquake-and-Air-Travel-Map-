package interactiveMap;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import model.AirportMarker;

public class TextFieldRoute extends JFrame implements ITextField
{
    // Текстовые поля
    private JTextField smallField, bigField;
    JPanel contents;
    //EarthquakeCityMap map=new EarthquakeCityMap();
    private static String start,end;
    List<Marker> way = new ArrayList<>();
    public  TextFieldRoute(List<Marker> airportList,List<Marker> quakeMarkers,List<Marker> cityMarkers, List<Marker> routeList)
    {
        super("Build air routes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание текстовых полей
        smallField = new JTextField(15);
        smallField.setToolTipText("Country");
        bigField = new JTextField(15);
        
        smallField.setToolTipText("Введите страну вылета");
        
        bigField.setToolTipText("Введите страну назначения");
        // Настройка шрифта
       // bigField.setFont(new Font("Dialog", Font.PLAIN, 14));
        bigField.setHorizontalAlignment(JTextField.RIGHT);
        // Слушатель окончания ввода
        
        
        
        bigField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(TextFieldRoute.this, 
                               "Прямые рейсы из страны: " + smallField.getText()+"\n в страну: " + bigField.getText()+"\n построены");
                
                
                start=smallField.getText().toString().trim();
                end=bigField.getText().toString().trim();
                if(start!=null&&!start.isEmpty()&&end!=null&&!end.isEmpty()) {
                	String countryName;
                	hideOtherMarkers( airportList,routeList);
        			for(Marker r:airportList) {

        				countryName=r.getProperty("country").toString();
        				String airportCountry=countryName.substring(1,countryName.length()-1);
        				
        				if(airportCountry.equals(start)) {//Нашли начальный
        				
        				
        					 for(Marker m:routeList) {
        						 AirportMarker air=(AirportMarker)r;
        						 
        						 String airStr=air.getFeature().getId().toString();
        						 String routStr=m.getProperty("destination").toString();
        						 String sourceStr=m.getProperty("source").toString();
        						 
        						 if(routStr.equals(airStr)||sourceStr.equals(airStr)) { //Нашли смежные пути
        	           			 
        	           			  
        	      				for (Marker mhide : airportList) {
        	      					AirportMarker otherAiroports=(AirportMarker)mhide;
        	    					 String airID=otherAiroports.getFeature().getId().toString();
        	  					if (routStr.equals(airID)||sourceStr.equals(airID)) {  //Нашли соседние аэропорты
        	  						String f=otherAiroports.getProperty("country").toString();
        	  						
        	        				String s=f.substring(1,f.length()-1);
        	  						if(s.equals(end)) {
        	  							 m.setHidden(false);
        	  						     mhide.setHidden(false);
        	        					 r.setHidden(false);

        	  						}

        	  					}
        	  				}
        	           		  }
        	           	  }

        				}
        		 }
        			 
        	
        		}
                
                
                
                
               // country=smallField.getText().toString();
               // System.out.println(smallField.getText());
            }
        });
        // Создание панели с текстовыми полями
         contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(smallField);
        contents.add(bigField  );
       // contents.add(password  );
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setSize(280, 100);
        setVisible(false);
        
     //   System.out.println(country);
    }
    private void hideOtherMarkers(List<Marker> airportList, List<Marker> routeList) {
    		 for(Marker marker:airportList) {
    			 marker.setHidden(true);
    		 }
    		 for(Marker marker:routeList) {
    			 marker.setHidden(true);
    		 }
  	}
    
    public void showTextPanel() {
        setVisible(true);

    }
    public void hideTextPanel() {
         setVisible(false);
        // dispose();

       //  contents.removeAll();

    }
   

    
}