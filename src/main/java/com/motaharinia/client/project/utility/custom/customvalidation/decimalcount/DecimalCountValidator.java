package com.motaharinia.client.project.utility.custom.customvalidation.decimalcount;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده و تعداد دقیق تعداد رقم اعشار<br>
 * فقط برای فیلدهای از نوع Double میتوان از این اعتبارسنجی استفاده کرد
 */
public class DecimalCountValidator implements ConstraintValidator<DecimalCount, Double> {

    private static final String MESSAGE_EXACT = "CUSTOM_VALIDATION.DECIMAL_COUNT_EXACT";
    private static final String MESSAGE_MIN = "CUSTOM_VALIDATION.DECIMAL_COUNT_MIN";
    private static final String MESSAGE_MAX = "CUSTOM_VALIDATION.DECIMAL_COUNT_MAX";
    private static final String UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE = "UTILITY_EXCEPTION.MIN_OR_MAX_IS_NEGATIVE";
    private static final String UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX = "UTILITY_EXCEPTION.MIN_IS_GREATER_THAN_MAX";

    private String message;
    private Integer min;
    private Integer max;
    private Integer exact;

    @Override
    public void initialize(DecimalCount decimalCount) {
        min = decimalCount.min();
        max = decimalCount.max();
        exact = decimalCount.exact();
        message = decimalCount.message();
    }

    @Override
    public boolean isValid(Double number, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(number)) {
            return true;
        }
        boolean result = true;
        String doubleStr = number + "";
        String[] parts = doubleStr.split("\\.");
        String decimalPart = parts[1];

        if (exact > 0) {
            if (!exact.equals(decimalPart.length())) {
                result = false;
                setMessage(MESSAGE_EXACT + "::" + exact);
            }
        } else {
            if (min < 0 || max < 0) {
                result = false;
                setMessage(UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE + "::min=" + min + "max=" + max);
            } else if (min > max) {
                result = false;
                setMessage(UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX + "::min=" + min + "max=" + max);
            } else if (min > 0 && decimalPart.length() < min) {
                result = false;
                setMessage(MESSAGE_MIN + "::" + min);
            } else if (max > 0 && decimalPart.length() > max) {
                result = false;
                setMessage(MESSAGE_MAX + "::" + max);
            }
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
