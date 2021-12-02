package com.motaharinia.crud.utility.tools.exception;


import com.motaharinia.crud.config.mvc.MessageService;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionMessageDto;
import com.motaharinia.crud.utility.custom.customexception.ExceptionTypeEnum;
import com.motaharinia.crud.utility.custom.customexception.business.BusinessException;
import com.motaharinia.crud.utility.custom.customexception.externalcall.ExternalCallException;
import com.motaharinia.crud.utility.custom.customexception.ratelimit.RateLimitException;
import com.motaharinia.crud.utility.tools.string.StringTools;
import org.apache.commons.lang3.ObjectUtils;

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


    /**
     * متد ترجمه خطا از خطای بیزینس
     *
     * @param messageService    سرویس ترجمه
     * @param businessException خطای بیزینس
     * @return خروجی: مدل خطا
     */
    static ExceptionDto translate(MessageService messageService, BusinessException businessException) {
        ExceptionDto exceptionDto = new ExceptionDto();
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        String translatedMessage = businessException.getMessage();
        if (!ObjectUtils.isEmpty(translatedMessage) && translatedMessage.toUpperCase(Locale.getDefault()).startsWith("BUSINESS_EXCEPTION")) {
            translatedMessage = StringTools.translateCustomMessage(messageService, businessException.getMessage());
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
        return exceptionDto;
    }

    /**
     * متد ترجمه خطا از خطای اعتبارسنجی پارامترهای متد
     *
     * @param messageService               سرویس ترجمه
     * @param constraintViolationException خطای اعتبارسنجی پارامتر متد
     * @return خروجی: مدل خطا
     */
    static ExceptionDto translate(MessageService messageService, ConstraintViolationException constraintViolationException) {
        ExceptionDto exceptionDto = new ExceptionDto();
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
                translatedMessage = StringTools.translateCustomMessage(messageService, violation.getMessage());
            }
            messageDtoList.add(new ExceptionMessageDto(translatedMessage, getStackTraceString(constraintViolationException), getStackTraceLineString(constraintViolationException), fieldName));
        }
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription("");
        return exceptionDto;
    }

    /**
     * متد ترجمه خطا از خطای فراخوانی سرویسهای بیرونی
     *
     * @param messageService        سرویس ترجمه
     * @param externalCallException خطای فراخوانی سرویسهای بیرونی
     * @return خروجی: مدل خطا
     */
    static ExceptionDto translate(MessageService messageService, ExternalCallException externalCallException) {
        ExceptionDto exceptionDto = new ExceptionDto();
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        String translatedMessage = "ExternalCallException for " + "[" + externalCallException.getRequestMethod().toString() + "]: " + externalCallException.getRequestUrl() + " message:" + externalCallException.getResponseException().getMessage();
        messageDtoList.add(new ExceptionMessageDto(translatedMessage, getStackTraceString(externalCallException), getStackTraceLineString(externalCallException), ""));
        exceptionDto.setType(ExceptionTypeEnum.EXTERNAL_CALL_EXCEPTION);
        exceptionDto.setExceptionClassName(externalCallException.getExceptionClassName());
        exceptionDto.setDataId(externalCallException.getRequestUrl());
        if (!messageDtoList.isEmpty()) {
            if (externalCallException.getResponseCustomError().equalsIgnoreCase("I/O error. Connection refused: connect")) {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.IO::" + externalCallException.getRequestCode()));
            } else if (externalCallException.getResponseCode().isEmpty()) {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.UNKNOWN::" + externalCallException.getRequestCode()));
            } else {
                exceptionDto.setMessage(StringTools.translateCustomMessage(messageService, "EXTERNAL_CALL_EXCEPTION.CODE::" + externalCallException.getResponseCode() + "," + externalCallException.getRequestCode()));
                exceptionDto.setExternalMessage(externalCallException.getResponseCustomError());
            }
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription(externalCallException.getRequestMethod().toString());
        return exceptionDto;
    }

    /**
     * متد سازنده مدل خطا از خطای محدودیت بازدید
     *
     * @param messageService     شیی ترجمه
     * @param rateLimitException خطای محدودیت بازدید
     * @return خروجی: مدل خطا
     */
    static ExceptionDto translate(MessageService messageService, RateLimitException rateLimitException) {
        ExceptionDto exceptionDto = new ExceptionDto();
        List<ExceptionMessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(new ExceptionMessageDto(StringTools.translateCustomMessage(messageService, rateLimitException.getMessage()), getStackTraceString(rateLimitException), getStackTraceLineString(rateLimitException), ""));
        exceptionDto.setType(ExceptionTypeEnum.RATE_LIMIT_EXCEPTION);
        exceptionDto.setExceptionClassName("");
        exceptionDto.setDataId("");
        if (!messageDtoList.isEmpty()) {
            exceptionDto.setMessage(messageDtoList.get(0).getMessage());
        }
        exceptionDto.setMessageDtoList(messageDtoList);
        exceptionDto.setDescription("");
        return exceptionDto;
    }

    /**
     * متد سازنده مدل خطا از خطای عمومی نامشخص
     *
     * @param messageService   شیی ترجمه
     * @param generalException خطای عمومی نامشخص
     * @return خروجی: مدل خطا
     */
    static ExceptionDto translate(MessageService messageService, Exception generalException) {
        ExceptionDto exceptionDto = new ExceptionDto();
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
        return exceptionDto;
    }


    /**
     * متد سازنده مدل اولیه خطا
     *
     * @param exceptionDto       مدل خطا
     * @param httpServletRequest شیی درخواست وب
     * @param appName            نام سرویس
     * @param appPort            پورت سرویس
     * @param username           کلمه کاربری کاربر لاگین شده
     * @param userId             شناسه کاربر لاگین شده
     */
    static void fillAdditionalData(ExceptionDto exceptionDto, HttpServletRequest httpServletRequest, String appName, int appPort, String username, Long userId) {
        exceptionDto.setAppName(appName);
        exceptionDto.setAppPort(String.valueOf(appPort));
        exceptionDto.setUrl(getRequestUrl(httpServletRequest));
        exceptionDto.setIpAddress(getRequestIpAddress(httpServletRequest));
        exceptionDto.setUsername(username);
        exceptionDto.setUserId(userId);

        //        //در صورتی که در محیط پروداکشن هستیم اطلاعات زیادی به فرانت ارسال نشود
//        if (environment.getActiveProfiles().length > 0 && Arrays.stream(environment.getActiveProfiles()).anyMatch("prod"::equals)) {
//            clientResponseDto.getException().setExceptionClassName("here is production");
//            clientResponseDto.getException().setAppPort("here is production");
//            clientResponseDto.getException().setDescription("here is production");
//            clientResponseDto.getException().setDataId("here is production");
//            clientResponseDto.getException().getMessageDtoList().stream().forEach(item -> {
//                item.setStackTrace("here is production");
//                item.setStackTraceLine("here is production");
//                item.setMessage("here is production");
//            });
//
//            if ( clientResponseDto.getException().getType().equals(ExceptionTypeEnum.GENERAL_EXCEPTION)) {
//                clientResponseDto.setMessage("here is production");
//                clientResponseDto.getException().setMessage("here is production");
//            }
//        }
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
