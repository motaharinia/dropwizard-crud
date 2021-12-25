package com.motaharinia.client.project.modules.member.business.service;


import com.motaharinia.client.project.modules.member.business.exception.MemberException;
import com.motaharinia.client.project.modules.member.business.mapper.MemberSettingDocumentMapper;
import com.motaharinia.client.project.modules.member.persistence.MemberSettingDocument;
import com.motaharinia.client.project.modules.member.persistence.MemberSettingDocumentDao;
import com.motaharinia.client.project.modules.member.presentation.MemberSettingDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author eng.motahari@gmail.com<br>
 * کلاس پیاده سازی سرویس تنظیمات عضو
 */
public class MemberSettingServiceImpl implements MemberSettingService {


    private final MemberSettingDocumentDao memberSettingDocumentDao;
    private static final MemberSettingDocumentMapper mapper= MemberSettingDocumentMapper.INSTANCE;
    private static final String BUSINESS_EXCEPTION_ID_NOT_FOUND = "BUSINESS_EXCEPTION.ID_NOT_FOUND";

    public MemberSettingServiceImpl(MemberSettingDocumentDao memberSettingDocumentDao) {
        this.memberSettingDocumentDao = memberSettingDocumentDao;
    }

    /**
     * متد ثبت
     *
     * @param memberSettingDto مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public MemberSettingDto create(@NotNull MemberSettingDto memberSettingDto) {
        //ثبت
        MemberSettingDocument document = mapper.toDocument(memberSettingDto);
        memberSettingDocumentDao.save(document);
        memberSettingDto.setId(document.getId());
        return memberSettingDto;
    }


    /**
     * متد جستجو با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    public @NotNull MemberSettingDto readById(@NotNull Long id) {
        //بررسی وجود شناسه و جستجو
        MemberSettingDocument document = memberSettingDocumentDao.findById(id).orElseThrow(() -> new MemberException(id.toString(), BUSINESS_EXCEPTION_ID_NOT_FOUND + "::" + id, ""));
        return mapper.toDto(document);
    }


    /**
     * متد جستجو همه موارد
     *
     * @return خروجی: لیست مدلهای جستجو شده
     */
    @Override
    public @NotNull List<MemberSettingDto> readAll() {
        return memberSettingDocumentDao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }


    /**
     * متد ویرایش
     *
     * @param memberSettingDto مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @Override
    @NotNull
    public MemberSettingDto update(@NotNull MemberSettingDto memberSettingDto) {
        //بررسی وجود شناسه و جستجو
        MemberSettingDocument document = memberSettingDocumentDao.findById(memberSettingDto.getId()).orElseThrow(() -> new MemberException(memberSettingDto.getId().toString(), BUSINESS_EXCEPTION_ID_NOT_FOUND + "::" + memberSettingDto.getId(), ""));
        //ویرایش
        mapper.updateDocument(memberSettingDto, document);
        memberSettingDocumentDao.save(document);
        return memberSettingDto;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    public MemberSettingDto delete(@NotNull Long id) {
        //بررسی وجود شناسه و جستجو
        MemberSettingDocument document = memberSettingDocumentDao.findById(id).orElseThrow(() -> new MemberException(id.toString(), BUSINESS_EXCEPTION_ID_NOT_FOUND + "::" + id, ""));
        MemberSettingDto memberSettingDto = mapper.toDto(document);
        //حذف
        memberSettingDocumentDao.delete(document);
        return memberSettingDto;
    }

}
