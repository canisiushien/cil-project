package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Document;
import cil.bf.activiteApp.domain.Reunion;
import cil.bf.activiteApp.domain.TypeReunion;
import cil.bf.activiteApp.domain.TypeSession;
import cil.bf.activiteApp.service.dto.ReunionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:29+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ReunionMapperImpl implements ReunionMapper {

    @Override
    public ReunionDTO toDto(Reunion r) {
        if ( r == null ) {
            return null;
        }

        ReunionDTO reunionDTO = new ReunionDTO();

        reunionDTO.setIdTypeReunion( rTypeReunionId( r ) );
        reunionDTO.setIdTypeSession( rTypeSessionId( r ) );
        reunionDTO.setId( r.getId() );
        reunionDTO.setDateReunion( r.getDateReunion() );
        reunionDTO.setHeureDebut( r.getHeureDebut() );
        reunionDTO.setHeureFin( r.getHeureFin() );
        reunionDTO.setOrdreJour( r.getOrdreJour() );
        reunionDTO.setNumeroReunion( r.getNumeroReunion() );
        reunionDTO.setEtat( r.getEtat() );
        reunionDTO.setIntituleSession( r.getIntituleSession() );
        reunionDTO.setSalleSession( r.getSalleSession() );
        List<Document> list = r.getDocuments();
        if ( list != null ) {
            reunionDTO.setDocuments( new ArrayList<Document>( list ) );
        }

        return reunionDTO;
    }

    @Override
    public Reunion toEntity(ReunionDTO r) {
        if ( r == null ) {
            return null;
        }

        Reunion reunion = new Reunion();

        reunion.setTypeReunion( reunionDTOToTypeReunion( r ) );
        reunion.setId( r.getId() );
        reunion.setDateReunion( r.getDateReunion() );
        reunion.setHeureDebut( r.getHeureDebut() );
        reunion.setHeureFin( r.getHeureFin() );
        reunion.setOrdreJour( r.getOrdreJour() );
        reunion.setNumeroReunion( r.getNumeroReunion() );
        reunion.setEtat( r.getEtat() );
        reunion.setIntituleSession( r.getIntituleSession() );
        reunion.setSalleSession( r.getSalleSession() );
        List<Document> list = r.getDocuments();
        if ( list != null ) {
            reunion.setDocuments( new ArrayList<Document>( list ) );
        }

        return reunion;
    }

    private Long rTypeReunionId(Reunion reunion) {
        if ( reunion == null ) {
            return null;
        }
        TypeReunion typeReunion = reunion.getTypeReunion();
        if ( typeReunion == null ) {
            return null;
        }
        Long id = typeReunion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long rTypeSessionId(Reunion reunion) {
        if ( reunion == null ) {
            return null;
        }
        TypeSession typeSession = reunion.getTypeSession();
        if ( typeSession == null ) {
            return null;
        }
        Long id = typeSession.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TypeReunion reunionDTOToTypeReunion(ReunionDTO reunionDTO) {
        if ( reunionDTO == null ) {
            return null;
        }

        TypeReunion typeReunion = new TypeReunion();

        typeReunion.setId( reunionDTO.getIdTypeReunion() );

        return typeReunion;
    }
}
