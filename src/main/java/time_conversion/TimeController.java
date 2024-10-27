package time_conversion;


import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/time")
//@CrossOrigin(origins = "http://localhost:4200")
//public class TimeController {
//
//    @GetMapping("/convert")
//    public String convertTimeZone() {
//        return TimeConverter.convertTimeToAllZones();
//    }
//}

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private final TimeConverter timeConverter = new TimeConverter();

    @GetMapping("/convert")
    public Map<String, String> convertPresentationTime() {
        LocalDateTime presentationDateTime = LocalDateTime.parse("2024-11-14T12:00"); // Assume the time is 12:00
        return timeConverter.convertToTimeZones(presentationDateTime);
    }
}