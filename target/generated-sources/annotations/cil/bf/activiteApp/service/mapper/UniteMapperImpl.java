package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Unite;
import cil.bf.activiteApp.service.dto.UniteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:28+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UniteMapperImpl implements UniteMapper {

    @Override
    public UniteDTO toDto(Unite u) {
        if ( u == null ) {
            return null;
        }

        UniteDTO uniteDTO = new UniteDTO();

        uniteDTO.setId( u.getId() );
        uniteDTO.setLibelle( u.getLibelle() );

        return uniteDTO;
    }

    @Override
    public Unite toEntity(UniteDTO u) {
        if ( u == null ) {
            return null;
        }

        Unite unite = new Unite();

        unite.setId( u.getId() );
        unite.setLibelle( u.getLibelle() );

        return unite;
    }
}
