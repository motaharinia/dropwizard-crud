package com.motaharinia.client.project.utility.custom.customfield;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityException;
import com.motaharinia.client.project.utility.custom.customexception.utility.UtilityExceptionKeyEnum;
import com.motaharinia.client.project.utility.tools.calendar.CalendarTools;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author https://github.com/motaharinia<br>
 * این کلاس برای جابجا کردن فیلد تاریخ در مدلها از شمسی به میلادی در زمان ارسال درخواست از کلاینت به سرور و بالعکس استفاده میشود
 */
@Data
public class CustomDate implements Comparable<CustomDate>, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CustomDate.class);

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

    public CustomDate() {
    }

    @JsonCreator
    public CustomDate(@JsonProperty("year") Integer year, @JsonProperty("month") Integer month, @JsonProperty("day") Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        deserializeDate();
    }

    /**
     * این متد تاریخ میلادی در ورودی دریافت میکند و یک کاستوم میلادی بر طبق آن تولید میکند
     *
     * @param date تاریخ میلادی
     */
    public CustomDate(Date date) {
        if (!ObjectUtils.isEmpty(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.setYear(calendar.get(Calendar.YEAR));
            this.setMonth(calendar.get(Calendar.MONTH) + 1);
            this.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        }
    }

    private void deserializeDate() {
        if (ObjectUtils.isEmpty(this.year) && ObjectUtils.isEmpty(this.month) && ObjectUtils.isEmpty(this.day)) {
            return;
        }
        Locale currentLocale = Locale.getDefault();
        if (!validateByLocal(currentLocale.getLanguage())) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.DATE_VALIDATION_FAILED, "");
        }
        if (currentLocale.getLanguage().equals("fa")) {
            //user entered a jalali date
            String jalaiDateString = this.year + "/" + this.month + "/" + this.day;
            try {
                String gregorianDateString = CalendarTools.jalaliToGregorianString(jalaiDateString, "/", "/");
                String[] gregorianParts = gregorianDateString.split("/");
                this.year = Integer.parseInt(gregorianParts[0]);
                this.month = Integer.parseInt(gregorianParts[1]);
                this.day = Integer.parseInt(gregorianParts[2]);
            } catch (Exception e) {
                this.year = null;
                this.month = null;
                this.day = null;
            }
        }
    }

    /**
     * این متد بررسی میکند که آیا متناسب با لوکال فعلی تاریخ کلاس معتبر است یا خیر
     *
     * @param locale لوکال فعلی
     * @return خروجی: نتیجه بررسی
     */
    private boolean validateByLocal(@NotNull String locale) {
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
     * @param customDate کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    @NotNull
    public static boolean isEmpty(CustomDate customDate) {
        if (ObjectUtils.isEmpty(customDate)) {
            return true;
        }
        return ObjectUtils.isEmpty(customDate.getYear()) || ObjectUtils.isEmpty(customDate.getMonth()) || ObjectUtils.isEmpty(customDate.getDay());
    }

    /**
     * این متد یک رشته جداکننده تاریخ از ورودی دریافت میکند و در صورت خالی نبودن کلاس ، رشته تاریخ متناسب با فیلدهای کلاس را با فرمت مورد نظر ورودی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ
     */
    @NotNull
    public String getFormattedString(@NotNull String dateDelimiter) {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        if (!CustomDate.isEmpty(this)) {
            return year + dateDelimiter + CalendarTools.fixOneDigit(month.toString()) + dateDelimiter + CalendarTools.fixOneDigit(day.toString());
        } else {
            return year + dateDelimiter + month + dateDelimiter + day;
        }
    }

    @Override
    public String toString() {
        try {
            return "CustomDate{" + this.getFormattedString("-") + '}';
        } catch (UtilityException e) {
            return "CustomDate{" + e.getMessage() + '}';
        }
    }

    @Override
    public int compareTo(CustomDate customDate) {
        if(ObjectUtils.isEmpty(customDate)){
            return 0;
        }
        int ret;
        if ((this.getYear() == null) || (customDate.getYear() == null)) {
            ret = 0;
        } else {
            if (Objects.equals(this.getYear(), customDate.getYear())) {
                if ((this.getMonth() == null) || (customDate.getMonth() == null)) {
                    ret = 0;
                } else {
                    if (Objects.equals(this.getMonth(), customDate.getMonth())) {
                        if ((this.getDay() == null) || (customDate.getDay() == null)) {
                            ret = 0;
                        } else {
                            ret = this.getDay() - customDate.getDay();
                        }
                    } else {
                        ret = this.getMonth() - customDate.getMonth();
                    }
                }
            } else {
                ret = this.getYear() - customDate.getYear();
            }
        }
        return ret;
    }

}
