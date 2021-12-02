package com.motaharinia.crud.utility.custom.customvalidation.listlength;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده اندازه لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
public class ListLengthValidator implements ConstraintValidator<ListLength, List> {

    private static final String MESSAGE_EXACT = "CUSTOM_VALIDATION.LIST_LENGTH_EXACT";
    private static final String MESSAGE_MIN = "CUSTOM_VALIDATION.LIST_LENGTH_MIN";
    private static final String MESSAGE_MAX = "CUSTOM_VALIDATION.LIST_LENGTH_MAX";
    private static final String UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE = "UTILITY_EXCEPTION.MIN_OR_MAX_IS_NEGATIVE";
    private static final String UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX = "UTILITY_EXCEPTION.MIN_IS_GREATER_THAN_MAX";

    private String message;
    private Integer min;
    private Integer max;
    private Integer exact;

    @Override
    public void initialize(ListLength listLength) {
        min = listLength.min();
        max = listLength.max();
        exact = listLength.exact();
        message = listLength.message();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(list)) {
            return true;
        }
        boolean result = true;
        if (exact > 0) {
            if (!exact.equals(list.size())) {
                result = false;
                setMessage(MESSAGE_EXACT + "::" + exact);
            }
        } else {
            if (min <= 0 && max <= 0) {
                result = false;
                setMessage(UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE + "::min=" + min + "max=" + max);
            } else if (min > 0 && max > 0 && min > max) {
                result = false;
                setMessage(UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX + "::min=" + min + "max=" + max);
            } else if (min > 0 && list.size() < min) {
                result = false;
                setMessage(MESSAGE_MIN + "::" + min);
            } else if (max > 0 && list.size() > max) {
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
