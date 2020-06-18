package dev.dgomezg.playground.java.strings;

import eu.javaspecialists.util.SuperSimpleGCParser;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class StringConcat {

    public static final int UP_TO = 1_000_000;
    public static final int NUM_OF_STRINGS = 20;

    public static void main(String[] args) throws Exception {

        SuperSimpleGCParser.showGCLogSummaryAtExit();

        for (int i = 0; i < UP_TO; i++) {
            String concatenated =
                                "";
                   testStringConcat(); //a.concat(b)
            //       testStringPlusString(); //a + b
            //       testStringBuilder(); // StringBuilder(a).append(b);
            //       testStringBuffer(); //Strinfbuffeer
            //       testLiferayStringBundler(); //LiferayStringBuilder()
            //       testStreamsCollector(); //Streams.collect(Collectors.joining(""));
            System.out.printf("iteration %d: concatenated string: %s\n", i, concatenated);
        }

        System.out.printf("Run using %s\n", ManagementFactory.getRuntimeMXBean().getVmVersion());

    }

    public static String testStreamsCollector() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String testLiferayStringBundler() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String testStringBuffer() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String testStringBuilder() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String testStringPlusString() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String testStringConcat() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static String[] generateStrings() {
        String [] strings = new String[NUM_OF_STRINGS];
        for (int i = 0; i < NUM_OF_STRINGS; i++) {
            strings[i] = generateRandomString(17 + i);
        }
        return strings;
    }

    private static String generateRandomString(int length) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

}
