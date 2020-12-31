import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;

public class Avia {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        DateFormat df = new SimpleDateFormat("HH ч mm мин");

        List<Terminal> terminals = Airport.getInstance().getTerminals();

        terminals.stream().flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> Util.dateTimeFromDate(flight.getDate()).isAfter(ZonedDateTime.now())
                        && Util.dateTimeFromDate(flight.getDate()).isBefore(ZonedDateTime.now().plusHours(2)))
                .map(fl -> "Взлет  в  " + df.format(fl.getDate()) + "  - самолет: " + fl.getAircraft()).forEach(System.out::println);

    }
}
