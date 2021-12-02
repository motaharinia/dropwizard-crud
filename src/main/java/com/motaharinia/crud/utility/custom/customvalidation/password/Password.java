package com.motaharinia.crud.utility.custom.customvalidation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی رمز عبور با تعیین حداقل و یا پیچیده بودن رمز <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * حداقل طول رمز عبور
     *
     * @return خروجی:
     */
    int min();

    /**
     * حداکثر طول رمز عبور
     *
     * @return خروجی:
     */
    int max() default 16;

    /**
     * بررسی رمز عبور پیچیده انجام شود؟
     *
     * @return خروجی:
     */
    boolean complicated() default false;


    /**
     *کارکترهای خاص رمز پیچیده
     *
     * @return خروجی:
     */
    String complicatedSpecialChars() default "!@#&()–[{}]:;',?/*~$^+=<>";

}
