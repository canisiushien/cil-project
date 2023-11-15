package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.ObjectifOperationnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectifOperationnelRepository extends JpaRepository<ObjectifOperationnel, Long> {
}
