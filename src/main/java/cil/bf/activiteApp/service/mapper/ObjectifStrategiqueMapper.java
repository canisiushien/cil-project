package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.ObjectifStrategique;
import cil.bf.activiteApp.service.dto.ObjectifStrategiqueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ObjectifStrategiqueMapper {

    ObjectifStrategiqueDTO toDto(ObjectifStrategique o);

    ObjectifStrategique toEntity(ObjectifStrategiqueDTO o);
}
