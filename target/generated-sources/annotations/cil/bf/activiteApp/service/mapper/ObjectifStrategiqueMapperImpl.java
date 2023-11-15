package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.ObjectifStrategique;
import cil.bf.activiteApp.service.dto.ObjectifStrategiqueDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-15T16:36:39+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class ObjectifStrategiqueMapperImpl implements ObjectifStrategiqueMapper {

    @Override
    public ObjectifStrategiqueDTO toDto(ObjectifStrategique o) {
        if ( o == null ) {
            return null;
        }

        ObjectifStrategiqueDTO objectifStrategiqueDTO = new ObjectifStrategiqueDTO();

        objectifStrategiqueDTO.setId( o.getId() );
        objectifStrategiqueDTO.setCode( o.getCode() );
        objectifStrategiqueDTO.setLibelle( o.getLibelle() );
        objectifStrategiqueDTO.setCout( o.getCout() );
        objectifStrategiqueDTO.setCoutReel( o.getCoutReel() );
        objectifStrategiqueDTO.setUniteReel( o.getUniteReel() );
        objectifStrategiqueDTO.setCibleReel( o.getCibleReel() );
        objectifStrategiqueDTO.setProgramme( o.getProgramme() );
        objectifStrategiqueDTO.setIndicateur( o.getIndicateur() );

        return objectifStrategiqueDTO;
    }

    @Override
    public ObjectifStrategique toEntity(ObjectifStrategiqueDTO o) {
        if ( o == null ) {
            return null;
        }

        ObjectifStrategique objectifStrategique = new ObjectifStrategique();

        objectifStrategique.setId( o.getId() );
        objectifStrategique.setCode( o.getCode() );
        objectifStrategique.setLibelle( o.getLibelle() );
        objectifStrategique.setCout( o.getCout() );
        objectifStrategique.setCoutReel( o.getCoutReel() );
        objectifStrategique.setUniteReel( o.getUniteReel() );
        objectifStrategique.setCibleReel( o.getCibleReel() );
        objectifStrategique.setProgramme( o.getProgramme() );
        objectifStrategique.setIndicateur( o.getIndicateur() );

        return objectifStrategique;
    }
}
