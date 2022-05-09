package interactiveMap;
import javax.swing.*;

import de.fhpotsdam.unfolding.marker.Marker;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;

public class TextFieldTest extends JFrame implements ITextField
{
    // Текстовые поля
    private JTextField smallField;// bigField;
    JPanel contents;
    //EarthquakeCityMap map=new EarthquakeCityMap();
    private static String country;
    public  TextFieldTest(List<Marker> airportList,List<Marker> quakeMarkers,List<Marker> cityMarkers)
    {
        super("Country");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание текстовых полей
        smallField = new JTextField(15);
        smallField.setToolTipText("Country");
       // bigField = new JTextField("Текст поля", 25);
        
        smallField.setToolTipText("Введите название страны");
        
       // bigField.setToolTipText("Длиное поле");
        // Настройка шрифта
       // bigField.setFont(new Font("Dialog", Font.PLAIN, 14));
       // bigField.setHorizontalAlignment(JTextField.RIGHT);
        // Слушатель окончания ввода
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(TextFieldTest.this, 
                               "Все маркеры в данной стране отмечены: " + smallField.getText());
                country=smallField.getText().toString().trim();
                if(country!=null&&!country.isEmpty()) {
                	String countryName;
        			for(Marker r:airportList) {

        				countryName=r.getProperty("country").toString();
        				String airportCountry=countryName.substring(1,countryName.length()-1);
        				
        				if(airportCountry.equals(country)) {
        					r.setHidden(false);
        				}else {
        					r.setHidden(true);
        				}
        		 }
        		for(Marker r:quakeMarkers) {

        			String quakeTitle=r.getProperty("title").toString();
        			String quakeCountry=quakeTitle.substring(quakeTitle.lastIndexOf(" ")).trim();
        			
        			
        			if(country.contains(quakeCountry)) {
        				r.setHidden(false);
        			}else {
        				r.setHidden(true);
        			}
        			
        	 }
        		for(Marker r:cityMarkers) {

        			if(r.getProperty("country").toString().equals(country)) {
        				r.setHidden(false);
        			}else {
        				r.setHidden(true);
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
        //contents.add(bigField  );
       // contents.add(password  );
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setSize(280, 80);
        setVisible(false);
        
     //   System.out.println(country);
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