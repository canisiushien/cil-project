package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.service.dto.ActionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ActionMapperImpl implements ActionMapper {

    @Override
    public ActionDTO toDto(Action action) {
        if ( action == null ) {
            return null;
        }

        ActionDTO actionDTO = new ActionDTO();

        actionDTO.setId( action.getId() );
        actionDTO.setCode( action.getCode() );
        actionDTO.setLibelle( action.getLibelle() );
        actionDTO.setCout( action.getCout() );
        actionDTO.setCibleReel( action.getCibleReel() );
        actionDTO.setUniteReel( action.getUniteReel() );
        actionDTO.setObjectifStrategique( action.getObjectifStrategique() );

        return actionDTO;
    }

    @Override
    public Action toEntity(ActionDTO actionDTO) {
        if ( actionDTO == null ) {
            return null;
        }

        Action action = new Action();

        action.setId( actionDTO.getId() );
        action.setCode( actionDTO.getCode() );
        action.setLibelle( actionDTO.getLibelle() );
        action.setCout( actionDTO.getCout() );
        action.setCibleReel( actionDTO.getCibleReel() );
        action.setUniteReel( actionDTO.getUniteReel() );
        action.setObjectifStrategique( actionDTO.getObjectifStrategique() );

        return action;
    }
}
