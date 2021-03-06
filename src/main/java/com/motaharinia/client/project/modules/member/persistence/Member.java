package com.motaharinia.client.project.modules.member.persistence;

import com.motaharinia.client.project.utility.entity.CustomEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس انتیتی عضو
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
    /**
     *شناسه داکیومنت تنظیمات
     */
    private Long settingId;
}