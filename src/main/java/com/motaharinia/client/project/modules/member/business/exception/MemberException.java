package com.motaharinia.client.project.modules.member.business.exception;


import com.motaharinia.client.project.utility.custom.customexception.business.BusinessException;
import org.jetbrains.annotations.NotNull;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس خطا عضو
 */
public class MemberException extends BusinessException {

    public MemberException(@NotNull String exceptionClassId, @NotNull String exceptionEnumString, @NotNull String exceptionDescription) {
        super(MemberException.class, exceptionClassId, exceptionEnumString, exceptionDescription);
    }
    
}
