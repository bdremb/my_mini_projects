import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {
    private static final int MAX_COUNT_OF_VOTERS = 50000;
    private int count = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            String birthDay = attributes.getValue("birthDay").replace('.', '-');
            String name = attributes.getValue("name");
            try {
                DBConnection.countVoter(name + "', '" + birthDay);
                count++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("voters") || count > MAX_COUNT_OF_VOTERS) {
            try {
                DBConnection.executeMultiInsert();
                DBConnection.setInsertQuery(new StringBuilder());
                count = 0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
