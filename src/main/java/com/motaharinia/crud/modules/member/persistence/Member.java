package com.motaharinia.crud.modules.member.persistence;

import com.motaharinia.crud.utility.entity.CustomEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends CustomEntity implements Serializable {

    /**
     * شناسه
     */
    @NotNull
    private Long id;
    /**
     * نام
     */
    @NotBlank
    @Length(min=2, max=255)
    private String firstName;
    /**
     * نام خانوادگی
     */
    @NotBlank
    @Length(min=2, max=255)
    private String lastName;
    /**
     * کد ملی
     */
    @NotBlank
    private String nationalCode;
    /**
     * تاریخ تولد
     */
    private LocalDate dateOfBirth;
}