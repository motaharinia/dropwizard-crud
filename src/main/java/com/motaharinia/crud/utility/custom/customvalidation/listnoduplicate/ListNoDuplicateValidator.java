package com.motaharinia.crud.utility.custom.customvalidation.listnoduplicate;


import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی عدم وجود عنصر تکراری در لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
public class ListNoDuplicateValidator implements ConstraintValidator<ListNoDuplicate, List> {
    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(list)) {
            return true;
        }
        Set set = new HashSet(list);
        return set.size() >= list.size();
    }
}
