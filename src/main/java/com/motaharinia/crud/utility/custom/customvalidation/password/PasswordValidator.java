package com.motaharinia.crud.utility.custom.customvalidation.password;

import com.motaharinia.crud.utility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رمز عبور با تعیین حداقل و یا پیچیده بودن رمز <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String MESSAGE_MIN = "CUSTOM_VALIDATION.PASSWORD_MIN";
    private static final String MESSAGE_MAX = "CUSTOM_VALIDATION.PASSWORD_MAX";
    private static final String MESSAGE_COMPLICATED = "CUSTOM_VALIDATION.PASSWORD_COMPLICATED";
    private static final String UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE = "UTILITY_EXCEPTION.MIN_OR_MAX_IS_NEGATIVE";
    private static final String UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX = "UTILITY_EXCEPTION.MIN_IS_GREATER_THAN_MAX";

    private String message;
    private Integer min;
    private Integer max;
    private Boolean complicated;
    private String complicatedSpecialChars;

    @Override
    public void initialize(Password password) {
        min = password.min();
        max = password.max();
        complicated = password.complicated();
        complicatedSpecialChars = password.complicatedSpecialChars();
        message = password.message();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(password)) {
            return true;
        }

        boolean result = true;
        if (min < 0 || max < 0) {
            result = false;
            setMessage(UTILITY_EXCEPTION_MIN_OR_MAX_IS_NEGATIVE + "::min=" + min + "max=" + max);
        } else if (min > max) {
            result = false;
            setMessage(UTILITY_EXCEPTION_MIN_IS_GREATER_THAN_MAX + "::min=" + min + "max=" + max);
        } else if (password.length() < min) {
            setMessage(MESSAGE_MIN + "::" + min);
            result = false;
        } else if (password.length() > max) {
            setMessage(MESSAGE_MAX + "::" + max);
            result = false;
        }
        if (complicated.equals(true) && (!password.matches(CustomValidationRegularExceptionEnum.PASSWORD_COMPLEXITY.getValue().replace("%COMPLECATED_SPECIAL_CHARS%", complicatedSpecialChars)))) {
            setMessage(MESSAGE_COMPLICATED);
            result = false;
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
