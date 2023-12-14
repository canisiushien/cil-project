package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Document;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentDTO toDto(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId( document.getId() );
        documentDTO.setLibelle( document.getLibelle() );
        documentDTO.setChemin( document.getChemin() );
        documentDTO.setFormat( document.getFormat() );
        documentDTO.setTypeDocument( document.getTypeDocument() );

        return documentDTO;
    }

    @Override
    public Document toEntity(DocumentDTO documentDTO) {
        if ( documentDTO == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( documentDTO.getId() );
        document.setLibelle( documentDTO.getLibelle() );
        document.setChemin( documentDTO.getChemin() );
        document.setFormat( documentDTO.getFormat() );
        document.setTypeDocument( documentDTO.getTypeDocument() );

        return document;
    }
}
