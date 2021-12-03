package com.motaharinia.client.project.utility.custom.custommapper;


import com.motaharinia.client.project.utility.tools.calendar.CalendarTools;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مپر(مبدل پیش فرض انتیتی به مدل و مدل به انتیتی)
 */
public interface CustomMapper {


    //--------------------Instant-Long--------------------

    /**
     * متد مبدل شیی Instant به میلی ثانیه زمان
     *
     * @param instant شیی Instant
     * @return خروجی: میلی ثانیه
     */
    default Long mapInstantToLong(Instant instant) {
        return instant == null ? null : instant.toEpochMilli();
    }

    /**
     * متد مبدل میلی ثانیه زمان به شیی Instant
     *
     * @param epochMiliLong میلی ثانیه زمان
     * @return خروجی: شیی Instant
     */
    default Instant mapLongToInstant(Long epochMiliLong) {
        return epochMiliLong == null ? null : Instant.ofEpochMilli(epochMiliLong);
    }

    //--------------------LocalDate-Long--------------------

    /**
     * متد مبدل شیی LocalDate به میلی ثانیه زمان
     *
     * @param localDate شیی LocalDate
     * @return خروجی: میلی ثانیه
     */
    default Long mapLocalDateToLong(LocalDate localDate) {
        return localDate == null ? null : localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * متد مبدل میلی ثانیه زمان به شیی LocalDate
     *
     * @param epochMiliLong میلی ثانیه زمان
     * @return خروجی: شیی LocalDate
     */
    default LocalDate mapLongToLocalDate(Long epochMiliLong) {
        //because of java8 compatibility:
//        return instantLong == null ? null : LocalDate.ofInstant(Instant.ofEpochMilli(instantLong), ZoneId.systemDefault());
        return epochMiliLong == null ? null : Instant.ofEpochMilli(epochMiliLong).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //--------------------LocalDateTime-Long--------------------

    /**
     * متد مبدل شیی LocalDateTime به میلی ثانیه زمان
     *
     * @param localDateTime شیی LocalDateTime
     * @return خروجی: میلی ثانیه
     */
    default Long mapLocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * متد مبدل میلی ثانیه زمان به شیی LocalDateTime
     *
     * @param epochMiliLong میلی ثانیه زمان
     * @return خروجی: شیی LocalDateTime
     */
    default LocalDateTime mapLongToLocalDateTime(Long epochMiliLong) {
        return epochMiliLong == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMiliLong), ZoneId.systemDefault());
    }

    //--------------------Date-Long--------------------

    /**
     * متد مبدل شیی Date به میلی ثانیه زمان
     *
     * @param date شیی Date
     * @return خروجی: میلی ثانیه
     */
    default Long mapDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * متد مبدل میلی ثانیه زمان به شیی Date
     *
     * @param epochMiliLong میلی ثانیه زمان
     * @return خروجی: شیی Date
     */
    default Date mapLongToDate(Long epochMiliLong) {
        return epochMiliLong == null ? null : new Date(epochMiliLong);
    }


    //--------------------Instant-String--------------------

    /**
     * متد مبدل شیی Instant به رشته تاریخ شمسی
     *
     * @param instant شیی Instant
     * @return خروجی: رشته تاریخ شمسی
     */
    default String mapInstantToJalaliString(Instant instant) {
        return instant == null ? null : CalendarTools.gregorianToJalaliString(instant, "/", true);
    }

    /**
     * متد مبدل رشته تاریخ شمسی به شیی Instant
     *
     * @param jalaliString رشته تاریخ شمسی
     * @return خروجی: شیی Instant
     */
    default Instant mapJalaliStringToInstant(String jalaliString) {
        jalaliString = CalendarTools.fixJalaliString(jalaliString, "/");
        return jalaliString == null ? null : CalendarTools.jalaliToGregorianInstant(jalaliString, "/", ZoneId.systemDefault());
    }

    //--------------------LocalDate-String--------------------

    /**
     * متد مبدل شیی LocalDate به رشته تاریخ شمسی
     *
     * @param localDate شیی LocalDate
     * @return خروجی: رشته تاریخ شمسی
     */
    default String mapLocalDateToJalaliString(LocalDate localDate) {
        return localDate == null ? null : CalendarTools.gregorianToJalaliString(localDate, "/", false);
    }

    /**
     * متد مبدل رشته تاریخ شمسی به شیی LocalDate
     *
     * @param jalaliString رشته تاریخ شمسی
     * @return خروجی: شیی LocalDate
     */
    default LocalDate mapJalaliStringToLocalDate(String jalaliString) {
        jalaliString = CalendarTools.fixJalaliString(jalaliString, "/");
        return jalaliString == null ? null : CalendarTools.jalaliToGregorianLocalDate(jalaliString, "/", ZoneId.systemDefault());
    }

    //--------------------LocalDateTime-String--------------------

    /**
     * متد مبدل شیی LocalDateTime به رشته تاریخ شمسی
     *
     * @param localDateTime شیی LocalDateTime
     * @return خروجی: رشته تاریخ شمسی
     */
    default String mapLocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime == null ? null : CalendarTools.gregorianToJalaliString(localDateTime, "/", true);
    }

    /**
     * متد مبدل رشته تاریخ شمسی به شیی LocalDateTime
     *
     * @param jalaliString رشته تاریخ شمسی
     * @return خروجی: شیی LocalDateTime
     */
    default LocalDateTime mapStringToLocalDateTime(String jalaliString) {
        jalaliString = CalendarTools.fixJalaliString(jalaliString, "/");
        return jalaliString == null ? null : CalendarTools.jalaliToGregorianLocalDateTime(jalaliString, "/", ZoneId.systemDefault());
    }

    //--------------------Date-String--------------------

    /**
     * متد مبدل شیی Date به رشته تاریخ شمسی
     *
     * @param date شیی Date
     * @return خروجی: رشته تاریخ شمسی
     */
    default String mapDateToJalaliString(Date date) {
        return date == null ? null : CalendarTools.gregorianToJalaliString(date, "/", true);
    }

    /**
     * متد مبدل رشته تاریخ شمسی به شیی Date
     *
     * @param jalaliString رشته تاریخ شمسی
     * @return خروجی: شیی Date
     */
    default Date mapJalaliStringToDate(String jalaliString) {
        jalaliString = CalendarTools.fixJalaliString(jalaliString, "/");
        return jalaliString == null ? null : CalendarTools.jalaliToGregorianDate(jalaliString, "/");
    }

}
