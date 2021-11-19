package com.motaharinia.crud.utility.custom.customexception.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس خطای بیزینس پایه که تمامی کلاسهای خطای بیزینس سامانه از آن توسعه می یابند
 */


@Getter
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException implements Serializable {

    /**
     * کلاسی که در آن خطا اتفاق می افتد
     */
    private final String exceptionClassName;
    /**
     * شناسه یونیک داده ای که خطا دز آن اتفاق افتاده
     */
    private final String dataId;
    /**
     * پیام خطا
     */
    private final String message;
    /**
     * توضیحاتی در مورد خطا
     */
    private final String description;


    /**
     * متد سازنده اکسپشن
     *
     * @param exceptionClass       کلاسی که در آن خطا اتفاق می افتد
     * @param dataId     شناسه یونیک داده ای که خطا دز آن اتفاق افتاده
     * @param message     پیام خطا
     * @param description توضیحاتی در مورد خطا
     */
    protected BusinessException(@NotNull Class exceptionClass, @NotNull String dataId, @NotNull String message, String description) {
        this.exceptionClassName = exceptionClass.getSimpleName();
        this.dataId = dataId;
        this.message = message;
        this.description = description;
    }
}
