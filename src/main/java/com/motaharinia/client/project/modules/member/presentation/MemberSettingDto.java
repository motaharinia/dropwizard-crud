package com.motaharinia.client.project.modules.member.presentation;



import com.motaharinia.client.project.utility.custom.customvalidation.required.Required;
import com.motaharinia.client.project.utility.custom.customvalidation.stringlength.StringLength;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدل تنظیمات عضو
 */
@Data
public class MemberSettingDto implements Serializable {
    /**
     * شناسه
     */
    private Long id;
    /**
     * عنوان پروفایل
     */
    @Required
    @StringLength(min = 3)
    private String profileTitle;
    /**
     *اندازه فونت
     */
    @Required
    private Integer fontSize;
    /**
     * رنگ
     */
    @Required
    private String color;
    /**
     *تاریخ درخواست
     */
    @Required
    private Long dateOfRequest;
    /**
     *شماره حسابهای بانکی انتخابی
     */
    private Set<String> accountSet = new HashSet<>();
}
