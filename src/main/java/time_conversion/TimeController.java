package time_conversion;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time")
@CrossOrigin(origins = "http://localhost:4200")
public class TimeController {

    @GetMapping("/convert")
    public String convertTimeZone() {
        return TimeConverter.convertTimeToAllZones();
    }
}