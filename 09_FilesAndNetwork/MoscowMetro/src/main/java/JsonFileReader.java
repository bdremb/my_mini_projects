import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonFileReader {

    public static void printNumberOfStationsOnLineAndConnections() {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(getJsonLines());
            JSONObject jsonArray = (JSONObject) jsonObject.get("stations");
            List<JSONObject> listCon = (List<JSONObject>) jsonObject.get("connections");
            System.out.println("В Московском метро: " + listCon.size() + " станций метро с переходом.");
            for (Object key : jsonArray.keySet()) {
                String[] lines = jsonArray.get(key).toString().split(",");
                System.out.println("На линии " + key + " -> " + lines.length + " станции(й)");
                
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getJsonLines() {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/mapm.json"));
            lines.forEach(sb::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
