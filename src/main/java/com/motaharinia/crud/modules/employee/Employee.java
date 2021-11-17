package com.motaharinia.crud.modules.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NotNull
    private Integer id;
    @NotBlank
    @Length(min=2, max=255)
    private String firstName;
    @NotBlank @Length(min=2, max=255)
    private String lastName;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

}