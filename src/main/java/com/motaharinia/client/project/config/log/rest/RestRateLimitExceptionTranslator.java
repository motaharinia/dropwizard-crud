package com.motaharinia.client.project.config.log.rest;

import com.motaharinia.client.project.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.client.project.utility.custom.customexception.ratelimit.RateLimitException;
import com.motaharinia.client.project.utility.tools.exception.ExceptionTools;
import com.motaharinia.client.project.utility.tools.string.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای محدودیت بازدید
 */
public class RestRateLimitExceptionTranslator implements ExceptionMapper<RateLimitException> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final ExceptionDto exceptionDto;
    private final MessageService messageService;

    public RestRateLimitExceptionTranslator(ExceptionDto exceptionDto,MessageService messageService) {
        this.exceptionDto = exceptionDto;
        this.messageService = messageService;
    }

    @Override
    public Response toResponse(RateLimitException exception) {
        exceptionDto.setAppUserId(0L);
        exceptionDto.setAppUsername("username");
        return Response.status(Response.Status.TOO_MANY_REQUESTS).entity(ExceptionTools.translate(messageService, httpServletRequest, List.of("prod"), exceptionDto,exception)).build();
    }


}
