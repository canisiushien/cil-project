package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeDocument;
import cil.bf.activiteApp.service.dto.TypeDocumentDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T18:47:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TypeDocumentMapperImpl implements TypeDocumentMapper {

    @Override
    public TypeDocumentDTO toDto(TypeDocument t) {
        if ( t == null ) {
            return null;
        }

        TypeDocumentDTO typeDocumentDTO = new TypeDocumentDTO();

        typeDocumentDTO.setId( t.getId() );
        typeDocumentDTO.setLibelle( t.getLibelle() );

        return typeDocumentDTO;
    }

    @Override
    public TypeDocument toEntity(TypeDocumentDTO t) {
        if ( t == null ) {
            return null;
        }

        TypeDocument typeDocument = new TypeDocument();

        typeDocument.setId( t.getId() );
        typeDocument.setLibelle( t.getLibelle() );

        return typeDocument;
    }
}
