package time_conversion;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class TimeConverter {
//
//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//
//    public static String convertTimeToAllZones() {
//        String currentTime = "2024-011-18T16:00:00Z";
//        ZonedDateTime currentZonedDateTime = ZonedDateTime.parse(currentTime);
//
//        ZoneId etZone = ZoneId.of("America/New_York");
//        ZoneId mtZone = ZoneId.of("America/Denver");
//        ZoneId utcZone = ZoneId.of("UTC");
//
//        ZonedDateTime etTime = currentZonedDateTime.withZoneSameInstant(etZone);
//        ZonedDateTime mtTime = currentZonedDateTime.withZoneSameInstant(mtZone);
//        ZonedDateTime utcTime = currentZonedDateTime.withZoneSameInstant(utcZone);
//
//        String etTimeString = etTime.format(formatter);
//        String mtTimeString = mtTime.format(formatter);
//        String utcTimeString = utcTime.format(formatter);
//
//        return etTimeString + " ET, " +  mtTimeString + " MT, "  + utcTimeString + " UTC";
//    }
//}
public class TimeConverter {
    public Map<String, String> convertToTimeZones(LocalDateTime dateTime) {
        Map<String, String> convertedTimes = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");

        convertedTimes.put("ET", dateTime.atZone(ZoneId.of("America/New_York")).format(formatter));
        convertedTimes.put("MT", dateTime.atZone(ZoneId.of("America/Denver")).format(formatter));
        convertedTimes.put("UTC", dateTime.atZone(ZoneId.of("UTC")).format(formatter));

        return convertedTimes;
    }
}