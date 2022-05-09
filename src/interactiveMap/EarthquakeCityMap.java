package interactiveMap;
import java.util.List;
import Handler.ConcreteHandlerAirports;
import Handler.ConcreteHandlerCities;
import Handler.ConcreteHandlerEarthquakes;
import Handler.ConcreteHandlerMenu;
import Handler.HalderContainer;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import model.AirportMarker;
import model.CityMarker;
import model.CommonMarker;
import model.EarthquakeMarker;
import model.menuMarker;
import parsing.Parser;
import parsing.ProcessingParse;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import secondHandler.ConcreteHandlerFirst;
import secondHandler.ConcreteHandlerSecond;
import secondHandler.ConcreteHandlerThird;
import secondHandler.SecondHalderContainer;

public class EarthquakeCityMap extends PApplet {

	private static final long serialVersionUID = 1L;
	private static final boolean offline = false;

	private PImage photo;
	private Clock clock;
	private HalderContainer halderNoLastClicks = new HalderContainer();
	private SecondHalderContainer halderWithLastClicks = new SecondHalderContainer();

	public void setup() {

		size(1200, 600, OPENGL);
		if (offline) {
			CommonMarker
					.setMap(new UnfoldingMap(this, 0, 0, 1500, 700, new MBTilesMapProvider(Parser.getmbTilesString())));
			Parser.offnlineearthquakesURL();
			CommonMarker.getMap().setZoomRange(1.7f, 3);

		} else {
			System.setProperty("http.agent", "Chrome");
			CommonMarker.setMap(new UnfoldingMap(this, 0, 0, 1500, 700, new Google.GoogleMapProvider()));
			CommonMarker.getMap().setZoomRange(1.7f, 6);

			// CommonMarker.setMap(new UnfoldingMap(this, 0, 0, 1500, 700));
		}

		CommonMarker
				.setsecMap(new UnfoldingMap(this, 360, 20, 495, 69, new MBTilesMapProvider(Parser.getmbTilesString())));

		CommonMarker.getConsole().zoomAndPanTo(3, new Location(83.44f, 23f));
		CommonMarker.getConsole().setTweening(false);
		CommonMarker.getConsole().setPanningRestriction(new Location(83.44f, 23f), 3);
		CommonMarker.getConsole().setZoomRange(3, 3);
		CommonMarker.getMap().zoom(1.7f);

		MapUtils.createDefaultEventDispatcher(this, CommonMarker.getMap());// Обрабатывает все события, которые
																			// происходят на карте.
		MapUtils.createDefaultEventDispatcher(this, CommonMarker.getConsole());

		ProcessingParse handler = new ProcessingParse();
		handler.parseAirports();
		handler.parseCountries();
		handler.parseMenuMarkers();
		handler.parseEarthquakes();

		CommonMarker.addMarkersToMap();
		CommonMarker.addMenuMarkersToMap();

		printQuakes();
		photo = loadImage("unnamed.png");

		addClock();
		
		
		 Service.setFields();

		halderNoLastClicks.addHandler(new ConcreteHandlerAirports());
		halderNoLastClicks.addHandler(new ConcreteHandlerCities());
		halderNoLastClicks.addHandler(new ConcreteHandlerEarthquakes());
		halderNoLastClicks.addHandler(new ConcreteHandlerMenu());


		halderWithLastClicks.addHandler(new ConcreteHandlerFirst());
		halderWithLastClicks.addHandler(new ConcreteHandlerSecond());
		halderWithLastClicks.addHandler(new ConcreteHandlerThird());


		
	}

	public void draw() {
		if (offline) {
			background(215, 215, 215);
		} else {
			background(138, 179, 246);
		}
		CommonMarker.getMap().draw();


		if (!Service.isHiddenKey()) {
			addKey();
			drawClock();
		}

		if (!Service.isHiddenInfo()) {
			addGeneralInfo();
		}
		
		CommonMarker.getConsole().draw();

	}

	@Override
	public void mouseMoved() {
		if (CommonMarker.getLastSelected() != null) {
			CommonMarker.unselected();
		}
		selectMarkerIfHover(CityMarker.getCityMarkers());
		selectMarkerIfHover(EarthquakeMarker.getQuakeMarkers());
		selectMarkerIfHover(AirportMarker.getAirportList());
		selectMarkerIfHover(menuMarker.getMenuMarkers());

	}

	private void selectMarkerIfHover(List<Marker> markers) {
		if (CommonMarker.getLastSelected() != null) {
			return;
		}

		for (Marker m : markers) {
			CommonMarker marker = (CommonMarker) m;
			if (marker.isInside(CommonMarker.getConsole(), mouseX, mouseY)) {
				CommonMarker.selectMarkerIfHover(marker);
				return;
			}
			if (marker.isInside(CommonMarker.getMap(), mouseX, mouseY)) {
				CommonMarker.selectMarkerIfHover(marker);
				return;
			}
		}
	}

	@Override
	public void mouseClicked() {
		if (CommonMarker.getLastClicked() != null) {
			AirportMarker.hideRouteByAir();

			if (CommonMarker.getLastClicked().isInside(CommonMarker.getConsole(), mouseX, mouseY)) {
				halderWithLastClicks.markerSetClick();
			}
			CommonMarker.setLastClickedNull();
		} else if (CommonMarker.getLastClicked() == null) {
			checkMarkerForClick();
		}

	}

	private void checkMarkerForClick() {
		if (CommonMarker.getLastClicked() != null)
			return;
		halderNoLastClicks.markerCheckClick(mouseX, mouseY);

	}



	private void addKey() {

		fill(255, 250, 240);
		stroke(0);
		int xbase = 55;
		int ybase = 50;

		image(photo, 10, 10, 300, 350);
		PFont font;
		font = createFont("Georgia", 18);
		textFont(font);

		fill(194, 92, 0);
		textAlign(LEFT, CENTER);
		textSize(12);

		text("Shapes", xbase + 90, ybase + 25);

		fill(204, 102, 0);
		int tri_xbase = xbase + 75;
		int tri_ybase = ybase + 70;
		triangle(tri_xbase, tri_ybase - CityMarker.TRI_SIZE, tri_xbase - CityMarker.TRI_SIZE,
				tri_ybase + CityMarker.TRI_SIZE, tri_xbase + CityMarker.TRI_SIZE, tri_ybase + CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("Airport Marker", tri_xbase + 15, tri_ybase - 20);

		text("City Marker", tri_xbase + 15, tri_ybase);

		text("Land Quake", xbase + 90, ybase + 90);
		text("Ocean Quake", xbase + 90, ybase + 110);
		// text("Size ~ Magnitude", xbase+45, ybase+130);
		fill(194, 92, 0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Color", xbase + 70, ybase + 140 - 10);

		fill(255, 255, 255);
		ellipse(xbase + 75, ybase + 90, 10, 10);

		fill(194, 92, 0);
		ellipse(xbase + 75, ybase + 110 - 60, 8, 8);
		fill(255, 255, 255);
		rect(xbase + 75 - 5, ybase + 110 - 5, 10, 10);

		fill(color(153, 255, 255));
		ellipse(xbase + 35, ybase + 160 - 10, 12, 12);
		fill(color(0, 128, 255));
		ellipse(xbase + 35, ybase + 180 - 10, 12, 12);
		fill(color(0, 0, 255));
		ellipse(xbase + 35, ybase + 200 - 10, 12, 12);

		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase + 50, ybase + 160 - 10);
		text("Intermediate", xbase + 50, ybase + 180 - 10);
		text("Deep", xbase + 50, ybase + 200 - 10);

		text("Past hour", xbase + 50, ybase + 220 - 10);

		fill(255, 255, 255);
		int centerx = xbase + 35;
		int centery = ybase + 220 - 10;
		ellipse(centerx, centery, 12, 12);

		strokeWeight(2);
		line(centerx - 8, centery - 8, centerx + 8, centery + 8);
		line(centerx - 8, centery + 8, centerx + 8, centery - 8);
		// text("Size ~ Magnitude", xbase+45, ybase+130);
		fill(194, 92, 0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Size", xbase + 70, ybase + 230);
		fill(0, 0, 0);
		textAlign(LEFT, CENTER);

		text("~ Magnitude", xbase + 25, ybase + 250);

	}

	private void addGeneralInfo() {
		fill(255, 250, 240);
		stroke(0);
		int xbase = 1020;
		int ybase = 30;

		int stringsCount = 0;
		for (String str : CommonMarker.getCountriesWithQuakes()) {
			stringsCount++;
		}
		stroke(204, 102, 0);

		rect(xbase - 75, ybase, 220, stringsCount * 17);

		PFont font;
		font = createFont("Georgia", 18);
		textFont(font);

		fill(255, 0, 0);
		textAlign(RIGHT, CENTER);
		textSize(12);
		text("Information", xbase + 85, ybase + 25);

		fill(150, 30, 30);

		fill(0, 0, 0);
		textAlign(RIGHT, CENTER);
		int x = 15;

		for (String str : CommonMarker.getCountriesWithQuakes()) {
			text(str, xbase + 140, ybase + 25 + x);
			x += 15;
		}

	}

	private void addClock() {
		clock = new Clock();

		int radius = 55;

		clock.setSecondsRadius((float) (radius * 0.72));
		clock.setMinutesRadius((float) (radius * 0.60));
		clock.setHoursRadius((float) (radius * 0.50));
		clock.setClockDiameter((float) (radius * 1.8));

		clock.setCX(255);
		clock.setCY(306);
	}


	public void selectByCountry(String country) {
		CommonMarker.selectByCountry(country);
	}

	private void drawClock() {
		// background(0);
		beginShape();
		fill(160, 160, 150);
		noStroke();
		ellipse(clock.getCX(), clock.getCY(), clock.getClockDiameter(), clock.getClockDiameter());

		float s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
		float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI;
		float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;

		// стрелки
		stroke(255);
		strokeWeight(1);
		stroke(205, 249, 255);
		line(clock.getCX(), clock.getCY(), clock.getCX() + cos(s) * clock.getSecondsRadius(),
				clock.getCY() + sin(s) * clock.getSecondsRadius());
		strokeWeight(2);
		line(clock.getCX(), clock.getCY(), clock.getCX() + cos(m) * clock.getMinutesRadius(),
				clock.getCY() + sin(m) * clock.getMinutesRadius());
		strokeWeight(4);
		line(clock.getCX(), clock.getCY(), clock.getCX() + cos(h) * clock.getHoursRadius(),
				clock.getCY() + sin(h) * clock.getHoursRadius());

		strokeWeight(2);
		endShape();
		beginShape(POINTS);
		stroke(255);
		for (int a = 0; a < 360; a += 6) {
			float angle = radians(a);
			float x = clock.getCX() + cos(angle) * clock.getSecondsRadius();
			float y = clock.getCY() + sin(angle) * clock.getSecondsRadius();
			vertex(x, y);
		}
		endShape();

	}

	private void printQuakes() {
		EarthquakeMarker.printQuakes();
	}

}
