package time_conversion;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TimeConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String convertTimeToAllZones() {
        String currentTime = "2023-05-18T16:00:00Z";
        ZonedDateTime currentZonedDateTime = ZonedDateTime.parse(currentTime);

        ZoneId etZone = ZoneId.of("America/New_York");
        ZoneId mtZone = ZoneId.of("America/Denver");
        ZoneId utcZone = ZoneId.of("UTC");

        ZonedDateTime etTime = currentZonedDateTime.withZoneSameInstant(etZone);
        ZonedDateTime mtTime = currentZonedDateTime.withZoneSameInstant(mtZone);
        ZonedDateTime utcTime = currentZonedDateTime.withZoneSameInstant(utcZone);

        String etTimeString = etTime.format(formatter);
        String mtTimeString = mtTime.format(formatter);
        String utcTimeString = utcTime.format(formatter);

        return etTimeString + " ET, " +  mtTimeString + " MT, "  + utcTimeString + " UTC";
    }
}
