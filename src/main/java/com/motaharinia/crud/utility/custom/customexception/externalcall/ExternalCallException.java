package com.motaharinia.crud.utility.custom.customexception.externalcall;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.HttpMethod;
import java.io.Serializable;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس خطای بیزینس پایه که تمامی کلاسهای خطای بیزینس سامانه از آن توسعه می یابند
 */


@Getter
@EqualsAndHashCode(callSuper = true)
public class ExternalCallException extends RuntimeException implements Serializable {

    /**
     * کلاسی که در آن خطا اتفاق می افتد
     */
    private final String exceptionClassName;
    /**
     * نشانی درخواست
     */
    private final String requestUrl;
    /**
     * نوع درخواست وب مانند GET,POST,PUT,DELETE
     */
    private final HttpMethod requestMethod;
    /**
     * کد درخواست
     */
    private final String requestCode;
    /**
     * کد خطای پاسخ
     */
    private final String responseCode;
    /**
     * خطای خاص هر سرویس
     */
    private final String responseCustomError;
    /**
     * اکسپشن پاسخ
     */
    private final Exception responseException;


    /**
     * متد سازنده اکسپشن
     *
     * @param exceptionClass    کلاسی که در آن خطا اتفاق می افتد
     * @param requestUrl        نشانی درخواست
     * @param requestCode       کد درخواست
     * @param responseCode      کد خطای پاسخ
     * @param requestMethod     نوع درخواست وب
     * @param responseCustomError  خطای خاص هر سرویس
     * @param responseException اکسپشن اتفاق افتاده
     */
    public ExternalCallException(@NotNull Class exceptionClass, @NotNull String requestUrl, @NotNull HttpMethod requestMethod, @NotNull String requestCode, @NotNull String responseCode,@NotNull String responseCustomError, @NotNull Exception responseException) {
        super("ExternalCallException");
        this.exceptionClassName = exceptionClass.getSimpleName();
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
        this.requestCode = requestCode;
        this.responseCode = responseCode;
        this.responseCustomError = responseCustomError;
        this.responseException = responseException;
    }


}
