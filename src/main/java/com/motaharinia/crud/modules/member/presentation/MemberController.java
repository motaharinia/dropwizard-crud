package com.motaharinia.crud.modules.member.presentation;

import com.motaharinia.crud.modules.member.business.service.MemberService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

@Path("/api/v1.0/member")
@Produces(MediaType.APPLICATION_JSON)
public class MemberController {

    private final Validator validator;
    private final MemberService memberService;

    public MemberController(Validator validator,MemberService memberService) {
        this.validator = validator;
        this.memberService = memberService;
    }

    @POST
    public Response create(MemberDto dto) throws URISyntaxException {
        // validation
        Set<ConstraintViolation<MemberDto>> violations = validator.validate(dto);
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<MemberDto> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        return Response.ok(memberService.create(dto)).build();
    }

    @GET
    public Response readAll() {
        return Response.ok(memberService.readAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response readById(@PathParam("id") Long id) {
        return Response.ok(memberService.readById(id)).build();
    }

    @GET
    @Path("/national-code/{nationalCode}")
    public Response readByNationalCode(@PathParam("nationalCode") String nationalCode) {
        return Response.ok(memberService.readByNationalCode(nationalCode)).build();
    }

    @PUT
    public Response update(MemberDto dto) {
        // validation
        Set<ConstraintViolation<MemberDto>> violations = validator.validate(dto);
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<MemberDto> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        return Response.ok(memberService.update(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(memberService.delete(id)).build();
    }
}
