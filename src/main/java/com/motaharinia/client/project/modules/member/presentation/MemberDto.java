package com.motaharinia.client.project.modules.member.presentation;

import com.motaharinia.client.project.utility.custom.customvalidation.nationalcode.NationalCode;
import com.motaharinia.client.project.utility.custom.customvalidation.required.Required;
import com.motaharinia.client.project.utility.custom.customvalidation.stringlength.StringLength;
import lombok.Data;



@Data
public class MemberDto {
    /**
     * شناسه
     */
    private Long id;
    /**
     * نام
     */
    @Required
    @StringLength(min = 3)
    private String firstName;
    /**
     * نام خانوادگی
     */
    @Required
    @StringLength(min = 3)
    private String lastName;
    /**
     * کد ملی
     */
    @Required
    @NationalCode
    private String nationalCode;
    /**
     *تاریخ تولد
     */
    @Required
    private Long dateOfBirth;
}
