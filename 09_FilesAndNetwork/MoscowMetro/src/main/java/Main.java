import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static final Elements START_ELEMENTS = getPage();
    public static final String URL = "https://www.moscowmap.ru/metro.html#lines";

    public static void main(String[] args) {
        var lines = ParseMetro.getLines(START_ELEMENTS);
        var stations = ParseMetro.getStationsByLines(lines, START_ELEMENTS);
        var jsonArrayLines = JsonOperations.getJsonArrayLines(lines);
        var jsonStations = JsonOperations.getJsonStations(stations, lines);
        var connections = ParseMetro.getConnections(START_ELEMENTS);
        JSONObject jsonMoscowMetro = new JSONObject();
        jsonMoscowMetro.put("stations", jsonStations);
        jsonMoscowMetro.put("lines", jsonArrayLines);
        jsonMoscowMetro.put("connections", connections);

        JsonOperations.writeJsonFile(jsonMoscowMetro);
        JsonFileReader.printNumberOfStationsOnLineAndConnections();
    }

    private static Elements getPage() {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).maxBodySize(0).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc != null ? doc.select("div[id=metrodata]") : null;
    }
}
