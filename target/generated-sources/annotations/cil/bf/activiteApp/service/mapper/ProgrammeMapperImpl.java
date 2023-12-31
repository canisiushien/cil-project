package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Programme;
import cil.bf.activiteApp.service.dto.ProgrammeDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProgrammeMapperImpl implements ProgrammeMapper {

    @Override
    public ProgrammeDTO toDto(Programme programme) {
        if ( programme == null ) {
            return null;
        }

        ProgrammeDTO programmeDTO = new ProgrammeDTO();

        programmeDTO.setId( programme.getId() );
        programmeDTO.setCode( programme.getCode() );
        programmeDTO.setLibelle( programme.getLibelle() );
        programmeDTO.setCout( programme.getCout() );
        programmeDTO.setCoutReel( programme.getCoutReel() );
        programmeDTO.setUniteReel( programme.getUniteReel() );
        programmeDTO.setCibleReel( programme.getCibleReel() );
        programmeDTO.setExercice( programme.getExercice() );

        return programmeDTO;
    }

    @Override
    public Programme toEntity(ProgrammeDTO programmeDTO) {
        if ( programmeDTO == null ) {
            return null;
        }

        Programme programme = new Programme();

        programme.setId( programmeDTO.getId() );
        programme.setCode( programmeDTO.getCode() );
        programme.setLibelle( programmeDTO.getLibelle() );
        programme.setCout( programmeDTO.getCout() );
        programme.setCoutReel( programmeDTO.getCoutReel() );
        programme.setUniteReel( programmeDTO.getUniteReel() );
        programme.setCibleReel( programmeDTO.getCibleReel() );
        programme.setExercice( programmeDTO.getExercice() );

        return programme;
    }
}
