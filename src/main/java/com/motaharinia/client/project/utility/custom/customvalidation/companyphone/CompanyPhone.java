package com.motaharinia.client.project.utility.custom.customvalidation.companyphone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی شماره تلفن ثابت سازمان که میتواند بین 4 تا 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = CompanyPhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyPhone {

    String message() default "CUSTOM_VALIDATION.COMPANY_PHONE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
