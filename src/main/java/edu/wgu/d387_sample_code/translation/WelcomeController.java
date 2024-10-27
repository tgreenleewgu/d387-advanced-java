package edu.wgu.d387_sample_code.translation;

import edu.wgu.d387_sample_code.custom.EnglishThread;
import edu.wgu.d387_sample_code.custom.FrenchThread;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("/welcome-fr")
    public ResponseEntity<String> getFrWelcomeMessage(@RequestParam("lang") String language) throws InterruptedException {
//
        FrenchThread frenchThread = new FrenchThread();
        Thread thread = new Thread(frenchThread);
        thread.start();
        Thread.sleep(20);

        return new ResponseEntity<>(frenchThread.getMessage(), HttpStatus.OK);
    }
    @GetMapping("/welcome-en")
    public ResponseEntity<String> getEngWelcomeMessage(@RequestParam("lang") String language) throws InterruptedException {

        EnglishThread englishThread = new EnglishThread();
        Thread thread = new Thread(englishThread);
        thread.start();
        Thread.sleep(10);

        return new ResponseEntity<>(englishThread.getMessage(), HttpStatus.OK);
    }
}