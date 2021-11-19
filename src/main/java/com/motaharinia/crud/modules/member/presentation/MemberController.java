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
//        Member entity = MemberDB.readById(dto.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<MemberDto> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        return Response.ok(memberService.create(dto)).build();
//        if (entity != null) {
//            MemberDB.update(dto.getId(), dto);
//            return Response.created(new URI("/employees/" + dto.getId()))
//                    .build();
//        } else
//            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response readAll() {
        return Response.ok(memberService.readAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response readById(@PathParam("id") Long id) {
        return Response.ok(memberService.readById(id)).build();
//        if (dto != null)
//            return Response.ok(dto).build();
//        else
//            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MemberDto dto) {
        // validation
        Set<ConstraintViolation<MemberDto>> violations = validator.validate(dto);
//        Member e = MemberDB.readById(dto.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<MemberDto> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        return Response.ok(memberService.update(dto)).build();
//        if (e != null) {
//            dto.setId(id);
//            MemberDB.update(id, dto);
//            return Response.ok(dto).build();
//        } else
//            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(memberService.delete(id)).build();

//        Member member = MemberDB.readById(id);
//        if (member != null) {
//            MemberDB.delete(id);
//            return Response.ok().build();
//        } else
//            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
