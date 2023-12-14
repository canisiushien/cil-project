package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Privilege;
import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:29+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PrivilegeMapperImpl implements PrivilegeMapper {

    @Override
    public PrivilegeDTO toDto(Privilege exercice) {
        if ( exercice == null ) {
            return null;
        }

        PrivilegeDTO privilegeDTO = new PrivilegeDTO();

        privilegeDTO.setId( exercice.getId() );
        privilegeDTO.setCode( exercice.getCode() );
        privilegeDTO.setLibelle( exercice.getLibelle() );

        return privilegeDTO;
    }

    @Override
    public Privilege toEntity(PrivilegeDTO exerciceDTO) {
        if ( exerciceDTO == null ) {
            return null;
        }

        Privilege privilege = new Privilege();

        privilege.setLibelle( exerciceDTO.getLibelle() );
        privilege.setId( exerciceDTO.getId() );
        privilege.setCode( exerciceDTO.getCode() );

        return privilege;
    }
}
