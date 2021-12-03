package com.motaharinia.client.project.utility.custom.customvalidation.required;


import com.motaharinia.client.project.utility.custom.customfield.CustomDate;
import com.motaharinia.client.project.utility.custom.customfield.CustomDateTime;
import com.motaharinia.client.project.utility.custom.customfield.CustomHtmlString;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی الزامی بودن فیلد <br>
 * برای همه فیلدها میتوان از این اعتبارسنجی استفاده کرد
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cvc) {
        //بررسی نال بودن شیی یا خالی بودن شیی اگر نوع آن موارد زیر باشد
        //Optional , Array , CharSequence , Collection , Map
        if (ObjectUtils.isEmpty(obj)) {
            return false;
        }
        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomDate باشد
        if ((obj instanceof CustomDate) && CustomDate.isEmpty((CustomDate) obj)) {
            return false;
        }
        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomDateTime باشد
        if ((obj instanceof CustomDateTime) && CustomDateTime.isEmpty((CustomDateTime) obj)) {
            return false;
        }
        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomHtmlString باشد
        return (!(obj instanceof CustomHtmlString)) || (!CustomHtmlString.isEmpty((CustomHtmlString) obj));
    }
}
