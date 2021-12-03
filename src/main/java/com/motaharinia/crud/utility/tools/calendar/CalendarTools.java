package com.motaharinia.crud.utility.tools.calendar;


import com.motaharinia.crud.utility.custom.customexception.utility.UtilityException;
import com.motaharinia.crud.utility.custom.customexception.utility.UtilityExceptionKeyEnum;
import com.motaharinia.crud.utility.custom.customfield.CustomDate;
import com.motaharinia.crud.utility.custom.customfield.CustomDateTime;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author https://github.com/motaharinia<br>
 * اینترفیس متدهای ابزاری تاریخ و زمان میلادی و جلالی
 */
public interface CalendarTools {

    /**
     * کلاس مبدل تاریخ میلادی و جلالی
     */
    JalaliCalendar jalaliCalendar = new JalaliCalendar();

    String EXCEPTION_EMPTY_DATE_DELIMITER = "dateDelimiter is empty";
    String EXCEPTION_EMPTY_SOURCE = "source is empty";
    String EXCEPTION_EMPTY_SOURCE_DATE = "sourceDate is empty";
    String EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER = "sourceDateDelimiter is empty";
    String EXCEPTION_EMPTY_SOURCE_DATE_TIME = "sourceDateTime is empty";
    String EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER = "destinationDateDelimiter is empty";

    //--------------------------------------------------متدهای کمکی--------------------------------------------------

    /**
     * این متد عدد را اگر یک رقمی باشد برای نمایش مرتب در خروجی دورقمی میکند
     *
     * @param inputDigit رشته عدد ورودی
     * @return خروجی: رشته عدد دو رقمی شده
     */
    @NotNull
    static String fixOneDigit(@NotNull String inputDigit) {
        if (ObjectUtils.isEmpty(inputDigit)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputDigit");
        }
        if (inputDigit.length() > 1) {
            return inputDigit;
        } else {
            return "0" + inputDigit;
        }
    }

    /**
     * این متد عدد را اگر یک رقمی باشد برای نمایش مرتب در خروجی دورقمی میکند
     *
     * @param inputDigit عدد ورودی
     * @return خروجی: رشته عدد دو رقمی شده
     */
    @NotNull
    static String fixOneDigit(int inputDigit) {
        if (inputDigit < 0) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputDigit");
        }
        if (inputDigit < 10) {
            return "0" + inputDigit;
        } else {
            return "" + inputDigit;
        }
    }

    /**
     * این متد یک رشته تاریخ و یک رشته جداکننده به عنوان ورودی میگیرد و سپس عددهای روز و ماه رشته تاریخ ورودی را اگر تک رقمی باشند دورقمی میکند و اصلاح مینماید
     *
     * @param inputDateString رشته تاریخ ورودی
     * @param delimiter       رشته جداکننده
     * @return خروجی: رشته تاریخ اصلاح شده
     */
    @NotNull
    static String fixDateSlash(@NotNull String inputDateString, @NotNull String delimiter) {
        if (ObjectUtils.isEmpty(inputDateString)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputDateString");
        }
        if (ObjectUtils.isEmpty(delimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "delimiter");
        }
        String[] tmpArray = inputDateString.split("/", -1);
        if (tmpArray.length != 3) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.INCORRECT_STRING_DATE_FORMAT, "inputDateString");
        }
        return tmpArray[0] + delimiter + fixOneDigit(tmpArray[1]) + delimiter + fixOneDigit(tmpArray[2]);
    }


    //--------------------------------------------------متدهای دریافت تاریخ و زمان فعلی--------------------------------------------------

    /**
     * این متد تاریخ فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ میلادی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ میلادی فعلی
     */
    @NotNull
    static String getCurrentGregorianDateString(@NotNull String dateDelimiter) {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DATE_DELIMITER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + dateDelimiter + "MM" + dateDelimiter + "dd", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    /**
     * این متد تاریخ و ساعت فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ میلادی و ساعت خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ میلادی و ساعت فعلی
     */
    @NotNull
    static String getCurrentGregorianDateTimeString(@NotNull String dateDelimiter) {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DATE_DELIMITER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + dateDelimiter + "MM" + dateDelimiter + "dd HH:mm:ss", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    /**
     * این متد تاریخ فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ جلالی فعلی
     */
    @NotNull
    static String getCurrentJalaliDateString(@NotNull String dateDelimiter) {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        return fixDateSlash(jalaliCalendar.getJalaliDate(new Date()), dateDelimiter);
    }

    /**
     * این متد تاریخ و ساعت فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی و ساعت خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ جلالی و ساعت فعلی
     */
    @NotNull
    static String getCurrentJalaliDateTimeString(@NotNull String dateDelimiter) {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return fixDateSlash(jalaliCalendar.getJalaliDate(new Date()), dateDelimiter) + " " + simpleDateFormat.format(new Date());
    }

    /**
     * این متد بررسی میکند امسال سال کبیسه هست یا خیر
     *
     * @return خروجی: وضعیت سال کبیسه امسال
     */
    @NotNull
    static Boolean getCurrentJalaliLeapYearStatus() {
        String[] jalaliDateArray = getCurrentJalaliDateString("-").split("-");
        return JalaliCalendar.isLeepYear(Integer.parseInt(jalaliDateArray[0]));
    }

    /**
     * این متد تاریخ جلالی شروع ماه جاری را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: تاریخ جلالی شروع ماه جاری
     */
    @NotNull
    static String getJalaliFirstDayOfMonth(@NotNull String dateDelimiter) {
        String[] jalaliDateArray = getCurrentJalaliDateString(dateDelimiter).split(dateDelimiter);
        return jalaliDateArray[0] + "/" + jalaliDateArray[1] + "/01";
    }

    /**
     * این متد تاریخ جلالی پایان ماه جاری را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: تاریخ جلالی پایان ماه جاری
     */
    @NotNull
    static String getJalaliLastDayOfMonth(@NotNull String dateDelimiter) {
        String[] jalaliDateArray = getCurrentJalaliDateString(dateDelimiter).split(dateDelimiter);
        int jalaliMonth = Integer.parseInt(jalaliDateArray[1]);
        if (jalaliMonth <= 6) {
            return jalaliDateArray[0] + "/" + jalaliDateArray[1] + "/31";
        }
        if (jalaliMonth < 12) {
            return jalaliDateArray[0] + "/" + jalaliDateArray[1] + "/30";
        }
        if (JalaliCalendar.isLeepYear(Integer.parseInt(jalaliDateArray[0]))) {
            return jalaliDateArray[0] + "/" + jalaliDateArray[1] + "/30";
        } else {
            return jalaliDateArray[0] + "/" + jalaliDateArray[1] + "/29";
        }
    }


    //--------------------------------------------------متدهای تبدیل تاریخ جلالی به میلادی--------------------------------------------------

    /**
     * این متد رشته تاریخ جلالی(میتواند شامل زمان هم باشد) و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و Date میلادی خروجی میدهد
     *
     * @param source              رشته تاریخ جلالی(میتواند شامل زمان هم باشد)
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date jalaliToGregorianDate(@NotNull String source, @NotNull String sourceDateDelimiter) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }

        String[] sourceArray = source.split(" ", -1);
        String sourceDate = sourceArray[0];
        Date destination = jalaliCalendar.getGregorianDate(sourceDate.replaceAll(sourceDateDelimiter, "/"));
        if (sourceArray.length > 1) {
            String[] sourceTimeArray = sourceArray[1].split(":", -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(destination);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sourceTimeArray[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(sourceTimeArray[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(sourceTimeArray[2]));
            calendar.set(Calendar.MILLISECOND, 0);
            destination = calendar.getTime();
        }
        return destination;
    }

    /**
     * این متد رشته تاریخ جلالی(میتواند شامل زمان هم باشد) و رشته جدا کننده تاریخ جلالی و رشته جدا کننده تاریخ میلادی را از ورودی دریافت میکند و رشته تاریخ-زمان میلادی آن را بر اساس رشته جدا کننده میلادی خروجی میدهد
     *
     * @param source                   رشته تاریخ جلالی(میتواند شامل زمان هم باشد)
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ جلالی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ میلادی
     * @return خروجی: رشته میلادی
     */
    @NotNull
    static String jalaliToGregorianString(@NotNull String source, @NotNull String sourceDateDelimiter, @NotNull String destinationDateDelimiter) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + destinationDateDelimiter + "MM" + destinationDateDelimiter + "dd", Locale.ENGLISH);
        Date destination = jalaliToGregorianDate(source, sourceDateDelimiter);
        String[] sourceArray = source.split(" ", -1);
        if (sourceArray.length > 1) {
            return simpleDateFormat.format(destination) + " " + sourceArray[1];
        } else {
            return simpleDateFormat.format(destination);
        }
    }

    /**
     * این متد رشته تاریخ جلالی(میتواند شامل زمان هم باشد) و رشته جدا کننده تاریخ جلالی و ZoneId را از ورودی دریافت میکند و Instant آن را خروجی میدهد
     *
     * @param source              رشته تاریخ جلالی(میتواند شامل زمان هم باشد)
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param zoneId              zoneId
     * @return خروجی: Instant
     */
    @NotNull
    static Instant jalaliToGregorianInstant(@NotNull String source, String sourceDateDelimiter, ZoneId zoneId) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }
        String[] sourceArray = source.split(" ", -1);
        String gregorianDateString = jalaliToGregorianString(sourceArray[0], sourceDateDelimiter, "-");
        ZonedDateTime zonedDateTime;
        if (sourceArray.length > 1) {
            zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(gregorianDateString + "T" + sourceArray[1] + ".00"), zoneId);
        } else {
            zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(gregorianDateString + "T00:00:00.00"), zoneId);
        }
        return zonedDateTime.toInstant();
    }

    /**
     * این متد رشته تاریخ جلالی و رشته جدا کننده تاریخ جلالی و ZoneId را از ورودی دریافت میکند و LocalDate آن را خروجی میدهد
     *
     * @param source              رشته تاریخ جلالی
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param zoneId              zoneId
     * @return خروجی: LocalDate
     */
    @NotNull
    static LocalDate jalaliToGregorianLocalDate(@NotNull String source, String sourceDateDelimiter, ZoneId zoneId) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }
        String[] sourceArray = source.split(" ", -1);
        String gregorianDateString = jalaliToGregorianString(sourceArray[0], sourceDateDelimiter, "-");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(gregorianDateString + "T00:00:00.00"), zoneId);
        return zonedDateTime.toLocalDate();
    }


    /**
     * این متد رشته تاریخ-زمان جلالی و رشته جدا کننده تاریخ جلالی و ZoneId را از ورودی دریافت میکند و LocalDateTime آن را خروجی میدهد
     *
     * @param source              رشته تاریخ جلالی(میتواند شامل زمان هم باشد)
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param zoneId              zoneId
     * @return خروجی: LocalDateTime
     */
    @NotNull
    static LocalDateTime jalaliToGregorianLocalDateTime(@NotNull String source, String sourceDateDelimiter, ZoneId zoneId) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }
        String[] sourceArray = source.split(" ", -1);
        String gregorianDateString = jalaliToGregorianString(sourceArray[0], sourceDateDelimiter, "-");
        ZonedDateTime zonedDateTime;
        if (sourceArray.length > 1) {
            zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(gregorianDateString + "T" + sourceArray[1] + ".00"), zoneId);
        } else {
            zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(gregorianDateString + "T00:00:00.00"), zoneId);
        }
        return zonedDateTime.toLocalDateTime();
    }


    //--------------------------------------------------متدهای تبدیل تاریخ میلادی به جلالی--------------------------------------------------

    /**
     * این متد رشته تاریخ میلادی و رشته جدا کننده تاریخ میلادی و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و رشته تاریخ جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param source                   رشته تاریخ میلادی
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: رشته تاریخ جلالی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String gregorianToJalaliString(@NotNull String source, @NotNull String sourceDateDelimiter, @NotNull String destinationDateDelimiter) throws ParseException {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDate");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_DELIMITER);
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + sourceDateDelimiter + "MM" + sourceDateDelimiter + "dd", Locale.ENGLISH);
        Date destination = simpleDateFormat.parse(source);
        String destinationDate = jalaliCalendar.getJalaliDate(destination);

        String[] sourceArray = source.split(" ", -1);
        if (sourceArray.length > 1) {
            return fixDateSlash(destinationDate, destinationDateDelimiter) + " " + sourceArray[1];
        } else {
            return fixDateSlash(destinationDate, destinationDateDelimiter);
        }
    }

    /**
     * این متد Date را از ورودی دریافت میکند و رشته تاریخ یا تاریخ-زمان جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param source                   پارامتر Date میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param withTime                 رشته خروجی همراه با زمان باشد؟
     * @return خروجی: رشته تاریخ یا تاریخ-زمان جلالی
     */
    @NotNull
    static String gregorianToJalaliString(@NotNull Date source, @NotNull String destinationDateDelimiter, boolean withTime) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        if (withTime) {
            LocalDateTime localDateTime = source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return fixDateSlash(jalaliCalendar.getJalaliDate(source), destinationDateDelimiter) + " " + localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            return fixDateSlash(jalaliCalendar.getJalaliDate(source), destinationDateDelimiter);
        }
    }

    /**
     * این متد Instant را از ورودی دریافت میکند و رشته تاریخ یا تاریخ-زمان جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param source                   پارامتر Instant میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param withTime                 رشته خروجی همراه با زمان باشد؟
     * @return خروجی: رشته تاریخ یا تاریخ-زمان جلالی
     */
    @NotNull
    static String gregorianToJalaliString(@NotNull Instant source, @NotNull String destinationDateDelimiter, boolean withTime) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        Date destination = Date.from(source);
        if (withTime) {
            LocalDateTime localDateTime = destination.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter) + " " + localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter);
        }
    }

    /**
     * این متد LocalDate را از ورودی دریافت میکند و رشته تاریخ یا تاریخ-زمان جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param source                   پارامتر LocalDate میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param withTime                 رشته خروجی همراه با زمان باشد؟
     * @return خروجی: رشته تاریخ یا تاریخ-زمان جلالی
     */
    @NotNull
    static String gregorianToJalaliString(@NotNull LocalDate source, @NotNull String destinationDateDelimiter, boolean withTime) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        Date destination = Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        if (withTime) {
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter) + " 00:00:00";
        } else {
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter);
        }
    }

    /**
     * این متد LocalDateTime را از ورودی دریافت میکند و رشته تاریخ یا تاریخ-زمان جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param source                   پارامتر LocalDateTime میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @param withTime                 رشته خروجی همراه با زمان باشد؟
     * @return خروجی: رشته تاریخ یا تاریخ-زمان جلالی
     */
    @NotNull
    static String gregorianToJalaliString(@NotNull LocalDateTime source, @NotNull String destinationDateDelimiter, boolean withTime) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        Date destination = Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        if (withTime) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(destination);
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter) + " " + fixOneDigit(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + fixOneDigit(calendar.get(Calendar.MINUTE)) + ":" + fixOneDigit(calendar.get(Calendar.SECOND));
        } else {
            return fixDateSlash(jalaliCalendar.getJalaliDate(destination), destinationDateDelimiter);
        }
    }

    /**
     * این متد تاریخ Date میلادی را از ورودی دریافت میکند و CustomDate جلالی آن را خروجی میدهد
     *
     * @param source پارامتر Date میلادی
     * @return خروجی: CustomDate جلالی
     */
    @NotNull
    static CustomDate gregorianToJalaliCustomDate(@NotNull Date source) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE);
        }
        CustomDate destinationCustomDate = new CustomDate();
        String destinationDate = jalaliCalendar.getJalaliDate(source);
        String[] destinationDateArray = destinationDate.split("/");
        destinationCustomDate.setYear(Integer.parseInt(destinationDateArray[0]));
        destinationCustomDate.setMonth(Integer.parseInt(destinationDateArray[1]));
        destinationCustomDate.setDay(Integer.parseInt(destinationDateArray[2]));
        return destinationCustomDate;
    }

    /**
     * این متد تاریخ Date میلادی را از ورودی دریافت میکند و CustomDateTime جلالی آن را خروجی میدهد
     *
     * @param source پارامتر Date میلادی
     * @return خروجی: CustomDateTime جلالی
     */
    @NotNull
    static CustomDateTime gregorianToJalaliCustomDateTime(@NotNull Date source) {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        CustomDateTime destinationCustomDateTime = new CustomDateTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        String destinationDate = jalaliCalendar.getJalaliDate(source);
        String[] destinationDateArray = destinationDate.split("/");
        destinationCustomDateTime.setYear(Integer.parseInt(destinationDateArray[0]));
        destinationCustomDateTime.setMonth(Integer.parseInt(destinationDateArray[1]));
        destinationCustomDateTime.setDay(Integer.parseInt(destinationDateArray[2]));
        destinationCustomDateTime.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        destinationCustomDateTime.setMinute(calendar.get(Calendar.MINUTE));
        destinationCustomDateTime.setSecond(calendar.get(Calendar.SECOND));
        return destinationCustomDateTime;
    }

    //--------------------------------------------------متدهای اصلاح کننده متناسب با زبان لوکال و تفاوت زمانی بین دو تاریخ--------------------------------------------------

    /**
     * این متد رشته تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و رشته تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param sourceDate               رشته تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDate(@NotNull String sourceDate, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(sourceDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE);
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String destinationDate;
        sourceDate = sourceDate.replace("/", "-");
        String[] sourceDateArray = sourceDate.split("-", -1);
        //بر اساس سال متوجه میشویم که رشته تاریخ ورودی میلادی است یا جلالی
        if (Integer.parseInt(sourceDateArray[0]) > 1500) {
            //اگر رشته تاریخ ورودی میلادی است و لوکال داده شده فارسی است آن را تبدیل میکنیم در غیر این صورت همان رشته تاریخ ورودی را به عنوان خروجی انتخاب میکنیم
            if (locale.getLanguage().equals("fa")) {
                destinationDate = CalendarTools.gregorianToJalaliString(sourceDate, "-", destinationDateDelimiter);
            } else {
                destinationDate = sourceDate;
            }
        } else {
            //اگر رشته تاریخ ورودی جلالی است و لوکال داده شده غیر فارسی است آن را تبدیل میکنیم در غیر این صورت همان رشته تاریخ ورودی را به عنوان خروجی انتخاب میکنیم
            if (!locale.getLanguage().equals("fa")) {
                destinationDate = CalendarTools.jalaliToGregorianString(sourceDate, "-", destinationDateDelimiter);
            } else {
                destinationDate = sourceDate;
            }
        }
        destinationDate = destinationDate.replace("-", destinationDateDelimiter);
        destinationDate = destinationDate.replace("/", destinationDateDelimiter);
        return destinationDate;
    }

    /**
     * این متد Date تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و Date تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param source                   Date تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDate(@NotNull Date source, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE);
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return fixToLocaleDate(simpleDateFormat.format(source), destinationDateDelimiter, locale);
    }

    /**
     * این متد CustomDate تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و CustomDate تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param customDate               CustomDate تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDate(@NotNull CustomDate customDate, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(customDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDate");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        return fixToLocaleDate(customDate.getFormattedString("-"), destinationDateDelimiter, locale);
    }

    /**
     * این متد رشته تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و رشته تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param sourceDateTime           رشته تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ-زمان متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDateTime(@NotNull String sourceDateTime, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(sourceDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_SOURCE_DATE_TIME);
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, EXCEPTION_EMPTY_DESTINATION_DATE_DELIMITER);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String destinationDateTime;

        sourceDateTime = sourceDateTime.replace("/", "-");
        String[] sourceDateTimeArray = sourceDateTime.split(" ", -1);
        String sourceDate = sourceDateTimeArray[0];
        String[] sourceDateArray = sourceDate.split("-", -1);
        if (Integer.parseInt(sourceDateArray[0]) > 1500) {
            if (locale.getLanguage().equals("fa")) {
                destinationDateTime = CalendarTools.gregorianToJalaliString(sourceDate, "-", destinationDateDelimiter) + " " + sourceDateTimeArray[1];
            } else {
                destinationDateTime = sourceDateTime;
            }
        } else {
            if (!locale.getLanguage().equals("fa")) {
                destinationDateTime = CalendarTools.jalaliToGregorianString(sourceDate, "-", destinationDateDelimiter) + " " + sourceDateTimeArray[1];
            } else {
                destinationDateTime = sourceDateTime;
            }
        }
        destinationDateTime = destinationDateTime.replace("-", destinationDateDelimiter);
        destinationDateTime = destinationDateTime.replace("/", destinationDateDelimiter);
        return destinationDateTime;
    }

    /**
     * این متد Date تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و Date تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param source                   Date تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ-زمان متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDateTime(@NotNull Date source, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return fixToLocaleDateTime(simpleDateFormat.format(source), destinationDateDelimiter, locale);
    }

    /**
     * این متد CustomDateTime تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و CustomDate تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param customDateTime           CustomDateTime تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String fixToLocaleDateTime(@NotNull CustomDateTime customDateTime, @NotNull String destinationDateDelimiter, Locale locale) throws ParseException {
        if (ObjectUtils.isEmpty(customDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDateTime");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        return fixToLocaleDateTime(customDateTime.getFormattedString("-"), destinationDateDelimiter, locale);
    }

    /**
     * این متد دو Date میلادی را از ورودی دریافت میکند و میزان اختلاف آن دو تاریخ را براساس واحد زمانی دلخواه خروجی میدهد
     *
     * @param date1            تاریخ1
     * @param date2            تاریخ2
     * @param dateTimeUnitEnum واحد زمانی
     * @return خروجی: میزان اختلاف آن تاریخ ورودی براساس واحد زمانی ورودی
     */
    static long getTwoDateDifference(@NotNull Date date1, @NotNull Date date2, @NotNull DateTimeUnitEnum dateTimeUnitEnum) {
        if (ObjectUtils.isEmpty(date1)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "date1");
        }
        if (ObjectUtils.isEmpty(date2)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "date2");
        }
        if (ObjectUtils.isEmpty(dateTimeUnitEnum)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateTimeUnitEnum");
        }
        long differenceInMillisecond = (date2.getTime() - date1.getTime());
        switch (dateTimeUnitEnum) {
            case SECOND:
                differenceInMillisecond = differenceInMillisecond / 1000L;
                break;
            case MINUTE:
                differenceInMillisecond = differenceInMillisecond / (1000L * 60);
                break;
            case HOUR:
                differenceInMillisecond = differenceInMillisecond / (1000L * 60 * 60);
                break;
            case DAY:
                differenceInMillisecond = differenceInMillisecond / (1000L * 60 * 60 * 24);
                break;
            case MONTH:
                differenceInMillisecond = differenceInMillisecond / (1000L * 60 * 60 * 24 * 30);
                break;
            case YEAR:
                differenceInMillisecond = differenceInMillisecond / (1000L * 60 * 60 * 24 * 30 * 12);
                break;
            default:
                break;
        }
        return differenceInMillisecond;
    }

    //--------------------------------------------------متدهای تبدیلی CustomDate و CustomDateTime به Date--------------------------------------------------

    /**
     * این متد مطابق با CustomDate میلادی ورودی ، Date میلادی متناسب را خروجی میدهد
     *
     * @param customDate پارامتر CustomDate میلادی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date getDateFromCustomDate(@NotNull CustomDate customDate) {
        if (CustomDate.isEmpty(customDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDate");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, customDate.getYear());
        calendar.set(Calendar.MONTH, customDate.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, customDate.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * این متد مطابق با CustomDateTime میلادی ورودی ، Date میلادی متناسب را خروجی میدهد
     *
     * @param customDateTime پارامتر CustomDateTime میلادی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date getDateFromCustomDateTime(@NotNull CustomDateTime customDateTime) {
        if (CustomDateTime.isEmpty(customDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDateTime");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, customDateTime.getYear());
        calendar.set(Calendar.MONTH, customDateTime.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, customDateTime.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, customDateTime.getHour());
        calendar.set(Calendar.MINUTE, customDateTime.getMinute());
        calendar.set(Calendar.SECOND, customDateTime.getSecond());
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //--------------------------------------------------متدهای بررسی کننده تاریخهای جلالی و میلادی--------------------------------------------------

    /**
     * این متد صحت تاریخ جلالی ورودی را بررسی میکند
     *
     * @param year  سال تاریخ جلالی
     * @param month ماه تاریخ جلالی
     * @param day   روز تاریخ جلالی
     * @return خروجی: نتیجه بررسی صحت تاریخ
     */
    @NotNull
    static Boolean checkJalaliDateValidity(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) {
        if (ObjectUtils.isEmpty(year)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "year");
        }
        if (ObjectUtils.isEmpty(month)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "month");
        }
        if (ObjectUtils.isEmpty(day)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "day");
        }
        if (year <= 0 || month <= 0 || month > 12 || day <= 0) {
            return false;
        }
        return (month > 6 || day <= 31) && (month <= 6 || day <= 30);
    }

    /**
     * این متد صحت تاریخ میلادی ورودی را بررسی میکند
     *
     * @param year  سال تاریخ میلادی
     * @param month ماه تاریخ میلادی
     * @param day   روز تاریخ میلادی
     * @return خروجی: نتیجه بررسی صحت تاریخ
     */
    @NotNull
    static Boolean checkGregorianDateValidity(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) {
        if (ObjectUtils.isEmpty(year)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "year");
        }
        if (ObjectUtils.isEmpty(month)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "month");
        }
        if (ObjectUtils.isEmpty(day)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "day");
        }
        boolean result = true;
        String dateFormat = "yyyy/MM/dd";
        String dateString = year + "/" + month + "/" + day;
        DateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            result = false;
        }
        return result;
    }


    /**
     * این متد بررسی میکند که آیا تاریخ ورودی امروز است یا خیر
     *
     * @param dateForCheck تاریخ ورودی
     * @return خروجی: نتیجه بررسی
     */
    @NotNull
    static Boolean isToday(Date dateForCheck) {
        if (ObjectUtils.isEmpty(dateForCheck)) return false;
        return DateUtils.isSameDay(new Date(), dateForCheck);
    }


    /**
     * متد اصلاح کننده رشته تاریخ جلالی
     *
     * @param jalaliString رشته تاریخ جلالی
     * @param delimiter    رشته جدا کننده تاریخ خروجی
     * @return خروجی: رشته تاریخ جلالی اصلاح شده با جدا کننده
     */
    static String fixJalaliString(String jalaliString, String delimiter) {
        //اگر تاریخ جلالی خالی است یا طول آن نامعتبر است
        if (ObjectUtils.isEmpty(jalaliString) || jalaliString.length() < 6) {
            return null;
        }
        //اصلاح تاریخهای 6 رقمی جلالی که ابتدای آنها 13 یا 14 نیست و سال به صورت مختصر نوشته شده مثلا بجای 1399 نوشته شده 99
        if (!jalaliString.startsWith("13") && !jalaliString.startsWith("14")) {
            int twoFirstNumber = (Character.getNumericValue(jalaliString.charAt(0)) * 10) + Character.getNumericValue(jalaliString.charAt(1));
            if (twoFirstNumber < 10) {
                //00-09 -> 1400-1409
                jalaliString = "14" + jalaliString;
            } else {
                //10-99 -> 1310-1399
                jalaliString = "13" + jalaliString;
            }
        }
        //اصلاح جدا کننده تاریخ
        if (jalaliString.contains("-")) {
            jalaliString = jalaliString.replace("-", delimiter);
        }
        if (jalaliString.contains("/")) {
            jalaliString = jalaliString.replace("/", delimiter);
        }
        if (jalaliString.contains("_")) {
            jalaliString = jalaliString.replace("_", delimiter);
        }
        //اصلاح جدا کننده تاریخ در حالتی که تاریخ بدون جدا کننده داده شده است مثل 13990225
        if (!jalaliString.contains(delimiter)) {
            if (jalaliString.length() > 7) {
                jalaliString = jalaliString.substring(0, 4) + delimiter + jalaliString.substring(4, 6) + delimiter + jalaliString.substring(6, 8);
            }
        }
        return jalaliString;
    }
}
