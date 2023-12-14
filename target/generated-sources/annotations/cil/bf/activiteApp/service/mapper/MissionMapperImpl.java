package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Mission;
import cil.bf.activiteApp.service.dto.MissionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MissionMapperImpl implements MissionMapper {

    @Override
    public MissionDTO toDto(Mission mission) {
        if ( mission == null ) {
            return null;
        }

        MissionDTO missionDTO = new MissionDTO();

        missionDTO.setId( mission.getId() );
        missionDTO.setNumMission( mission.getNumMission() );
        missionDTO.setBudget( mission.getBudget() );
        missionDTO.setDateDebut( mission.getDateDebut() );
        missionDTO.setDateFin( mission.getDateFin() );
        missionDTO.setLieu( mission.getLieu() );
        missionDTO.setEtat( mission.getEtat() );

        return missionDTO;
    }

    @Override
    public Mission toEntity(MissionDTO missionDTO) {
        if ( missionDTO == null ) {
            return null;
        }

        Mission mission = new Mission();

        mission.setId( missionDTO.getId() );
        mission.setNumMission( missionDTO.getNumMission() );
        mission.setBudget( missionDTO.getBudget() );
        mission.setDateDebut( missionDTO.getDateDebut() );
        mission.setDateFin( missionDTO.getDateFin() );
        mission.setLieu( missionDTO.getLieu() );
        mission.setEtat( missionDTO.getEtat() );

        return mission;
    }
}
