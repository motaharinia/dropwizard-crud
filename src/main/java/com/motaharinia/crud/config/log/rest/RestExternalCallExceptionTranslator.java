package com.motaharinia.crud.config.log.rest;

import com.motaharinia.crud.config.mvc.MessageService;
import com.motaharinia.crud.utility.custom.customdto.ClientResponseDto;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.custom.customexception.externalcall.ExternalCallException;
import com.motaharinia.crud.utility.tools.exception.ExceptionTools;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای فراخوانی سرویسهای بیرونی
 */
public class RestExternalCallExceptionTranslator implements ExceptionMapper<ExternalCallException> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final MessageService messageService;

    public RestExternalCallExceptionTranslator(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Response toResponse(ExternalCallException exception) {
        ExceptionDto exceptionDto = ExceptionTools.translate( messageService, exception);
        ExceptionTools.fillAdditionalData(exceptionDto, httpServletRequest, "dropwizard-crud", 8080, "username", 0L);
        return Response.status(Response.Status.TOO_MANY_REQUESTS).entity(new ClientResponseDto<>(exceptionDto, exceptionDto.getMessage())).build();
    }


}
