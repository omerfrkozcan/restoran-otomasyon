package com.omerfarukozcan.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;

import static java.lang.Boolean.TRUE;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

@Slf4j
public class CommonUtil {

    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static final String LANG_PARAM = "lang";
    public static final String DEFAULT_LANG = "en";
    public static final Locale DEFAULT_LOCALE = new Locale(DEFAULT_LANG);


    public static String encodePass(String p) {
        return Base64Util.encode(p);
    }

    public static void main(String[] args) {
        System.out.println(encodePass("asd"));
    }

    public static int currentYear() {
        return getInstance().get(YEAR);
    }

    public static boolean izFalse(Boolean bool) {
        return !izTrue(bool);
    }

    public static boolean izTrue(Boolean bool) {
        return izNotNull(bool) && TRUE.equals(bool);
    }

    public static boolean izFalse(String str) {
        return !izTrue(str);
    }

    public static boolean izTrue(String str) {
        return izNotBlank(str) && "TRUE".equalsIgnoreCase(str);
    }

    public static boolean izNull(Object obj) {
        return obj == null;
    }

    public static boolean izNotNull(Object obj) {
        return !izNull(obj);
    }

    public static boolean izBlank(String str) {
        return izNull(str) || str.trim().length() == 0 || str.trim().equalsIgnoreCase("null");
    }

    public static boolean izNotBlank(String str) {
        return !izBlank(str);
    }

    public static String trim(String str) {
        return izBlank(str) ? "" : str.trim();
    }

    public static boolean izEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean izNotEmpty(Collection<?> collection) {
        return !izEmpty(collection);
    }

    public static boolean izEmpty(Object[] arr) {
        return izNull(arr) || arr.length == 0;
    }

    public static boolean izNotEmpty(Object[] arr) {
        return !izEmpty(arr);
    }

}
