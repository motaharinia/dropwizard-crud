package com.motaharinia.client.project.utility.custom.customvalidation.personphone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی شماره تلفن منزل که میتواند 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PersonPhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonPhone {

    String message() default "CUSTOM_VALIDATION.PERSON_PHONE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
