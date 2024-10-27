package edu.wgu.d387_sample_code.custom;

import java.util.Locale;
import java.util.ResourceBundle;

public class EnglishThread implements Runnable{
   String message;
    @Override
    public void run() {
        Locale.setDefault( Locale.ENGLISH);
        ResourceBundle resourceBundle= ResourceBundle.getBundle("Nat", Locale.getDefault());
        this.message = resourceBundle.getString("welcomeMessage");
    }

    public String getMessage() {
        return message;
    }
}
