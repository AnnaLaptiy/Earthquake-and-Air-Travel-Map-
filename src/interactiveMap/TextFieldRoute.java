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
    // ��������� ����
    private JTextField smallField, bigField;
    JPanel contents;
    //EarthquakeCityMap map=new EarthquakeCityMap();
    private static String start,end;
    List<Marker> way = new ArrayList<>();
    public  TextFieldRoute(List<Marker> airportList,List<Marker> quakeMarkers,List<Marker> cityMarkers, List<Marker> routeList)
    {
        super("Build air routes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // �������� ��������� �����
        smallField = new JTextField(15);
        smallField.setToolTipText("Country");
        bigField = new JTextField(15);
        
        smallField.setToolTipText("������� ������ ������");
        
        bigField.setToolTipText("������� ������ ����������");
        // ��������� ������
       // bigField.setFont(new Font("Dialog", Font.PLAIN, 14));
        bigField.setHorizontalAlignment(JTextField.RIGHT);
        // ��������� ��������� �����
        
        
        
        bigField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ����������� ���������� ������
                JOptionPane.showMessageDialog(TextFieldRoute.this, 
                               "������ ����� �� ������: " + smallField.getText()+"\n � ������: " + bigField.getText()+"\n ���������");
                
                
                start=smallField.getText().toString().trim();
                end=bigField.getText().toString().trim();
                if(start!=null&&!start.isEmpty()&&end!=null&&!end.isEmpty()) {
                	String countryName;
                	hideOtherMarkers( airportList,routeList);
        			for(Marker r:airportList) {

        				countryName=r.getProperty("country").toString();
        				String airportCountry=countryName.substring(1,countryName.length()-1);
        				
        				if(airportCountry.equals(start)) {//����� ���������
        				
        				
        					 for(Marker m:routeList) {
        						 AirportMarker air=(AirportMarker)r;
        						 
        						 String airStr=air.getFeature().getId().toString();
        						 String routStr=m.getProperty("destination").toString();
        						 String sourceStr=m.getProperty("source").toString();
        						 
        						 if(routStr.equals(airStr)||sourceStr.equals(airStr)) { //����� ������� ����
        	           			 
        	           			  
        	      				for (Marker mhide : airportList) {
        	      					AirportMarker otherAiroports=(AirportMarker)mhide;
        	    					 String airID=otherAiroports.getFeature().getId().toString();
        	  					if (routStr.equals(airID)||sourceStr.equals(airID)) {  //����� �������� ���������
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
        // �������� ������ � ���������� ������
         contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(smallField);
        contents.add(bigField  );
       // contents.add(password  );
        setContentPane(contents);
        // ���������� ������ ���� � ������� ��� �� �����
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