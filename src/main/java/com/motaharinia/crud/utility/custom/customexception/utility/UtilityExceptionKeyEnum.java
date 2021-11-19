package com.motaharinia.crud.utility.custom.customexception.utility;


/**
 * @author https://github.com/motaharinia<br>
 * مقادیر ثابت کلید اکسپشنهای یوتیلیتی که در کلاینت ساید پروژه ترجمه آنها طبق زبان انتخاب شده کاربر نمایش داده خواهد شد
 */
public enum UtilityExceptionKeyEnum {

    /**
     * یکی از ورودی های متد مقدار null و یا خالی دارد
     */
    METHOD_PARAMETER_IS_NULL_OR_EMPTY("METHOD_PARAMETER_IS_NULL_OR_EMPTY"),
    /**
     * یکی از ورودی های متد مقدار صفر و یا منفی دارد
     */
    METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE("METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE"),
    /**
     * فرمت تاریخ رشته ورودی صحیح نمیباشد
     */
    INCORRECT_STRING_DATE_FORMAT("INCORRECT_STRING_DATE_FORMAT"),
    /**
     * تاریخ معتبر نمیباشد
     */
    DATE_VALIDATION_FAILED("DATE_VALIDATION_FAILED"),
    /**
     * تاریخ-زمان معتبر نمیباشد
     */
    DATE_TIME_VALIDATION_FAILED("DATE_TIME_VALIDATION_FAILED"),
    /**
     * فونت مورد نیاز برای تولید کد کپچا یافت نشد
     */
    CAPTCHA_FONT_FILE_IS_NOT_EXIST("CAPTCHA_FONT_FILE_IS_NOT_EXIST"),
    /**
     * تصویر پس زمینه مورد نیاز برای تولید کد کپچا یافت نشد
     */
    CAPTCHA_BACKGROUND_FILE_IS_NOT_EXIST("CAPTCHA_BACKGROUND_FILE_IS_NOT_EXIST"),
    /**
     * رفلکشن: فیلد مورد نظر مطابق نام ورودی در کلاس وجود ندارد
     */
    REFLECTION_FIELD_IS_NOT_EXISTED("REFLECTION_FIELD_IS_NOT_EXISTED"),
    /**
     * رفلکشن: متد مورد نظر مطابق نام ورودی در کلاس وجود ندارد
     */
    REFLECTION_METHOD_IS_NOT_EXISTED("REFLECTION_METHOD_IS_NOT_EXISTED"),

    /**
     * ابزار فایل: فرمت نام فایل ورودی صحیح نمیباشد
     */
    FSO_FILE_NAME_FORMAT_IS_INVALID("FSO_FILE_NAME_FORMAT_IS_INVALID"),
    /**
     * ابزار فایل: مسیر داده شده در فایل سیستم وجود ندارد
     */
    FSO_PATH_IS_NOT_EXISTED("FSO_PATH_IS_NOT_EXISTED"),
    /**
     * ابزار فایل: مسیر داده شده در فایل سیستم از نوع دایرکتوری نیست
     */
    FSO_PATH_IS_NOT_DIRECTORY("FSO_PATH_IS_NOT_DIRECTORY"),
    /**
     * ابزار فایل: مسیر مقصد از قبل در فایل سیستم وجود دارد
     */
    FSO_DESTINATION_PATH_EXISTED("FSO_DESTINATION_PATH_EXISTED"),
    /**
     * ابزار فایل: مسیر فایل داده شده صحیح نمیباشد
     */
    FSO_MIMETYPE_NOT_VALID_FILE_PATH("FSO_MIMETYPE_NOT_VALID_FILE_PATH"),
    /**
     * ابزار فایل: امکان ساخت دایرکتوری وجود ندارد
     */
    FSO_DIRECTORY_CREATION_FAILED("FSO_DIRECTORY_CREATION_FAILED"),
    /**
     * ابزار تصویر: تصویر ورودی قابل خواندن نیست
     */
    IMAGE_ORIGINAL_READ_PROBLEM("IMAGE_ORIGINAL_READ_PROBLEM"),
    /**
     *خطای ابزار جستجو
     */
    SEARCH_TOOLS_EXCEPTION("SEARCH_TOOLS_EXCEPTION"),
    ;

    private final String value;

    UtilityExceptionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
