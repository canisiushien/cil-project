package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Document;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE d.chemin = :fileUri")
    Optional<Document> findByChemin(@Param("fileUri") String fileUri);
}
