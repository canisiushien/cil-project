package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Privilege;
import cil.bf.activiteApp.domain.Profil;
import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import cil.bf.activiteApp.service.dto.ProfilDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T18:47:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProfilMapperImpl implements ProfilMapper {

    @Override
    public ProfilDTO toDto(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        ProfilDTO profilDTO = new ProfilDTO();

        profilDTO.setId( profil.getId() );
        profilDTO.setCode( profil.getCode() );
        profilDTO.setLibelle( profil.getLibelle() );
        profilDTO.setNativeProfile( profil.isNativeProfile() );
        profilDTO.setPrivilegeCollection( privilegeSetToPrivilegeDTOSet( profil.getPrivilegeCollection() ) );

        return profilDTO;
    }

    @Override
    public Profil toEntity(ProfilDTO profilDTO) {
        if ( profilDTO == null ) {
            return null;
        }

        Profil profil = new Profil();

        profil.setLibelle( profilDTO.getLibelle() );
        profil.setId( profilDTO.getId() );
        profil.setCode( profilDTO.getCode() );
        profil.setNativeProfile( profilDTO.isNativeProfile() );
        profil.setPrivilegeCollection( privilegeDTOSetToPrivilegeSet( profilDTO.getPrivilegeCollection() ) );

        return profil;
    }

    protected PrivilegeDTO privilegeToPrivilegeDTO(Privilege privilege) {
        if ( privilege == null ) {
            return null;
        }

        PrivilegeDTO privilegeDTO = new PrivilegeDTO();

        privilegeDTO.setId( privilege.getId() );
        privilegeDTO.setCode( privilege.getCode() );
        privilegeDTO.setLibelle( privilege.getLibelle() );

        return privilegeDTO;
    }

    protected Set<PrivilegeDTO> privilegeSetToPrivilegeDTOSet(Set<Privilege> set) {
        if ( set == null ) {
            return null;
        }

        Set<PrivilegeDTO> set1 = new LinkedHashSet<PrivilegeDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Privilege privilege : set ) {
            set1.add( privilegeToPrivilegeDTO( privilege ) );
        }

        return set1;
    }

    protected Privilege privilegeDTOToPrivilege(PrivilegeDTO privilegeDTO) {
        if ( privilegeDTO == null ) {
            return null;
        }

        Privilege privilege = new Privilege();

        privilege.setLibelle( privilegeDTO.getLibelle() );
        privilege.setId( privilegeDTO.getId() );
        privilege.setCode( privilegeDTO.getCode() );

        return privilege;
    }

    protected Set<Privilege> privilegeDTOSetToPrivilegeSet(Set<PrivilegeDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Privilege> set1 = new LinkedHashSet<Privilege>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PrivilegeDTO privilegeDTO : set ) {
            set1.add( privilegeDTOToPrivilege( privilegeDTO ) );
        }

        return set1;
    }
}
