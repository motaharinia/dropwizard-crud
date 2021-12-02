//package com.motaharinia.crud.config.log;
//
//import com.motaharinia.crud.config.mvc.MessageService;
//import com.motaharinia.crud.utility.custom.customdto.ClientResponseDto;
//import com.motaharinia.crud.utility.tools.exception.ExceptionTools;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// *کلاس مدیریت یکپارچه خطای دریافتی در rest یا graphql
// */
//@Singleton
//@Slf4j
//public class ExceptionLogger2 {
//    /**
//     * مترجم پیامها
//     */
//    private final MessageService messageService;
//
//
//    private static final String SECURITY_EXCEPTION_401 = "SECURITY_EXCEPTION.401";
//    private static final String SECURITY_EXCEPTION_403 = "SECURITY_EXCEPTION.403";
//
//    @Inject
//    public ExceptionLogger2(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    /**
//     * این متد خطای دریافتی در rest یا graphql را یکپارچه مدیریت میکند
//     * @param exception خطا
//     * @param httpServletRequest شیی درخواست وب
//     * @param httpServletResponse شیی پاسخ وب
//     * @return خروجی: پاسخ فرانت
//     */
//    public ClientResponseDto<String> handle(Exception exception, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//        //مدیریت خطای 403 برای متدهای preAuthorize که به متد accessDeniedHandler تنظیمات ریسورس نمیرود
////        if (exception instanceof AccessDeniedException) {
////            securityException403(httpServletRequest,httpServletResponse,messageService);
////            return null;
////        }
//
//        //در صورتی که security فعال باشد تلاش میکنیم شناسه و کلمه کاربری شخص لاگین شده را در مدل ثبت کنیم
//        Long userId = null;
//        String username = null;
////        Optional<LoggedInUserDto> loggedInUserDtoOptional = resourceTokenProvider.getLoggedInDto();
////        if (loggedInUserDtoOptional.isPresent()) {
////            userId = loggedInUserDtoOptional.get().getId();
////            username = loggedInUserDtoOptional.get().getUsername();
////        }
//
//        //دریافت مدل خروجی خطا
//        ClientResponseDto<String> clientResponseDto = ExceptionTools.doException(exception, httpServletRequest, httpServletResponse, "dropwizard-crud", 8080, messageService, userId, username);
//
//        //لاگ کردن خطا در elk
////        log.error(clientResponseDto.getException().getMessageDtoList().get(0).getMessage(), kv("exceptionDto", clientResponseDto.getException()));
//
//        //لاگ کرد شرح خطا در کنسول
//        log.error("ResponseException: {} StackTrace:{}", clientResponseDto.getException().getMessage(), clientResponseDto.getException().getMessageDtoList().get(0).getStackTrace());
//
////        //در صورتی که در محیط پروداکشن هستیم اطلاعات زیادی به فرانت ارسال نشود
////        if (environment.getActiveProfiles().length > 0 && Arrays.stream(environment.getActiveProfiles()).anyMatch("prod"::equals)) {
////            clientResponseDto.getException().setExceptionClassName("here is production");
////            clientResponseDto.getException().setAppPort("here is production");
////            clientResponseDto.getException().setDescription("here is production");
////            clientResponseDto.getException().setDataId("here is production");
////            clientResponseDto.getException().getMessageDtoList().stream().forEach(item -> {
////                item.setStackTrace("here is production");
////                item.setStackTraceLine("here is production");
////                item.setMessage("here is production");
////            });
////
////            if ( clientResponseDto.getException().getType().equals(ExceptionTypeEnum.GENERAL_EXCEPTION)) {
////                clientResponseDto.setMessage("here is production");
////                clientResponseDto.getException().setMessage("here is production");
////            }
////        }
//
//        return clientResponseDto;
//    }
//
//
////    /**
////     * متد بررسی خطای 401 عدم احراز هویت امنیت
////     * @param httpServletRequest شیی درخواست وب
////     * @param httpServletResponse شیی پاسخ وب
////     */
////    public static void securityException401(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,MessageSource messageService){
////        httpServletResponse.setContentType("application/json;charset=UTF-8");
////        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("timestamp", CalendarTools.getCurrentJalaliDateTimeString("/"));
////        jsonObject.put("api_url", getFullURL(httpServletRequest));
////        jsonObject.put("api_method", httpServletRequest.getMethod());
////        jsonObject.put("message", StringTools.translateCustomMessage(messageService,SECURITY_EXCEPTION_401));
////        try {
////            httpServletResponse.getWriter().write(jsonObject.toString());
////        } catch (Exception getWriterException) {
////            throw new RuntimeException(getWriterException.getMessage());
////        }
////    }
////
////    /**
////     * متد بررسی خطای 403 عدم دسترسی امنیت
////     * @param httpServletRequest شیی درخواست وب
////     * @param httpServletResponse شیی پاسخ وب
////     */
////    public static void securityException403(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,MessageSource messageService){
////        httpServletResponse.setContentType("application/json;charset=UTF-8");
////        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("timestamp", CalendarTools.getCurrentJalaliDateTimeString("/"));
////        jsonObject.put("api_url", getFullURL(httpServletRequest));
////        jsonObject.put("api_method", httpServletRequest.getMethod());
////        jsonObject.put("message", StringTools.translateCustomMessage(messageService,SECURITY_EXCEPTION_403) );
////        try {
////            httpServletResponse.getWriter().write(jsonObject.toString());
////        } catch (Exception getWriterException) {
////            throw new RuntimeException(getWriterException.getMessage());
////        }
////    }
////
////    /**
////     * متد به دست آورنده url از روی شیی درخواست وب
////     * @param httpServletRequest شیی درخواست وب
////     * @return خروجی: مسیر
////     */
////    private static String getFullURL(HttpServletRequest httpServletRequest) {
////        StringBuilder requestURL = new StringBuilder(httpServletRequest.getRequestURL().toString());
////        String queryString = httpServletRequest.getQueryString();
////        if (queryString == null) {
////            return requestURL.toString();
////        } else {
////            return requestURL.append('?').append(queryString).toString();
////        }
////    }
//}
