package com.motaharinia.crud.utility.custom.customexception.ratelimit;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس خطا محدودیت بازدید متد
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class RateLimitException extends RuntimeException {
    /**
     * پیام خطا
     */
    private final String message = "RATE_LIMIT_EXCEPTION.BAN";

}
