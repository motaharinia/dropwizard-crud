package com.motaharinia.crud.utility.custom.customvalidation.usernameemailormobile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی کلمه کاربری با پست الکترونیکی یا شماره تلفن همراه<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = UsernameEmailOrMobileValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameEmailOrMobile {

    String message() default "CUSTOM_VALIDATION.USERNAME_EMAIL_OR_MOBILE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
