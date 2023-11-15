package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Document;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DocumentMapper {

    DocumentDTO toDto(Document document);

    Document toEntity(DocumentDTO documentDTO);
}
