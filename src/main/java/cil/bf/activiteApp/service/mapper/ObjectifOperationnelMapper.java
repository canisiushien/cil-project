package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.ObjectifOperationnel;
import cil.bf.activiteApp.service.dto.ObjectifOperationnelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ObjectifOperationnelMapper {

    ObjectifOperationnelDTO toDto(ObjectifOperationnel o);

    ObjectifOperationnel toEntity(ObjectifOperationnelDTO o);
}
