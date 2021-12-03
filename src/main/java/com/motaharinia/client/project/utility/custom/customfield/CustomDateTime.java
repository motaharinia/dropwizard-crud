package com.motaharinia.client.project.utility.custom.customfield;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityException;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityExceptionKeyEnum;
import com.motaharinia.client.project.utility.tools.calendar.CalendarTools;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس برای جابجا کردن فیلد تاریخ-زمان در مدلها از شمسی به میلادی در زمان ارسال درخواست از کلاینت به سرور و بالعکس استفاده میشود
 */
@Data
public class CustomDateTime implements Comparable<CustomDateTime>, Serializable {

    /**
     * سال در تاریخ شمسی
     */
    private Integer year;
    /**
     * ماه در تاریخ شمسی
     */
    private Integer month;
    /**
     * روز در تاریخ شمسی
     */
    private Integer day;
    /**
     * ساعت در تاریخ شمسی
     */
    private Integer hour;
    /**
     * دقیقه در تاریخ شمسی
     */
    private Integer minute;
    /**
     * ثانیه در تاریخ شمسی
     */
    private Integer second;

    public CustomDateTime() {
    }

    @JsonCreator
    public CustomDateTime(@JsonProperty("year") Integer year, @JsonProperty("month") Integer month, @JsonProperty("day") Integer day,
                          @JsonProperty("hour") Integer hour, @JsonProperty("minute") Integer minute, @JsonProperty("second") Integer second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        deserializeDateTime();
    }

    /**
     * این متد تاریخ میلادی در ورودی دریافت میکند و یک کاستوم میلادی بر طبق آن تولید میکند
     *
     * @param date تاریخ میلادی
     */
    public CustomDateTime(Date date) {
        if (!ObjectUtils.isEmpty(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.setYear(calendar.get(Calendar.YEAR));
            this.setMonth(calendar.get(Calendar.MONTH) + 1);
            this.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            this.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            this.setMinute(calendar.get(Calendar.MINUTE));
            this.setSecond(calendar.get(Calendar.SECOND));
        }
    }

    private void deserializeDateTime(){
        if (ObjectUtils.isEmpty(this.year) && ObjectUtils.isEmpty(this.month) && ObjectUtils.isEmpty(this.day)) {
            return;
        }
        Locale currentLocale = Locale.getDefault();
        if (!validateByLocal(currentLocale.getLanguage())) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.DATE_TIME_VALIDATION_FAILED, "");
        }
        if (currentLocale.getLanguage().equals("fa")) {
            //user entered a jalali date
            String jalaiDateStringTime = this.year + "/" + this.month + "/" + this.day + " " + this.hour + ":" + this.minute + ":" + this.second;
            try {
                String gregorianDateString = CalendarTools.jalaliToGregorianString(jalaiDateStringTime, "/", "/");
                String[] dateTimeParts = gregorianDateString.split(" ");
                String[] dateParts = dateTimeParts[0].split("/");
                String[] timeParts = dateTimeParts[1].split(":");
                this.year = Integer.parseInt(dateParts[0]);
                this.month = Integer.parseInt(dateParts[1]);
                this.day = Integer.parseInt(dateParts[2]);
                this.hour = Integer.parseInt(timeParts[0]);
                this.minute = Integer.parseInt(timeParts[1]);
                this.second = Integer.parseInt(timeParts[2]);
            } catch (Exception e) {
                this.year = null;
                this.month = null;
                this.day = null;
                this.hour = null;
                this.minute = null;
                this.second = null;
            }
        }
    }

    /**
     * این متد بررسی میکند که آیا متناسب با لوکال فعلی تاریخ کلاس معتبر است یا خیر
     *
     * @param locale لوکال فعلی
     * @return خروجی: نتیجه بررسی
     */
    private boolean validateByLocal(String locale) {
        if (ObjectUtils.isEmpty(locale)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "locale");
        }
        boolean result = true;
        if (locale.equals("fa") && !CalendarTools.checkJalaliDateValidity(year, month, day)) {
            result = false;
        }
        if (locale.equals("en") && !CalendarTools.checkGregorianDateValidity(year, month, day)) {
            result = false;
        }
        return result;
    }


    /**
     * بررسی میکند آیا کاستوم ورودی نال یا خالی هست
     *
     * @param customDateTime کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    @NotNull
    public static boolean isEmpty(CustomDateTime customDateTime) {
        if (ObjectUtils.isEmpty(customDateTime)) {
            return true;
        }
        return ObjectUtils.isEmpty(customDateTime.getYear()) || ObjectUtils.isEmpty(customDateTime.getMonth()) || ObjectUtils.isEmpty(customDateTime.getDay()) || ObjectUtils.isEmpty(customDateTime.getHour()) || ObjectUtils.isEmpty(customDateTime.getMinute()) || ObjectUtils.isEmpty(customDateTime.getSecond());
    }

    /**
     * این متد یک رشته جداکننده تاریخ از ورودی دریافت میکند و در صورت خالی نبودن کلاس ، رشته تاریخ-زمان متناسب با فیلدهای کلاس را با فرمت مورد نظر ورودی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ-زمان
     */
    @NotNull
    public String getFormattedString(@NotNull String dateDelimiter){
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        if (!CustomDateTime.isEmpty(this)) {
            return year + dateDelimiter + CalendarTools.fixOneDigit(month.toString()) + dateDelimiter + CalendarTools.fixOneDigit(day.toString()) + " " + CalendarTools.fixOneDigit(hour.toString()) + ":" + CalendarTools.fixOneDigit(minute.toString()) + ":" + CalendarTools.fixOneDigit(second.toString());
        } else {
            return year + dateDelimiter + month + dateDelimiter + day + " " + hour+ ":" + minute+ ":" + second;
        }
    }

    @Override
    public String toString() {
        try {
            return "CustomDateTime{" + this.getFormattedString("-") + '}';
        } catch (UtilityException e) {
            return "CustomDateTime{" + e.getMessage() + '}';
        }
    }

    @Override
    public int compareTo(CustomDateTime customDateTime) {
        if(ObjectUtils.isEmpty(customDateTime)){
            return 0;
        }
        int ret;
        if ((this.getYear() == null) || (customDateTime.getYear() == null)) {
            ret = 0;
        } else {
            if (Objects.equals(this.getYear(), customDateTime.getYear())) {
                if ((this.getMonth() == null) || (customDateTime.getMonth() == null)) {
                    ret = 0;
                } else {
                    if (Objects.equals(this.getMonth(), customDateTime.getMonth())) {
                        if ((this.getDay() == null) || (customDateTime.getDay() == null)) {
                            ret = 0;
                        } else {
                            if (Objects.equals(this.getDay(), customDateTime.getDay())) {
                                if ((this.getHour() == null) || (customDateTime.getHour() == null)) {
                                    ret = 0;
                                } else {
                                    if (Objects.equals(this.getHour(), customDateTime.getHour())) {
                                        if ((this.getMinute() == null) || (customDateTime.getMinute() == null)) {
                                            ret = 0;
                                        } else {
                                            if (Objects.equals(this.getMinute(), customDateTime.getMinute())) {
                                                if ((this.getSecond() == null) || (customDateTime.getSecond() == null)) {
                                                    ret = 0;
                                                } else {
                                                    ret = this.getSecond() - customDateTime.getSecond();
                                                }
                                            } else {
                                                ret = this.getMinute() - customDateTime.getMinute();
                                            }
                                        }
                                    } else {
                                        ret = this.getHour() - customDateTime.getHour();
                                    }
                                }
                            } else {
                                ret = this.getDay() - customDateTime.getDay();
                            }
                        }
                    } else {
                        ret = this.getMonth() - customDateTime.getMonth();
                    }
                }
            } else {
                ret = this.getYear() - customDateTime.getYear();
            }
        }
        return ret;
    }



}
