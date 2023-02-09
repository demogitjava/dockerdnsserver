/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author root
 */
public interface iBind9Server {
    public static String st_timezones = "Europe/Berlin";
    public static void main(String[] args)
    {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(st_timezones));
        //ZonedDateTime now = ZonedDateTime.now();

        // set up to GMT
        now.withZoneSameInstant( ZoneId.of("GMT") )
                .format( DateTimeFormatter.ISO_OFFSET_DATE_TIME );
        // 1. ZonedDateTime to TimeStamp
        Timestamp timestamp = Timestamp.valueOf(now.toLocalDateTime());

        // 2. ZonedDateTime to TimeStamp , no different
        Timestamp timestamp2 = Timestamp.from(now.toInstant());

        System.out.println(now);
        System.out.println(timestamp);
        System.out.println(timestamp2);

        SpringApplication.run(BIND9Server.class, args);
    }
}
