package com.motaharinia.client.project.modules.member.persistence;


import com.motaharinia.client.project.utility.document.CustomDocument;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس داکیومنت تنظیمات عضو
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MemberSettingDocument extends CustomDocument implements Serializable {
    /**
     * شناسه
     */
    @Id
    private Long id;
    /**
     * عنوان پروفایل
     */
//    @Field("profile_title")
    private String profileTitle;
    /**
     *اندازه فونت
     */
//    @Field("font_size")
    private Integer fontSize;
    /**
     * رنگ
     */
//    @Field("color")
    private String color;
    /**
     *تاریخ درخواست
     */
//    @Field("date_of_request")
    private Long dateOfRequest;
    /**
     *شماره حسابهای بانکی انتخابی
     */
//    @Field("account_set")
    private Set<String> accountSet = new HashSet<>();
}
