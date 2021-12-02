package com.motaharinia.crud.utility.custom.customvalidation.url;

import com.motaharinia.crud.utility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی نشانی وب <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 * نمونه اعتبارسنجی: regex101.com/r/HUNasA/2
 */
public class UrlValidator implements ConstraintValidator<Url, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }
        return string.matches(CustomValidationRegularExceptionEnum.URL.getValue());
    }
}
