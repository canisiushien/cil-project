package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeDocument;
import cil.bf.activiteApp.service.dto.TypeDocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TypeDocumentMapper {

    TypeDocumentDTO toDto(TypeDocument t);

    TypeDocument toEntity(TypeDocumentDTO t);
}
