import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonOperations {

    private static String dataFile = "src/main/resources/mapm.json";

    public static JSONArray getJsonArrayLines(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        sb.append("number,name\n");
        for (String lin : lines) {
            String[] st = lin.split(" ");
            String stat = st[0] + "," + st[1] + "\n";
            sb.append(stat);
        }
        return CDL.toJSONArray(sb.toString());
    }

    public static JSONObject getJsonStations(List<String> stations, List<String> lines) {
        JSONObject jsonObject = new JSONObject();
        for (String lin : lines) {
            String[] arrayStr = lin.split(" ");
            String line = arrayStr[0];
            ArrayList<String> list = new ArrayList<>();
            for (String st : stations) {
                String[] string = st.split(",");
                String lineStation = string[0];
                if (line.equals(lineStation)) {
                    list.add(string[2]);
                }
            }
            jsonObject.put(line, list);
        }
        return jsonObject;
    }

    public static void writeJsonFile(JSONObject jsonObject) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(dataFile), jsonObject.toMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
