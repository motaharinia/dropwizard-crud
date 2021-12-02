package com.motaharinia.crud.utility.custom.customvalidation.organizationnationalcode;

import com.motaharinia.crud.utility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی کد ملی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class OrganizationNationalCodeValidator implements ConstraintValidator<OrganizationNationalCode, String> {
    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(nationalCode)) {
            return true;
        }
        return nationalCode.matches(CustomValidationRegularExceptionEnum.ORGANIZATION_NATIONAL_CODE.getValue());
    }
}
