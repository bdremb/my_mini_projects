import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParseMetro {

    public static List<String> getStationsByLines(List<String> lines, Elements prepare) {
        List<String> arrayList = new ArrayList<>();
        Elements name = prepare.select("span[class=\"name\"]");
        Elements num = prepare.select("span[class=\"num\"]");
        int index = -1;
        for (int i = 0; i < num.size(); i++) {
            String number = num.get(i).text().replaceAll("\\.", "");
            if (number.equals("1")) {
                ++index;
            }
            String nameStation = name.get(i).text();
            String line = lines.get(index);
            arrayList.add(line.substring(0, line.indexOf(" ")) + "," + number + "," + nameStation);
        }
        return arrayList;
    }

    public static List<String> getLines(Elements prepare) {
        List<String> lines = new ArrayList<>();
        Elements metroLines = prepare.select("span[class^=js-metro-line]");
        for (Element lineName : metroLines) {
            String number = lineName.cssSelector().substring(lineName.cssSelector().lastIndexOf("-") + 1);
            lines.add(number + " " + lineName.text());
        }
        return lines;
    }

    public static List<Connection> getConnections(Elements prepare) {
        List<Connection> connections = new ArrayList<>();
        Elements stationsByLines = prepare.select("div.js-metro-stations");
        Elements lines = prepare.select("div.js-toggle-depend");

        for (int i = 0; i < lines.size(); i++) {
            Element line = lines.get(i).selectFirst("span.js-metro-line");
            Elements stations = stationsByLines.get(i).select("p");
            String lineNum = line.attr("data-line");  // number of line

            for (Element el : stations) {
                String stationName = el.select("span.name").text();
                Elements connectionsElements = el.select("span.t-icon-metroln");  //connections

                List<Connection> listConnectStations = new ArrayList<>();
                for (Element elem : connectionsElements) {
                    String numConnect = elem.attr("class").replaceAll(".+ln-", "");
                    String nameConnect = elem.attr("title").replaceAll(".+«(.+)».+", "$1");
                    listConnectStations.add(new Connection(numConnect, nameConnect));
                }
                if (listConnectStations.size() != 0) {
                    connections.add(new Connection(lineNum, stationName, listConnectStations));
                }
            }
        }
        return connections;
    }
}
