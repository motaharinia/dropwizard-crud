package com.motaharinia.crud.utility.custom.customdto;

import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدل پاسخ به کلاینت
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto<T> implements Serializable {
    /**
     * داده برگشتی از سرور به کلاینت
     */
    private T data;
    /**
     * پیامی که کلاینت قرار است نمایش دهد
     */
    private String message;
    /**
     * زمان سرور در هنگام پاسخ
     */
    private Long currentTime = Instant.now().toEpochMilli();
    /**
     * اطلاعات خطا
     */
    private ExceptionDto exception;
    /**
     * کد رزرو برای حالتهای خاص ارتباط بین کلاینت و سرور
     */
    private Integer returnCode = 0;

    public ClientResponseDto(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ClientResponseDto(ExceptionDto exception, String message) {
        this.exception = exception;
        this.message = message;
    }
}