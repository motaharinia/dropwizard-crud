package com.motaharinia.client.project.modules.member.business.mapper;



import com.motaharinia.client.project.modules.member.persistence.MemberSettingDocument;
import com.motaharinia.client.project.modules.member.presentation.MemberSettingDto;
import com.motaharinia.client.project.utility.custom.custommapper.CustomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مبدل داکیومنت و مدل تنظیمات عضو
 */
@Mapper
public interface MemberSettingDocumentMapper extends CustomMapper {
    MemberSettingDocumentMapper INSTANCE = Mappers.getMapper(MemberSettingDocumentMapper.class);

    MemberSettingDto toDto(MemberSettingDocument document);
    MemberSettingDocument toDocument(MemberSettingDto dto);
    ArrayList<MemberSettingDto> toDtoList(List<MemberSettingDocument> documentList);
    List<MemberSettingDocument> toDocumentList(ArrayList<MemberSettingDto> dtoList);

    void updateDocument(MemberSettingDto dto, @MappingTarget MemberSettingDocument document);
}
