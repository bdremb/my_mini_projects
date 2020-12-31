import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Util {

    public static ZonedDateTime dateTimeFromDate (Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault());
    }
}
