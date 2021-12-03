package com.motaharinia.client.project.modules.member.rpc.server;

import com.motaharinia.client.project.modules.member.business.service.MemberService;
import com.motaharinia.client.project.modules.member.presentation.MemberDto;
import com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberGrpc;
import com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto;
import com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class MemberFindByIdGrpcImpl extends MemberGrpc.MemberImplBase {

    private final MemberService memberService;

    public MemberFindByIdGrpcImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * @param request          مدل درخواست
     * @param responseObserver شیی مدیریت پاسخ
     */
    @Override
    public void readById(MemberReadByIdRequestDto request, StreamObserver<MemberResponseDto> responseObserver) {
        //ایجاد آبجکت از مدل خروجی
        MemberResponseDto.Builder response = MemberResponseDto.newBuilder();
        //اگر مقدار توکن خالی است خطا صادر میشود
//        if (request.getToken().isEmpty()) {
//            throw new BackUserException("", BUSINESS_EXCEPTION_BACK_USER_TOKEN_INVALID, "");
//        }
        MemberDto memberDto = memberService.readById(request.getId());

        //پر کردن مدل خروجی
        response.setId(memberDto.getId())
                .setFirstName(memberDto.getFirstName())
                .setLastName(memberDto.getLastName())
                .setNationalCode(memberDto.getNationalCode())
                .setDateOfBirth(memberDto.getDateOfBirth())
                .setResponseCode(200)
                .setResponseMessage(Status.OK.toString());

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
