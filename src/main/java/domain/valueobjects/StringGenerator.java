package domain.valueobjects;

import java.security.SecureRandom;
import java.util.stream.IntStream;

public class StringGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String generate(int length) {
        if (length < 1)
            throw new IllegalArgumentException("String length cannot be less than 1");

        StringBuilder sb = new StringBuilder(length);
        IntStream.range(0, length).forEach(i -> {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        });

        return sb.toString();
    }
}