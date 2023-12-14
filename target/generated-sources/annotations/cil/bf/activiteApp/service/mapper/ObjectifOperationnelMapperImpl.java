package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.ObjectifOperationnel;
import cil.bf.activiteApp.service.dto.ObjectifOperationnelDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:28+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ObjectifOperationnelMapperImpl implements ObjectifOperationnelMapper {

    @Override
    public ObjectifOperationnelDTO toDto(ObjectifOperationnel o) {
        if ( o == null ) {
            return null;
        }

        ObjectifOperationnelDTO objectifOperationnelDTO = new ObjectifOperationnelDTO();

        objectifOperationnelDTO.setId( o.getId() );
        objectifOperationnelDTO.setCode( o.getCode() );
        objectifOperationnelDTO.setLibelle( o.getLibelle() );
        objectifOperationnelDTO.setCout( o.getCout() );
        objectifOperationnelDTO.setCoutReel( o.getCoutReel() );
        objectifOperationnelDTO.setCibleReel( o.getCibleReel() );
        objectifOperationnelDTO.setUniteReel( o.getUniteReel() );
        objectifOperationnelDTO.setObjectifOperationnelParent( o.getObjectifOperationnelParent() );
        objectifOperationnelDTO.setAction( o.getAction() );
        objectifOperationnelDTO.setIndicateur( o.getIndicateur() );

        return objectifOperationnelDTO;
    }

    @Override
    public ObjectifOperationnel toEntity(ObjectifOperationnelDTO o) {
        if ( o == null ) {
            return null;
        }

        ObjectifOperationnel objectifOperationnel = new ObjectifOperationnel();

        objectifOperationnel.setId( o.getId() );
        objectifOperationnel.setCode( o.getCode() );
        objectifOperationnel.setLibelle( o.getLibelle() );
        objectifOperationnel.setCout( o.getCout() );
        objectifOperationnel.setCoutReel( o.getCoutReel() );
        objectifOperationnel.setCibleReel( o.getCibleReel() );
        objectifOperationnel.setUniteReel( o.getUniteReel() );
        objectifOperationnel.setObjectifOperationnelParent( o.getObjectifOperationnelParent() );
        objectifOperationnel.setAction( o.getAction() );
        objectifOperationnel.setIndicateur( o.getIndicateur() );

        return objectifOperationnel;
    }
}
