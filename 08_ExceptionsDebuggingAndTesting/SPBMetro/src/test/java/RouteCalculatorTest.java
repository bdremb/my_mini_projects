import core.Line;
import core.Station;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest {    //ready
    private static StationIndex stationIndex;
    private static RouteCalculator routeCalculator;

    private static List<Station> connections1;
    private static List<Station> connections3;

    private static Station one1;
    private static Station one2;
    private static Station one3;
    private static Station one4;
    private static Station two1;
    private static Station two2;
    private static Station three3;
    private static Station three4;
    private static Station three5;

    @BeforeClass
    public static void setUp() throws Exception {
        stationIndex = createTestStationIndex();
        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Test
    public void testGetShortestRoute() {              //ready
        var actual = routeCalculator.getShortestRoute(one4, three3);
        var expected = new ArrayList<>();
        expected.add(one4);
        expected.add(one3);
        expected.add(one2);
        expected.add(three4);
        expected.add(three3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDuration() {               //ready
        var route = new ArrayList<Station>();
        route.add(one1);
        route.add(one2);
        route.add(one3);
        route.add(one4);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 7.5;
        Assert.assertEquals(expected, actual, 0.1);
    }

    @Test
    public void testGetRouteOnTheLine() {                //ready
        for (Station station : routeCalculator.getShortestRoute(one1, one4)) {
            Assert.assertEquals(one1.getLine(), station.getLine());
        }
    }

    @Test
    public void testGetRouteWithOneConnection() {        //ready
        var actual = routeCalculator.getShortestRoute(three3, one1);
        var expected = new ArrayList<>();
        expected.add(three3);
        expected.add(three4);
        expected.add(one2);
        expected.add(one1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteWithTwoConnections() {       //ready
        var actual = routeCalculator.getShortestRoute(two1, three5);
        var expected = new ArrayList<>();
        expected.add(two1);
        expected.add(two2);
        expected.add(one3);
        expected.add(one2);
        expected.add(three4);
        expected.add(three5);
        Assert.assertEquals(expected, actual);
    }

    private static StationIndex createTestStationIndex() {
        stationIndex = new StationIndex();
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        one1 = new Station("Черная", line1);
        one2 = new Station("Коричневая1", line1);
        one3 = new Station("Синяя1", line1);
        one4 = new Station("Пшеничная", line1);

        two1 = new Station("Овсяная", line2);
        two2 = new Station("Синяя2", line2);

        three3 = new Station("Желтая", line3);
        three4 = new Station("Коричневая3", line3);
        three5 = new Station("Оранжевая", line3);

        stationIndex.addStation(one1);
        stationIndex.addStation(one2);
        stationIndex.addStation(one3);
        stationIndex.addStation(one4);
        stationIndex.addStation(two1);
        stationIndex.addStation(two2);

        stationIndex.addStation(three3);
        stationIndex.addStation(three4);
        stationIndex.addStation(three5);

        line1.addStation(one1);
        line1.addStation(one2);
        line1.addStation(one3);
        line1.addStation(one4);

        line2.addStation(two1);
        line2.addStation(two2);

        line3.addStation(three3);
        line3.addStation(three4);
        line3.addStation(three5);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        connections1 = new ArrayList<>(); // первая пересадка
        connections1.add(one2);
        connections1.add(three4);

        connections3 = new ArrayList<>();  // третья пересадка
        connections3.add(two2);
        connections3.add(one3);

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections3);

        return stationIndex;
    }
}
