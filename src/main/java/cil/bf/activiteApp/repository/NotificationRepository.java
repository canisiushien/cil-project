package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    Page<Notification> findAllByUtilisateurId(Long id, Pageable pageable);
//    Page<Notification> findAllByUtilisateurIdAndActifFalse(Long id, Pageable pageable);
    Page<Notification> findByUtilisateurId(Long id, Pageable pageable);
    Page<Notification> findByUtilisateurIdAndActifFalse(Long id, Pageable pageable);

}
