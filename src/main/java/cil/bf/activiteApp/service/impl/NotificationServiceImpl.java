package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.NotificationRepository;
import cil.bf.activiteApp.service.NotificationService;
import cil.bf.activiteApp.service.dto.NotificationDTO;
import cil.bf.activiteApp.service.mapper.NotificationMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDTO create(NotificationDTO notificationDTO) {
        log.info("Creation d'une notification : {}", notificationDTO);
        return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDTO)));
    }

    @Override
    public NotificationDTO update(NotificationDTO notificationDTO) {
        log.info("Update d'une notification : {}", notificationDTO);
        return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDTO)));
    }

    @Override
    public Optional<NotificationDTO> getById(Long id) {
        log.info("Consultation d'une notification : {}", id);
        return Optional.ofNullable(notificationMapper.toDto(notificationRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<NotificationDTO> findAll(Pageable pageable) {
        log.info("Liste des notifications");
        return notificationRepository.findAll(pageable).map(notificationMapper::toDto);
    }

    @Override
    public List<NotificationDTO> findAll() {
        return notificationRepository.findAll().stream().map(notificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<NotificationDTO> findAllByUser(Long userId, Pageable pageable) {

//        return notificationRepository.findAllByUtilisateurId(userId, pageable).map(notificationMapper::toDto);

        return notificationRepository.findByUtilisateurId(userId, pageable).map(notificationMapper::toDto);
    }

    @Override
    public Page<NotificationDTO> findAllUnreadByUser(Long userId, Pageable pageable) {

//        return notificationRepository.findAllByUtilisateurIdAndActifFalse(userId, pageable).map(notificationMapper::toDto);

        return notificationRepository.findByUtilisateurIdAndActifFalse(userId, pageable).map(notificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une notification : {}", id);
        notificationRepository.deleteById(id);
    }
}
