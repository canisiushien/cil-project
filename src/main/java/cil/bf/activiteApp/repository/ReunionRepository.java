package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {

    List<Reunion> findByTypeReunionId(Long id);


}
