package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Notification;
import cil.bf.activiteApp.service.dto.NotificationDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setId( notification.getId() );
        notificationDTO.setMessage( notification.getMessage() );
        notificationDTO.setAction( notification.getAction() );
        notificationDTO.setActif( notification.isActif() );
        notificationDTO.setIdAction( notification.getIdAction() );
        notificationDTO.setUtilisateur( notification.getUtilisateur() );

        return notificationDTO;
    }

    @Override
    public Notification toEntity(NotificationDTO notificationDTO) {
        if ( notificationDTO == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setId( notificationDTO.getId() );
        notification.setMessage( notificationDTO.getMessage() );
        notification.setAction( notificationDTO.getAction() );
        notification.setActif( notificationDTO.isActif() );
        notification.setIdAction( notificationDTO.getIdAction() );
        notification.setUtilisateur( notificationDTO.getUtilisateur() );

        return notification;
    }
}
