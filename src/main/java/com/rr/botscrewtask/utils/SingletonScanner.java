package com.rr.botscrewtask.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingletonScanner implements AutoCloseable {
    private static SingletonScanner instance;
    private static Scanner scanner;

    private String stringValue;
    private Integer integerVariable;

    private synchronized static void createInstance() {

        if (instance == null)
            instance = new SingletonScanner();
    }

    private SingletonScanner() {
        scanner = new Scanner(System.in);
    }

    public static final SingletonScanner getInstance() {

        if (instance == null) {
            createInstance();
        }

        return instance;
    }

    public synchronized String getStringValue() {
        this.stringValue = scanner.nextLine();
        return stringValue;
    }

    public synchronized String isCorrectAuthorVariable() {
        Pattern p = Pattern.compile("");
        String str = scanner.nextLine();
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            System.out
                    .println("Wrong input data (to less, to many or incorrect symbols)! Write again!");
            return null;
        }

        return str;
    }

    private synchronized void setIntegerValue(Integer integerVariable) {
        this.integerVariable = integerVariable;
    }

    public synchronized Integer getIntegerValue() {

        scanner.nextLine(); // \r\n
        return integerVariable;
    }

    public synchronized boolean isIntegerValue() {

        String str = scanner.next();
        Integer value = null;

        try {
            value = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            System.err.println(str + " is not an integer!");
            scanner.nextLine();
            return false;
        }

        setIntegerValue(value);
        return true;
    }

    @Override
    public void close() throws Exception {

        System.out.println("\nScanner was closed!");
        scanner.close();
    }
}
