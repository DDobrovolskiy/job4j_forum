package ru.job4j.test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class FormatTime {
    public static void main(String[] args) {
        System.out.println(Date.from(Instant.now()));
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        System.out.println("date: " + dateFormat.format(new Date()));
    }
}
