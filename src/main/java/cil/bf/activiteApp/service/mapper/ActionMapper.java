package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.service.dto.ActionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ActionMapper {

    ActionDTO toDto(Action action);

    Action toEntity(ActionDTO actionDTO);
}
