package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeControleMission;
import cil.bf.activiteApp.service.dto.TypeControleMissionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-15T16:36:39+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TypeControleMissionMapperImpl implements TypeControleMissionMapper {

    @Override
    public TypeControleMissionDTO toDto(TypeControleMission typeControleMission) {
        if ( typeControleMission == null ) {
            return null;
        }

        TypeControleMissionDTO typeControleMissionDTO = new TypeControleMissionDTO();

        typeControleMissionDTO.setId( typeControleMission.getId() );
        typeControleMissionDTO.setLibelle( typeControleMission.getLibelle() );

        return typeControleMissionDTO;
    }

    @Override
    public TypeControleMission toEntity(TypeControleMissionDTO typeControleMissionDTO) {
        if ( typeControleMissionDTO == null ) {
            return null;
        }

        TypeControleMission typeControleMission = new TypeControleMission();

        typeControleMission.setId( typeControleMissionDTO.getId() );
        typeControleMission.setLibelle( typeControleMissionDTO.getLibelle() );

        return typeControleMission;
    }
}
