package com.motaharinia.crud.utility.custom.customvalidation.companyphone;

import com.motaharinia.crud.utility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی شماره تلفن ثابت سازمان که میتواند بین 4 تا 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class CompanyPhoneValidator implements ConstraintValidator<CompanyPhone, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(phone)) {
            return true;
        }
        return phone.matches(CustomValidationRegularExceptionEnum.COMPANY_PHONE.getValue());
    }

}
