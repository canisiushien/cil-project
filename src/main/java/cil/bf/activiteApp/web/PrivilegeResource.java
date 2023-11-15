package cil.bf.activiteApp.web;

import cil.bf.activiteApp.service.PrivilegeService;
import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/auth/privileges")
public class PrivilegeResource {

    private final PrivilegeService privilegeService;

    public PrivilegeResource(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping("/list")
    public List<PrivilegeDTO> findAll() {
        return privilegeService.findAll();
    }
}
