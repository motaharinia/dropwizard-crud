package com.motaharinia.crud.modules.member.presentation;

import com.motaharinia.crud.modules.member.business.service.MemberService;
import com.motaharinia.crud.utility.custom.customdto.ClientResponseDto;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1.0/member")
@Produces(MediaType.APPLICATION_JSON)
public class MemberController {

    private final MemberService memberService;

    /**
     * پیام موفقیت فرم
     */
    private static final String FORM_SUBMIT_SUCCESS = "USER_MESSAGE.FORM_SUBMIT_SUCCESS";

    public MemberController( MemberService memberService) {
        this.memberService = memberService;
    }

    @POST
    public ClientResponseDto<MemberDto> create(@Valid MemberDto dto) {
        return new ClientResponseDto<>(memberService.create(dto), FORM_SUBMIT_SUCCESS);
    }

    @GET
    public ClientResponseDto<List<MemberDto>> readAll() {
        return new ClientResponseDto<>(memberService.readAll(), FORM_SUBMIT_SUCCESS);
    }

    @GET
    @Path("/{id}")
    public ClientResponseDto<MemberDto> readById(@PathParam("id") Long id) {
        return new ClientResponseDto<>(memberService.readById(id), FORM_SUBMIT_SUCCESS);
    }

    @GET
    @Path("/national-code/{nationalCode}")
    public ClientResponseDto<MemberDto> readByNationalCode(@PathParam("nationalCode") String nationalCode) {
        return new ClientResponseDto<>(memberService.readByNationalCode(nationalCode), FORM_SUBMIT_SUCCESS);
    }

    @PUT
    public ClientResponseDto<MemberDto> update(@Valid MemberDto dto) {
        return new ClientResponseDto<>(memberService.update(dto), FORM_SUBMIT_SUCCESS);
    }

    @DELETE
    @Path("/{id}")
    public ClientResponseDto<MemberDto> delete(@PathParam("id") Long id) {
        return new ClientResponseDto<>(memberService.delete(id), FORM_SUBMIT_SUCCESS);
    }
}
