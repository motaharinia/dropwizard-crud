package com.motaharinia.crud.utility.custom.customexception.utility;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس اکسپشنهای یوتیلیتی که توسط برنامه نویس در پکیج یوتیلیتی کنترل شده است
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class UtilityException extends RuntimeException implements Serializable {

    /**
     * کلاسی که در آن اکسپشن اتفاق افتاده است
     */
    private final Class exceptionOccurredClass;
    /**
     * کلید خطای اکسپشن که در کلاینت ترجمه خواهد شد
     * در این کلاس کلید ارسالی توسط نوع اکسپشن (بیزینس یا یوتیلیتی یا لیست) و نام کلاسی که خطا در آن اتفاق افتاده کامل میشود
     * تا در کلاینت کلیدهای خطا دسته بندی شده در یک رشته دریافت گردد
     * نمونه:
     * BUSINESS_EXCEPTION.MEMBER.NATIONAL_CODE_DUPLICATE
     */
    private final String exceptionMessage;
    /**
     * توضیحات خطا که میخواهیم به کلاینت ارسال نماییم
     */
    private final String exceptionDescription;


    /**
     * متد سازنده یوتیلیتی اکسپشن
     *
     * @param exceptionOccurredClass کلاسی که در آن اکسپشن اتفاق افتاده است
     * @param exceptionKeyEnum       کلید خطای اکسپشن که در کلاینت ترجمه خواهد شد
     * @param exceptionDescription   توضیحات خطا که میخواهیم به کلاینت ارسال نماییم
     */
    public UtilityException(@NotNull Class exceptionOccurredClass, @NotNull UtilityExceptionKeyEnum exceptionKeyEnum, String exceptionDescription) {
        this.exceptionOccurredClass = exceptionOccurredClass;
        this.exceptionMessage = "UTILITY_EXCEPTION." + exceptionOccurredClass.getSimpleName().toUpperCase() + "." + exceptionKeyEnum.getValue().toUpperCase();
        this.exceptionDescription = exceptionDescription;
    }

    @Override
    public String getMessage() {
        return this.exceptionMessage;
    }
}
