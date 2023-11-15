package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Profil;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ProfilRepository;
import cil.bf.activiteApp.service.ProfilService;
import cil.bf.activiteApp.service.dto.ProfilDTO;
import cil.bf.activiteApp.service.mapper.ProfilMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
@Transactional
public class ProfilServiceImpl implements ProfilService {

    private final ProfilRepository profilRepository;

    private final ProfilMapper mapper;

    public ProfilServiceImpl(ProfilRepository profilRepository, ProfilMapper profilMapper) {
        this.profilRepository = profilRepository;
        this.mapper = profilMapper;
    }

    @Override
    public ProfilDTO save(ProfilDTO profilDTO) {
        log.info("Creation d'un Profile : {}", profilDTO);
        Profil profile = profilRepository.save(mapper.toEntity(profilDTO));
        return mapper.toDto(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfilDTO> findAll() {
        log.info("Liste de tous les Profiles");
        return profilRepository.findAll().stream()
                .map(p -> mapper.toDto(p))
                .collect(Collectors.toList());
    }

    public ProfilDTO findById(Long id) {
        log.info("Consulte un profile via ID : {}", id);
        return mapper.toDto(profilRepository.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getProfiles() {
        log.info("Liste des libelle de tous les Profiles");
        return profilRepository.findAll().stream().map(Profil::getLibelle).collect(Collectors.toList());
    }

    public ProfilDTO findByCode(String code) {
        log.info("Consulte un profile via CODE : {}", code);
        return mapper.toDto(profilRepository.findByCode(code).orElse(null));
    }

    @Override
    @Transactional
    public void delete(ProfilDTO profileDTO) {
        log.info("Supprime un profile : {}", profileDTO);
        if (profileDTO.getId() < 3) {
            throw new CustomException("Ce profile ne peut être supprimé.");
        }
        profilRepository.deleteAssociatePrivilege(profileDTO.getId());
        profilRepository.delete(mapper.toEntity(profileDTO));
    }

}
