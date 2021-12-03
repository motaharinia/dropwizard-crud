
package com.motaharinia.client.project.utility.custom.customvalidation.daterange;


import com.motaharinia.client.project.utility.custom.customfield.CustomDate;
import com.motaharinia.client.project.utility.tools.calendar.CalendarTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده تاریخ<br>
 * فقط برای فیلدهای از نوع CustomDate میتوان از این اعتبارسنجی استفاده کرد
 */
@Slf4j
public class CustomDateRangeValidator implements ConstraintValidator<CustomDateRange, CustomDate> {

    private static final String MESSAGE_FROM = "CUSTOM_VALIDATION.DATE_RANGE_FROM";
    private static final String MESSAGE_TO = "CUSTOM_VALIDATION.DATE_RANGE_TO";
    private static final String UTILITY_EXCEPTION_CUSTOM_DATE_RANGE = "UTILITY_EXCEPTION.CUSTOM_DATE_RANGE";

    private static final String DATE_UNLIMITED = "unlimited";
    private static final String DATE_TODAY = "today";

    private String message;
    private String from;
    private String to;

    @Override
    public void initialize(CustomDateRange customDateRange) {
        from = customDateRange.from();
        to = customDateRange.to();
        message = customDateRange.message();
    }

    @Override
    public boolean isValid(CustomDate customDate, ConstraintValidatorContext cvc) {
        if (CustomDate.isEmpty(customDate)) {
            return true;
        }

        if (!validateParameters()) {
            return false;
        }

        boolean result;
        try {
            String jalaliDate = CalendarTools.fixToLocaleDate(customDate, "-", new Locale("fa", "IR"));
            LocalDate localDate = CalendarTools.jalaliToGregorianLocalDate(jalaliDate,"-",ZoneId.systemDefault());
            result = (validateFrom(localDate) && validateTo(localDate));
        } catch (Exception e) {
            setMessage(UTILITY_EXCEPTION_CUSTOM_DATE_RANGE + "::" + e.getMessage());
            result = false;
        }
        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return result;
    }

    private boolean validateParameters() {
        //بررسی صحت تاریخ ابتدای محدوده شرطی
        try {
            if (!from.equalsIgnoreCase(DATE_UNLIMITED) && !from.equalsIgnoreCase(DATE_TODAY)) {
                CalendarTools.jalaliToGregorianLocalDate(from, "-", ZoneId.systemDefault());
            }
        } catch (Exception e) {
            setMessage(UTILITY_EXCEPTION_CUSTOM_DATE_RANGE + "::'from' format is not valid");
            return false;
        }

        //بررسی صحت تاریخ انتهای محدوده شرطی
        try {
            if (!to.equalsIgnoreCase(DATE_UNLIMITED) && !to.equalsIgnoreCase(DATE_TODAY)) {
                CalendarTools.jalaliToGregorianLocalDate(to, "-", ZoneId.systemDefault());
            }
        } catch (Exception e) {
            setMessage(UTILITY_EXCEPTION_CUSTOM_DATE_RANGE + "::'to' format is not valid");
            return false;
        }

        return true;
    }


    private boolean validateFrom(@NotNull LocalDate localDate) {
        if (from.equalsIgnoreCase(DATE_UNLIMITED)) {
            return true;
        }
        LocalDate fromLocalDate = LocalDate.now();
        if (!from.equalsIgnoreCase(DATE_TODAY)) {
            fromLocalDate = CalendarTools.jalaliToGregorianLocalDate(from, "-", ZoneId.systemDefault());
        }
        if (localDate.isBefore(fromLocalDate)) {
            setMessage(MESSAGE_FROM + "::" + CalendarTools.gregorianToJalaliString(fromLocalDate, "-", false));
            return false;
        }
        return true;
    }

    private boolean validateTo(@NotNull LocalDate localDate) {
        if (to.equalsIgnoreCase(DATE_UNLIMITED)) {
            return true;
        }
        LocalDate toLocalDate = LocalDate.now();
        if (!to.equalsIgnoreCase(DATE_TODAY)) {
            toLocalDate = CalendarTools.jalaliToGregorianLocalDate(to, "-", ZoneId.systemDefault());
        }
        if (localDate.isAfter(toLocalDate)) {
            setMessage(MESSAGE_TO + "::" + CalendarTools.gregorianToJalaliString(toLocalDate, "-", false));
            return false;
        }
        return true;
    }

    private void setMessage(String conditionalMessage) {
        if (ObjectUtils.isEmpty(message)) {
            message = conditionalMessage;
        }
    }
}
