package com.motaharinia.crud.utility.custom.customvalidation.stringpattern;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی الگوی رشته ها<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class StringPatternValidator implements ConstraintValidator<StringPattern, String> {

    private String pattern;

    @Override
    public void initialize(StringPattern stringPattern) {
        pattern = stringPattern.pattern();

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }
        return string.matches(pattern);
    }
}
