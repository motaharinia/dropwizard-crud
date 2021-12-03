package com.motaharinia.crud.utility.custom.customdto.exception;

import com.motaharinia.crud.utility.custom.customexception.ExceptionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدل اکسپشن
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto implements Serializable {
    /**
     * کلید لاگ که برای پیدا کردن خطا در کیبانا یا فایل لاگ میتواند استفاده شود
     */
    private String logKey = UUID.randomUUID().toString();
    /**
     * نوع خطا
     */
    private ExceptionTypeEnum type;
    /**
     * کلاسی که در آن خطا اتفاق می افتد
     */
    private String exceptionClassName;
    /**
     * شناسه یونیک داده ای که خطا دز آن اتفاق افتاده
     */
    private String dataId;
    /**
     * اولین پیام خطا
     */
    private String message;
    /**
     * پیام خطای اکسترنال
     */
    private String externalMessage;
    /**
     * پیامهای خطا
     */
    private List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
    /**
     * توضیحاتی در مورد خطا
     */
    private String description;
    /**
     * زمانی که در آن خطا اتفاق افتاده است
     */
    private String dateOfException = String.valueOf(Instant.now().toEpochMilli());
    /**
     * پروفایل سامانه
     */
    private String appProfile;
    /**
     * نام سامانه
     */
    private String appName;
    /**
     * پورت سامانه
     */
    private String appPort;
    /**
     * شناسه کاربر (در صورتی که لاگین باشد) در زمان بروز خطا
     */
    private Long appUserId;
    /**
     * کلمه کاربری کاربر (در صورتی که لاگین باشد) در زمان بروز خطا
     */
    private String appUsername;
    /**
     * نشانی وب در زمان بروز خطا
     */
    private String appUrlAddress;
    /**
     * نشانی آی پی کاربر در زمان بروز خطا
     */
    private String appIpAddress;

    public ExceptionDto(String appProfile, String appName, String appPort) {
        this.appProfile = appProfile;
        this.appName = appName;
        this.appPort = appPort;
    }
}
