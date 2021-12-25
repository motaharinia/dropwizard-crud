package com.motaharinia.client.project.modules.member.business.service;


import com.motaharinia.client.project.modules.member.business.exception.MemberException;
import com.motaharinia.client.project.modules.member.business.mapper.MemberMapper;
import com.motaharinia.client.project.modules.member.persistence.Member;
import com.motaharinia.client.project.modules.member.persistence.MemberDao;
import com.motaharinia.client.project.modules.member.presentation.MemberDto;
import com.motaharinia.client.project.modules.member.presentation.MemberSettingDto;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس پیاده سازی سرویس عضو
 */
@Transaction
public class MemberServiceImpl implements MemberService {


    private final MemberDao memberDao;
    private final MemberSettingService memberSettingService;
    private static final MemberMapper mapper= MemberMapper.INSTANCE;
    private static final String BUSINESS_EXCEPTION_NATIONAL_CODE_DUPLICATE = "BUSINESS_EXCEPTION.NATIONAL_CODE_DUPLICATE";
    private static final String BUSINESS_EXCEPTION_ID_NOT_FOUND = "BUSINESS_EXCEPTION.ID_NOT_FOUND";
    private static final String BUSINESS_EXCEPTION_NATIONAL_CODE_NOT_FOUND = "BUSINESS_EXCEPTION.NATIONAL_CODE_NOT_FOUND";

    public MemberServiceImpl(MemberDao memberDao, MemberSettingService memberSettingService) {
        this.memberDao = memberDao;
        this.memberSettingService = memberSettingService;
    }

    /**
     * متد ثبت
     *
     * @param memberDto مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public MemberDto create(@NotNull MemberDto memberDto) {
        //بررسی عدم تکراری بودن کد ملی
        if (memberDao.findByNationalCode(memberDto.getNationalCode()).isPresent()) {
            throw new MemberException(memberDto.getNationalCode(), BUSINESS_EXCEPTION_NATIONAL_CODE_DUPLICATE + "::" + memberDto.getNationalCode(), "");
        }
        //ثبت
        Member member = mapper.toEntity(memberDto);
        Long id = memberDao.create(member);
        memberDto.setId(id);

        //ثبت تنظیمات
        MemberSettingDto documentDto= memberSettingService.create(memberDto.getSetting());
        member.setSettingId(documentDto.getId());
        memberDao.update(member);
        return memberDto;
    }


    /**
     * متد جستجو با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    public @NotNull MemberDto readById(@NotNull Long id) {
        //بررسی وجود شناسه و جستجو
        Member member = memberDao.findById(id).orElseThrow(() -> new MemberException(id.toString(), BUSINESS_EXCEPTION_ID_NOT_FOUND + "::" + id, ""));
        return mapper.toDto(member);
    }

    /**
     * متد جستجو با کد ملی
     *
     * @param nationalCode کد ملی
     * @return خروجی: مدل جستجو شده
     */
    @Override
    public @NotNull MemberDto readByNationalCode(@NotNull String nationalCode) {
        //بررسی وجود کدملی و جستجو
        Member member = memberDao.findByNationalCode(nationalCode).orElseThrow(() -> new MemberException(nationalCode, BUSINESS_EXCEPTION_NATIONAL_CODE_NOT_FOUND + "::" + nationalCode, ""));
        return mapper.toDto(member);
    }


    /**
     * متد جستجو همه موارد
     *
     * @return خروجی: لیست مدلهای جستجو شده
     */
    @Override
    public @NotNull List<MemberDto> readAll() {
        return memberDao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }


    /**
     * متد ویرایش
     *
     * @param memberDto مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @Override
    @NotNull
    public MemberDto update(@NotNull MemberDto memberDto) {
        //بررسی وجود شناسه و جستجو
        Member member = memberDao.findById(memberDto.getId()).orElseThrow(() -> new MemberException(memberDto.getId().toString(), BUSINESS_EXCEPTION_ID_NOT_FOUND + "::" + memberDto.getId(), ""));
        //ویرایش
        mapper.updateEntity(memberDto, member);
        memberDao.update(member);
        return memberDto;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    public MemberDto delete(@NotNull Long id) {
        //بررسی وجود شناسه و جستجو
        MemberDto memberDto = this.readById(id);
        //حذف
        memberDao.delete(id);
        return memberDto;
    }

}
