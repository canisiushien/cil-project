package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import java.util.List;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
public interface PrivilegeService {

    /**
     * Liste tous les privileges
     *
     * @return
     */
    List<PrivilegeDTO> findAll();

}
