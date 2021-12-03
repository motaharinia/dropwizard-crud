package com.motaharinia.crud.config.log.rest;

import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.custom.customexception.externalcall.ExternalCallException;
import com.motaharinia.crud.utility.tools.exception.ExceptionTools;
import com.motaharinia.crud.utility.tools.string.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای فراخوانی سرویسهای بیرونی
 */
public class RestExternalCallExceptionTranslator implements ExceptionMapper<ExternalCallException> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final ExceptionDto exceptionDto;
    private final MessageService messageService;

    public RestExternalCallExceptionTranslator(ExceptionDto exceptionDto,MessageService messageService) {
        this.exceptionDto = exceptionDto;
        this.messageService = messageService;
    }

    @Override
    public Response toResponse(ExternalCallException exception) {
        exceptionDto.setAppUserId(0L);
        exceptionDto.setAppUsername("username");
        return Response.status(Response.Status.CONFLICT).entity(ExceptionTools.translate(messageService, httpServletRequest, List.of("prod"), exceptionDto,exception)).build();
    }


}
