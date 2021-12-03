package com.motaharinia.client.project.modules.member.business.service;

import com.motaharinia.client.project.modules.member.presentation.MemberDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * @author eng.motahari@gmail.com<br>
 * کلاس اینترفیس سرویس عضو
 */
public interface MemberService {

    /**
     * متد ثبت
     *
     * @param memberDto مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @NotNull
    MemberDto create(@NotNull MemberDto memberDto);


    /**
     * متد جستجو با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @NotNull
    MemberDto readById(@NotNull Long id);

    /**
     * متد جستجو با کد ملی
     *
     * @param nationalCode کد ملی
     * @return خروجی: مدل جستجو شده
     */
    @NotNull
    MemberDto readByNationalCode(@NotNull String nationalCode);

    /**
     * متد جستجو همه موارد
     *
     * @return خروجی: لیست مدلهای جستجو شده
     */
    @NotNull
    List<MemberDto> readAll();


    /**
     * متد ویرایش
     *
     * @param memberDto مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @NotNull
    MemberDto update(@NotNull MemberDto memberDto);

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @NotNull
    MemberDto delete(@NotNull Long id);

}
