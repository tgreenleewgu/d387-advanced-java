package edu.wgu.d387_sample_code.translation;


import java.util.Locale;
import java.util.ResourceBundle;

import java.util.*;

public class DisplayMessage implements Runnable {
    private  Locale locale;
    private String message;

    public DisplayMessage(Locale locale) {
        this.locale = locale;
    }

    public String getMessage() {

            ResourceBundle bundle = ResourceBundle.getBundle("Nat", Locale.getDefault());
            message = bundle.getString("welcomeMessage");

        return message;
    }

    @Override
    public void run() {
        System.out.println(getMessage());
    }
}