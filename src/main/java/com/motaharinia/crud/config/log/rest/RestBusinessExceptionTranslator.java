package com.motaharinia.crud.config.log.rest;

import com.motaharinia.crud.config.mvc.MessageService;
import com.motaharinia.crud.utility.custom.customdto.ClientResponseDto;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.custom.customexception.business.BusinessException;
import com.motaharinia.crud.utility.tools.exception.ExceptionTools;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای بیزینس
 */
public class RestBusinessExceptionTranslator implements ExceptionMapper<BusinessException> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final MessageService messageService;

    public RestBusinessExceptionTranslator(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Response toResponse(BusinessException exception) {
        ExceptionDto exceptionDto = ExceptionTools.translate(messageService, exception);
        ExceptionTools.fillAdditionalData(exceptionDto, httpServletRequest, "dropwizard-crud", 8080, "username", 0L);
        return Response.status(Response.Status.BAD_REQUEST).entity(new ClientResponseDto<>(exceptionDto, exceptionDto.getMessage())).build();
    }


}
