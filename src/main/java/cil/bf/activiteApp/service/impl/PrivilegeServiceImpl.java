package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.PrivilegeRepository;
import cil.bf.activiteApp.service.PrivilegeService;
import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import cil.bf.activiteApp.service.mapper.PrivilegeMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Slf4j
@Transactional
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    
    private final PrivilegeRepository privilegeRepository;
    
    private final PrivilegeMapper privilegeMapper;
    
    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, PrivilegeMapper privilegeMapper) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeMapper = privilegeMapper;
    }

    /**
     *
     * @return
     */
    public List<PrivilegeDTO> findAll() {
        log.info("Liste de tous les privilèges");
        return privilegeRepository.findAll()
                .stream()
                .map(p -> privilegeMapper.toDto(p))
                .collect(Collectors.toList());
    }
}
