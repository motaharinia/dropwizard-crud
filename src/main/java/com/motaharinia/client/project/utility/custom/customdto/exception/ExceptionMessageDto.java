package com.motaharinia.client.project.utility.custom.customdto.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author eng.motahari@gmail.com<br>
 * کلاس شرح مدل اکسپشن
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessageDto implements Serializable {
    /**
     * پیام خطا
     */
    private String message;
    /**
     * مسیر فنی خطا
     */
    private String stackTrace;
    /**
     * خط ابتدای مسیر فنی خطا
     */
    private String stackTraceLine;
    /**
     * مرجع خطا که میتواند طبق نظر توسعه دهنده برای موارد خاص ست شود
     */
    private String reference;

}
