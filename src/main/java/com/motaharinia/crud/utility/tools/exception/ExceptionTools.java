package com.motaharinia.crud.utility.tools.exception;


import com.motaharinia.crud.utility.custom.customdto.ClientResponseDto;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionMessageDto;
import com.motaharinia.crud.utility.custom.customexception.ExceptionTypeEnum;
import com.motaharinia.crud.utility.custom.customexception.business.BusinessException;
import com.motaharinia.crud.utility.custom.customexception.externalcall.ExternalCallException;
import com.motaharinia.crud.utility.custom.customexception.ratelimit.RateLimitException;
import com.motaharinia.crud.utility.tools.string.MessageService;
import com.motaharinia.crud.utility.tools.string.StringTools;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس ابزار خطا که خطا را به مدل تبدیل میکند
 */
public interface ExceptionTools {

    String MASK = "here is production";

    /**
     * متد ترجمه خطا از خطای بیزینس و ایجاد مدل فرانت
     *
     * @param messageService     سرویس ترجمه
     * @param httpServletRequest شیی درخواست وب
     * @param maskedProfileList  لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto       مدل خطا
     * @param businessException  خطای بیزینس
     * @return خروجی: مدل فرانت
     */
    @NotNull
    static ClientResponseDto<Void> translate(@NotNull MessageService messageService, HttpServletRequest httpServletRequest, @NotNull List<String> maskedProfileList, @NotNull ExceptionDto exceptionDto, @NotNull BusinessException businessException) {
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        String translatedMessage = businessException.getMessage();
        if (!ObjectUtils.isEmpty(translatedMessage) && translatedMessage.toUpperCase(Locale.getDefault()).startsWith("BUSINESS_EXCEPTION")) {
            translatedMessage = StringTools.translateCustomMessage(messageService, businessException.getMessage(), httpServletRequest.getLocale());
        }
        messageDtoList.add(new ExceptionMessageDto(translatedMessage, getStackTraceString(businessException), getStackTraceLineString(businessException), ""));
        exceptionDto.setType(ExceptionTypeEnum.BUSINESS_EXCEPTION);
        exceptionDto.setExceptionClassName(businessException.getExceptionClassName());
        exceptionDto.setDataId(businessException.getDataId());
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription(businessException.getDescription());
        return fillAdditionalData(httpServletRequest, maskedProfileList, exceptionDto);
    }

    /**
     * متد ترجمه خطا از خطای اعتبارسنجی پارامترهای متد و ایجاد مدل فرانت
     *
     * @param messageService               سرویس ترجمه
     * @param httpServletRequest           شیی درخواست وب
     * @param maskedProfileList            لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto                 مدل خطا
     * @param constraintViolationException خطای اعتبارسنجی پارامتر متد
     * @return خروجی: مدل فرانت
     */
    @NotNull
    static ClientResponseDto<Void> translate(@NotNull MessageService messageService, HttpServletRequest httpServletRequest, @NotNull List<String> maskedProfileList, @NotNull ExceptionDto exceptionDto, @NotNull ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        exceptionDto.setType(ExceptionTypeEnum.VALIDATION_EXCEPTION);
        String translatedMessage;
        String fieldName;
        for (ConstraintViolation<?> violation : constraintViolationSet) {
            fieldName = "";
            for (Path.Node node : violation.getPropertyPath()) {
                fieldName = node.getName();
            }
            translatedMessage = violation.getMessage();
            if (!ObjectUtils.isEmpty(translatedMessage) && translatedMessage.toUpperCase(Locale.getDefault()).startsWith("CUSTOM_VALIDATION")) {
                translatedMessage = StringTools.translateCustomMessage(messageService, violation.getMessage(), httpServletRequest.getLocale());
            }
            messageDtoList.add(new ExceptionMessageDto(translatedMessage, getStackTraceString(constraintViolationException), getStackTraceLineString(constraintViolationException), fieldName));
        }
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription("");
        return fillAdditionalData(httpServletRequest, maskedProfileList, exceptionDto);
    }

    /**
     * متد ترجمه خطا از خطای فراخوانی سرویسهای بیرونی و ایجاد مدل فرانت
     *
     * @param messageService        سرویس ترجمه
     * @param httpServletRequest    شیی درخواست وب
     * @param maskedProfileList     لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto          مدل خطا
     * @param externalCallException خطای فراخوانی سرویسهای بیرونی
     * @return خروجی: مدل فرانت
     */
    @NotNull
    static ClientResponseDto<Void> translate(@NotNull MessageService messageService, HttpServletRequest httpServletRequest, @NotNull List<String> maskedProfileList, @NotNull ExceptionDto exceptionDto, @NotNull ExternalCallException externalCallException) {
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        String translatedMessage = "ExternalCallException for " + "[" + externalCallException.getRequestMethod().toString() + "]: " + externalCallException.getRequestUrl() + " message:" + externalCallException.getResponseException().getMessage();
        messageDtoList.add(new ExceptionMessageDto(translatedMessage, getStackTraceString(externalCallException), getStackTraceLineString(externalCallException), ""));
        exceptionDto.setType(ExceptionTypeEnum.EXTERNAL_CALL_EXCEPTION);
        exceptionDto.setExceptionClassName(externalCallException.getExceptionClassName());
        exceptionDto.setDataId(externalCallException.getRequestUrl());
        if (!messageDtoList.isEmpty()) {
            if (externalCallException.getResponseCustomError().equalsIgnoreCase("I/O error. Connection refused: connect")) {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.IO::" + externalCallException.getRequestCode(), httpServletRequest.getLocale()));
            } else if (externalCallException.getResponseCode().isEmpty()) {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.UNKNOWN::" + externalCallException.getRequestCode(), httpServletRequest.getLocale()));
            } else {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.CODE::" + externalCallException.getResponseCode() + "," + externalCallException.getRequestCode(), httpServletRequest.getLocale()));
                exceptionDto.setExternalMessage(externalCallException.getResponseCustomError());
            }
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription(externalCallException.getRequestMethod().toString());
        return fillAdditionalData(httpServletRequest, maskedProfileList, exceptionDto);
    }


    /**
     * متد سازنده مدل خطا از خطای محدودیت بازدید و ایجاد مدل فرانت
     *
     * @param messageService     سرویس ترجمه
     * @param httpServletRequest شیی درخواست وب
     * @param maskedProfileList  لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto       مدل خطا
     * @param rateLimitException خطای محدودیت بازدید
     * @return خروجی: مدل فرانت
     */
    @NotNull
    static ClientResponseDto<Void> translate(@NotNull MessageService messageService, HttpServletRequest httpServletRequest, @NotNull List<String> maskedProfileList, @NotNull ExceptionDto exceptionDto, @NotNull RateLimitException rateLimitException) {
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(new ExceptionMessageDto(StringTools.translateCustomMessage(messageService, rateLimitException.getMessage(), httpServletRequest.getLocale()), getStackTraceString(rateLimitException), getStackTraceLineString(rateLimitException), ""));
        exceptionDto.setType(ExceptionTypeEnum.RATE_LIMIT_EXCEPTION);
        exceptionDto.setExceptionClassName("");
        exceptionDto.setDataId("");
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription("");
        return fillAdditionalData(httpServletRequest, maskedProfileList, exceptionDto);
    }

    /**
     * متد سازنده مدل خطا از خطای عمومی و ایجاد مدل فرانت
     *
     * @param httpServletRequest شیی درخواست وب
     * @param maskedProfileList  لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto       مدل خطا
     * @param generalException   خطای عمومی
     * @return خروجی: مدل فرانت
     */
    @NotNull
    static ClientResponseDto<Void> translate(HttpServletRequest httpServletRequest, @NotNull List<String> maskedProfileList, @NotNull ExceptionDto exceptionDto, @NotNull Exception generalException) {
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(new ExceptionMessageDto(generalException.getMessage(), getStackTraceString(generalException), getStackTraceLineString(generalException), ""));
        exceptionDto.setType(ExceptionTypeEnum.GENERAL_EXCEPTION);
        exceptionDto.setExceptionClassName("");
        exceptionDto.setDataId("");
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription("");
        return fillAdditionalData(httpServletRequest, maskedProfileList, exceptionDto);
    }


    /**
     * متد تکمیل اطلاعات مدل خطا و آماده سازی مدل فرانت
     *
     * @param httpServletRequest شیی درخواست وب
     * @param maskedProfileList  لیست پروفایلهایی که شرح فنی در آنها مخفی میشود
     * @param exceptionDto       مدل خطا
     * @return خروجی: مدل فرانت
     */
    static ClientResponseDto<Void> fillAdditionalData(HttpServletRequest httpServletRequest, List<String> maskedProfileList, ExceptionDto exceptionDto) {
        exceptionDto.setAppUrlAddress(getRequestUrl(httpServletRequest));
        exceptionDto.setAppIpAddress(getRequestIpAddress(httpServletRequest));

        ClientResponseDto<Void> clientResponseDto = new ClientResponseDto<>(exceptionDto, exceptionDto.getMessage());
        //مخفی نمودن شرح فنی در پروفایلهای خاص مانند پروداکشن
        if (ObjectUtils.isNotEmpty(maskedProfileList) && maskedProfileList.contains(exceptionDto.getAppProfile())) {
            clientResponseDto.getException().setExceptionClassName(MASK);
            clientResponseDto.getException().setAppPort(MASK);
            clientResponseDto.getException().setDescription(MASK);
            clientResponseDto.getException().setDataId(MASK);
            clientResponseDto.getException().getMessageDtoList().forEach(item -> {
                item.setStackTrace(MASK);
                item.setStackTraceLine(MASK);
                item.setMessage(MASK);
            });

            if (clientResponseDto.getException().getType().equals(ExceptionTypeEnum.GENERAL_EXCEPTION)) {
                clientResponseDto.setMessage(MASK);
                clientResponseDto.getException().setMessage(MASK);
            }
        }
        return clientResponseDto;
    }

    /**
     * این متد رشته stacktrace خطا را خروجی میدهد
     *
     * @param exception خطا
     * @return خروجی: رشته stacktrace خطا
     */
    static String getStackTraceString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    /**
     * این متد خط اول فنی خطا را به صورت رشته خروجی میدهد
     *
     * @param exception خطا
     * @return خروجی: خط اول رشته stacktrace خطا
     */
    static String getStackTraceLineString(Exception exception) {
        if (exception.getStackTrace() != null) {
            StackTraceElement[] stackTraceElements = exception.getStackTrace();
            String relatedToClassName = stackTraceElements[0].getClassName();
            String relatedToMethodName = stackTraceElements[0].getMethodName();
            String relatedToLineNumber = Integer.toString(stackTraceElements[0].getLineNumber());
            return ("ClassName:" + relatedToClassName + " MethodName:" + relatedToMethodName + " LineNumber:" + relatedToLineNumber);
        } else {
            return "";
        }
    }


    /**
     * این متد نشانی وب را از درخواست وب خروجی میدهد
     *
     * @param httpServletRequest درخواست وب
     * @return خروجی: نشانی وب
     */
    static String getRequestUrl(HttpServletRequest httpServletRequest) {
        if (httpServletRequest != null) {
            return httpServletRequest.getServletPath();
        } else {
            return "";
        }
    }

    /**
     * این متد آی پی کاربر را از درخواست وب خروجی میدهد
     *
     * @param httpServletRequest درخواست وب
     * @return خروجی: آی پی کاربر
     */
    static String getRequestIpAddress(HttpServletRequest httpServletRequest) {
        if (httpServletRequest != null) {
            String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = httpServletRequest.getRemoteAddr();
            }
            return ipAddress;
        } else {
            return "";
        }
    }

}
