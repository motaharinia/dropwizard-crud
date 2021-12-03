package com.motaharinia.client.project.config.log.rest;

import com.motaharinia.client.project.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.client.project.utility.custom.customexception.business.BusinessException;
import com.motaharinia.client.project.utility.tools.exception.ExceptionTools;
import com.motaharinia.client.project.utility.tools.string.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای بیزینس
 */
public class RestBusinessExceptionTranslator implements ExceptionMapper<BusinessException> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final ExceptionDto exceptionDto;
    private final MessageService messageService;

    public RestBusinessExceptionTranslator(ExceptionDto exceptionDto, MessageService messageService) {
        this.exceptionDto = exceptionDto;
        this.messageService = messageService;
    }

    @Override
    public Response toResponse(BusinessException exception) {
        exceptionDto.setAppUserId(0L);
        exceptionDto.setAppUsername("username");
        return Response.status(Response.Status.BAD_REQUEST).entity(ExceptionTools.translate(messageService, httpServletRequest, List.of("prod"), exceptionDto,exception)).build();
    }


}
