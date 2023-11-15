package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.NotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
public interface NotificationService {
    NotificationDTO create(NotificationDTO notificationDTO);

    NotificationDTO update(NotificationDTO notificationDTO);

    Optional<NotificationDTO> getById(Long id);

    Page<NotificationDTO> findAll(Pageable pageable);

    List<NotificationDTO> findAll();

    Page<NotificationDTO> findAllByUser(Long userId, Pageable pageable);
    Page<NotificationDTO> findAllUnreadByUser(Long userId, Pageable pageable);

    void delete(Long id);
}
