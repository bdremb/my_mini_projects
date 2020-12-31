import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {
    public static final String SMALL_FILE_NAME = "D:/VoteAnalyzer/VoteAnalyzer/res/data-18M.xml";
    public static final String BIG_FILE_NAME = "D:/VoteAnalyzer/VoteAnalyzer/res/data-1572M.xml";

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(BIG_FILE_NAME), handler);

        System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " ms");
        DBConnection.printVoterCounts();
        DBConnection.getConnection().close();

        System.out.println("The End !!!!!");
    }
}