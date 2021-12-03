package com.motaharinia.client.project.utility.custom.customvalidation.integerrange;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده اعداد صحیح<br>
 * فقط برای فیلدهای از نوع Integer میتوان از این اعتبارسنجی استفاده کرد
 */
public class IntegerRangeValidator implements ConstraintValidator<IntegerRange, Integer> {

    private static final String MESSAGE_MIN = "CUSTOM_VALIDATION.INTEGER_RANGE_MIN";
    private static final String MESSAGE_MAX = "CUSTOM_VALIDATION.INTEGER_RANGE_MAX";
    private static final String UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX = "UTILITY_EXCEPTION.MIN_IS_GREATER_THAN_MAX";

    private String message;
    private Integer min;
    private Integer max;

    @Override
    public void initialize(IntegerRange integerRange) {
        min = integerRange.min();
        max = integerRange.max();
        message = integerRange.message();
    }

    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(number)) {
            return true;
        }

        boolean result = true;
        if (min > max) {
            result = false;
            setMessage(UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX + "::min=" + min + "max=" + max);
        } else if (number < min) {
            result = false;
            setMessage(MESSAGE_MIN + "::" + min);
        } else if (number > max) {
            result = false;
            setMessage(MESSAGE_MAX + "::" + max);
        }

        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return result;
    }

    /**
     * تنظیم پیام خطای پیش فرض در صورتی که توسعه دهنده پیام خاصی در انوتیشن ست نکرده باشد
     * @param conditionalMessage خطای پیش فرض
     */
    private void setMessage(String conditionalMessage) {
        if (ObjectUtils.isEmpty(message)) {
            message = conditionalMessage;
        }
    }
}
