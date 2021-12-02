package com.motaharinia.crud.utility.custom.customvalidation.latincharacters;

import com.motaharinia.crud.utility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رشته با کارکترهای لاتین و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class LatinCharactersValidator implements ConstraintValidator<LatinCharacters, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(string)){
            return true;
        }
        return string.matches(CustomValidationRegularExceptionEnum.LATIN_CHARACTERS.getValue());
    }

}
