package com.motaharinia.crud.config.log.rest;

import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.crud.utility.tools.exception.ExceptionTools;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدیریت خطای عمومی
 */
public class RestGeneralExceptionTranslator implements ExceptionMapper<Exception> {
    @Context
    protected HttpServletRequest httpServletRequest;

    private final ExceptionDto exceptionDto;

    public RestGeneralExceptionTranslator(ExceptionDto exceptionDto) {
        this.exceptionDto = exceptionDto;
    }

    @Override
    public Response toResponse(Exception exception) {
        exceptionDto.setAppUserId(0L);
        exceptionDto.setAppUsername("username");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ExceptionTools.translate(httpServletRequest, List.of("prod"), exceptionDto, exception)).build();
    }


}
