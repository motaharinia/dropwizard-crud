package com.motaharinia.crud.modules.member.presentation;

import com.motaharinia.crud.utility.custom.customvalidation.nationalcode.NationalCode;
import com.motaharinia.crud.utility.custom.customvalidation.required.Required;
import com.motaharinia.crud.utility.custom.customvalidation.stringlength.StringLength;
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
