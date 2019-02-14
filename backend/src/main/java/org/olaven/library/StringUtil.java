package org.olaven.library;

import java.util.Random;

public class StringUtil {
    public static String randomString(int n) {

        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String string = "";
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            string= string + alphabet.charAt(index);
        }

        return string;
    }
}
