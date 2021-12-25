package com.motaharinia.client.project.modules.member.business.service;



import com.google.inject.ImplementedBy;
import com.motaharinia.client.project.modules.member.presentation.MemberSettingDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * @author eng.motahari@gmail.com<br>
 * کلاس اینترفیس سرویس تنظیمات عضو
 */
@ImplementedBy(MemberSettingServiceImpl.class)
public interface MemberSettingService {

    /**
     * متد ثبت
     *
     * @param memberSettingDto مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @NotNull
    MemberSettingDto create(@NotNull MemberSettingDto memberSettingDto);

    /**
     * متد جستجو با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @NotNull
    MemberSettingDto readById(@NotNull Long id);

    /**
     * متد جستجو همه موارد
     *
     * @return خروجی: لیست مدلهای جستجو شده
     */
    @NotNull
    List<MemberSettingDto> readAll();

    /**
     * متد ویرایش
     *
     * @param memberSettingDto مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @NotNull
    MemberSettingDto update(@NotNull MemberSettingDto memberSettingDto);

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @NotNull
    MemberSettingDto delete(@NotNull Long id);

}
