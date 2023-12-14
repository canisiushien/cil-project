package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeSession;
import cil.bf.activiteApp.service.dto.TypeSessionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TypeSessionMapperImpl implements TypeSessionMapper {

    @Override
    public TypeSessionDTO toDto(TypeSession t) {
        if ( t == null ) {
            return null;
        }

        TypeSessionDTO typeSessionDTO = new TypeSessionDTO();

        typeSessionDTO.setId( t.getId() );
        typeSessionDTO.setLibelle( t.getLibelle() );

        return typeSessionDTO;
    }

    @Override
    public TypeSession toEntity(TypeSessionDTO t) {
        if ( t == null ) {
            return null;
        }

        TypeSession typeSession = new TypeSession();

        typeSession.setId( t.getId() );
        typeSession.setLibelle( t.getLibelle() );

        return typeSession;
    }
}
