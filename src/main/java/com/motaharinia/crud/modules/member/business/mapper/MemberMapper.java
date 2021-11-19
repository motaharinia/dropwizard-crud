package com.motaharinia.crud.modules.member.business.mapper;



import com.motaharinia.crud.modules.member.persistence.Member;
import com.motaharinia.crud.modules.member.presentation.MemberDto;
import com.motaharinia.crud.utility.custom.custommapper.CustomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مبدل انتیتی و مدل عضو
 */
@Mapper
public interface MemberMapper extends CustomMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toDto(Member entity);
    Member toEntity(MemberDto dto);
    ArrayList<MemberDto> toDtoList(List<Member> entityList);
    List<Member> toEntityList(ArrayList<MemberDto> dtoList);

    void updateEntity(MemberDto dto, @MappingTarget Member entity);
}
