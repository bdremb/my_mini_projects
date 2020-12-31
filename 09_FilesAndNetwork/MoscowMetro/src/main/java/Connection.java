import java.util.List;

public class Connection {
    private String lineNumber;
    private String stationName;
    private List<Connection> connects;

    public Connection(String lineNumber, String stationName) {
        this.lineNumber = lineNumber;
        this.stationName = stationName;
    }

    public Connection(String lineNumber, String stationName, List<Connection> connections) {
        this.lineNumber = lineNumber;
        this.stationName = stationName;
        this.connects = connections;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<Connection> getConnects() {
        return connects;
    }

    public void setConnects(List<Connection> connects) {
        this.connects = connects;
    }
}
